package kr.ac.kaist.ires

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error._
import kr.ac.kaist.ires.model._
import kr.ac.kaist.ires.util.Useful._
import kr.ac.kaist.ires.util.Span
import kr.ac.kaist.ires.parser.UnicodeRegex
import scala.collection.mutable.{ Map => MMap }

trait ModelTrait {
  // initial state
  def initState(
    inst: Inst,
    body: ScriptBody,
    filename: String
  ): State = State(
    context = Context(insts = List(inst)),
    ctxtStack = Nil,
    globals = initGlobal(body, filename),
    heap = initHeap
  )

  // special names
  val AGENT = "AGENT"
  val ALGORITHM = "ALGORITHM"
  val CONTEXT = "CONTEXT"
  val EXECUTION_STACK = "EXECUTION_STACK"
  val FILENAME = "FILENAME"
  val GLOBAL = "GLOBAL"
  val INTRINSICS = "INTRINSICS"
  val JOB_QUEUE = "JOB_QUEUE"
  val PRIMITIVE = "PRIMITIVE"
  val REALM = "REALM"
  val RESULT = "RESULT"
  val RETURN = "RETURN"
  val SCRIPT_BODY = "SCRIPT_BODY"
  val HOST_DEFINED = "HOST_DEFINED"
  val SYMBOL_REGISTRY = "SYMBOL_REGISTRY"
  val TOP_LEVEL = "TOP_LEVEL"
  val TYPED_ARRAY_INFO = "TYPED_ARRAY_INFO"

  // prefixes
  val CONST_PREFIX = "CONST_"
  val INTRINSIC_PREFIX = "INTRINSIC_"
  val SYMBOL_PREFIX = "SYMBOL_"

  // initial global variables
  def initGlobal(
    body: ScriptBody,
    filename: String
  ): MMap[Id, Value] = {
    val map = MMap[Id, Value](
      Id(SCRIPT_BODY) -> ASTVal(body),
      Id(FILENAME) -> Str(filename),
    )
    for (c <- consts) {
      map += Id(CONST_PREFIX + c) -> NamedAddr(CONST_PREFIX + c)
    }
    for (i <- intrinsics) {
      map += Id(INTRINSIC_PREFIX + i) -> intrinsicToAddr(i)
    }
    for (s <- symbols) {
      map += Id(SYMBOL_PREFIX + s) -> NamedAddr(s"$GLOBAL.Symbol.$s")
    }
    for ((x, algo) <- algos if algo.isNormal) {
      map += Id(x) -> Func(algo)
    }
    for ((name, value) <- BaseModel.globals) {
      map += Id(name) -> value
    }
    map
  }

  // initial heap
  def initHeap: Heap = {
    val map = MMap[Addr, Obj]()
    for (c <- consts) {
      map += NamedAddr(CONST_PREFIX + c) -> IRSymbol(Str(CONST_PREFIX + c))
    }
    for (s <- symbols) {
      map += NamedAddr(s"$GLOBAL.Symbol.$s") -> IRSymbol(Str("Symbol." + s))
    }
    for ((addr, obj) <- BaseModel.heap) {
      map += addr -> obj
    }
    addBuiltin(map)
    Heap(map)
  }

  // constant names
  val consts: List[String]

  // intrinsic names
  val intrinsics: List[String]

  // symbol names
  val symbols: List[String]

  // algorithm map
  val algos: Map[String, Algo]

  // flatten statements
  def flattenStmtList(
    s: StatementList,
    list: List[StatementListItem] = Nil
  ): List[StatementListItem] = s match {
    case StatementList0(x0, _, _) => x0 :: list
    case StatementList1(x0, x1, _, _) => flattenStmtList(x0, x1 :: list)
  }
  def flattenStmt(s: Script): List[StatementListItem] = s match {
    case Script0(Some(ScriptBody0(stlist, _, _)), _, _) =>
      flattenStmtList(stlist)
    case _ => Nil
  }

  // merge statements to script
  def mergeStmt(l: List[StatementListItem]): Script = {
    val params = List(false, false, false)
    val bodyOpt = l match {
      case a :: rest => {
        val init: StatementList = StatementList0(a, params, a.span)
        val list = rest.foldLeft(init) {
          case (x, y) =>
            val span = Span(x.span.start, y.span.end)
            StatementList1(x, y, params, span)
        }
        Some(ScriptBody0(list, params, list.span))
      }
      case Nil => None
    }
    val span = bodyOpt.fold(Span())(_.span)
    Script0(bodyOpt, params, span)
  }

  def addBuiltin(map: MMap[Addr, Obj]): Unit = for {
    (_, algo) <- algos
    head <- algo.head match {
      case head: BuiltinHead => Some(head)
      case _ => None
    }
    (base, prop, propV, propName) <- head.ref match {
      case RefId(Id(name)) if name.head.isLower =>
        Some(GLOBAL, name, Str(name), name)
      case RefProp(ref, EStr(prop)) =>
        Some(GLOBAL + "." + ref.beautified, prop, Str(prop), prop)
      case RefProp(ref, ERef(RefId(Id(name)))) if name startsWith SYMBOL_PREFIX =>
        val symbolName = name.substring(SYMBOL_PREFIX.length)
        Some(GLOBAL + "." + ref.beautified, name, NamedAddr(name), s"[Symbol.$symbolName]")
      case _ => None
    }
    baseAddr = NamedAddr(s"$base.SubMap")
    irMap <- map.get(baseAddr) match {
      case Some(m: IRMap) => Some(m)
      case _ => None
    }
    name <- propV match {
      case Str(name) => Some(s"$base.$prop")
      case NamedAddr(name) => Some(s"$base[$name]")
      case _ => None
    }
    addr = NamedAddr(s"$name")
    descAddr = NamedAddr(s"DESC:$name")
  } {
    irMap.update(propV, descAddr)
    map += descAddr -> IRMap("PropertyDescriptor")(List(
      Str("Value") -> addr,
      Str("Writable") -> Bool(true),
      Str("Enumerable") -> Bool(false),
      Str("Configurable") -> Bool(true),
    ))
    map.get(addr) match {
      case Some(irMap: IRMap) => {
        irMap.update(Str("Extensible"), Bool(true))
        irMap.update(Str("ScriptOrModule"), Null)
        irMap.update(Str("Realm"), NamedAddr("REALM"))
      }
      case _ => map += addr -> IRMap("BuiltinFunctionObject")(List(
        Str("Code") -> Func(algo),
        Str("Prototype") -> NamedAddr("GLOBAL.Function.prototype"),
        Str("Extensible") -> Bool(true),
        Str("ScriptOrModule") -> Null,
        Str("Realm") -> NamedAddr("REALM"),
        Str("SubMap") -> NamedAddr(s"$name.SubMap"),
      ))
    }
    map += NamedAddr(s"$name.SubMap") -> IRMap("SubMap")(List(
      Str("name") -> NamedAddr(s"DESC:$name.name"),
      Str("length") -> NamedAddr(s"DESC:$name.length"),
    ))
    map += NamedAddr(s"DESC:$name.name") -> IRMap("PropertyDescriptor")(List(
      Str("Value") -> Str(propName),
      Str("Writable") -> Bool(false),
      Str("Enumerable") -> Bool(false),
      Str("Configurable") -> Bool(true),
    ))
    map += NamedAddr(s"DESC:$name.length") -> IRMap("PropertyDescriptor")(List(
      Str("Value") -> Num(head.origParams.length),
      Str("Writable") -> Bool(false),
      Str("Enumerable") -> Bool(false),
      Str("Configurable") -> Bool(true),
    ))
  }

  def intrinsicToAddr(name: String): Addr =
    NamedAddr(GLOBAL + "." + name.replaceAll("_", "."))

  private def getPropStr(value: Value): String = value match {
    case Str(str) => s".$str"
    case _ => s"[${value.beautified}]"
  }

  private val notSupportedSyntaxPrefixList = List("RegularExpression")
  def checkSupported(ast: AST): AST = {
    ast.exists(name => notSupportedSyntaxPrefixList.exists(pre => {
      if (name.startsWith(pre)) throw NotSupported(pre)
      false
    }))
    ast
  }
}
