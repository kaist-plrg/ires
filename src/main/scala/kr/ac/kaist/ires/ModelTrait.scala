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
  val CONTEXT = "CONTEXT"
  val EXECUTION_STACK = "EXECUTION_STACK"
  val FILENAME = "FILENAME"
  val GLOBAL = "GLOBAL"
  val INTRINSICS = "INTRINSICS"
  val JOB_QUEUE = "JOB_QUEUE"
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
      map += Id(INTRINSIC_PREFIX + i) -> NamedAddr(i.replaceAll("_", "."))
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

  // def getPropStr(value: Value): String = value match {
  //   case Str(str) => s".$str"
  //   case _ => s"[${value.beautified}]"
  // }

  // def addBuiltin(
  //   map: Map[Addr, Obj],
  //   builtinMethods: List[(String, Int, Func)]
  // ): Map[Addr, Obj] = ???
  // builtinMethods.foldLeft(map) {
  //   case (m, (givenName, length, func)) =>
  //     val base = removedExt(givenName)
  //     val prop = getExt(givenName)
  //     val (propV, propName) = if (prop.startsWith("SYMBOL_")) {
  //       val p = prop.substring("SYMBOL_".length)
  //       (NamedAddr(s"GLOBAL.Symbol.$p"), s"[Symbol.$p]")
  //     } else (Str(prop), prop)
  //     val name = base + getPropStr(propV)
  //     val addr = NamedAddr(name)
  //     val baseAddr =
  //       if (base == "GLOBAL") NamedAddr("GLOBAL")
  //       else NamedAddr(s"$base.SubMap")
  //     val descAddr = NamedAddr(s"DESC:$name")
  //     (m.get(baseAddr) match {
  //       case Some(IRMap(ty, map, size)) => m ++ List(
  //         baseAddr -> IRMap(ty, map + (propV -> (descAddr, size)), size + 1),
  //         descAddr -> m.getOrElse(descAddr, IRUMap(Ty("PropertyDescriptor"), Map(
  //           Str("Value") -> addr,
  //           Str("Writable") -> Bool(true),
  //           Str("Enumerable") -> Bool(false),
  //           Str("Configurable") -> Bool(true)
  //         )))
  //       )
  //       case _ => m
  //     }) + (m.get(addr) match {
  //       case Some(IRMap(ty, map, size)) =>
  //         addr -> IRMap(ty, map ++ Map(
  //           Str("Extensible") -> (Bool(true), size),
  //           Str("ScriptOrModule") -> (Null, size + 1),
  //           Str("Realm") -> (NamedAddr("REALM"), size + 2)
  //         ), size + 3)
  //       case _ =>
  //         addr -> IRUMap(Ty("BuiltinFunctionObject"), BuiltinFunctionObject.map - Str("Construct") ++ Map(
  //           Str("Code") -> func,
  //           Str("Prototype") -> NamedAddr("GLOBAL.Function.prototype"),
  //           Str("Extensible") -> Bool(true),
  //           Str("ScriptOrModule") -> Null,
  //           Str("Realm") -> NamedAddr("REALM"),
  //           Str("SubMap") -> NamedAddr(s"$name.SubMap")
  //         ))
  //     }) ++ List(
  //       NamedAddr(s"$name.SubMap") -> (m.getOrElse(NamedAddr(s"$name.SubMap"), IRUMap(Ty("SubMap"), Map())) match {
  //         case IRMap(ty, map, size) => IRMap(ty, map ++ List(
  //           Str("name") -> map.getOrElse(Str("name"), (NamedAddr(s"DESC:$name.name"), size)),
  //           Str("length") -> map.getOrElse(Str("length"), (NamedAddr(s"DESC:$name.length"), size + 1))
  //         ), size + 2)
  //         case obj => error(s"not a map: $obj")
  //       }),
  //       NamedAddr(s"DESC:$name.name") -> m.getOrElse(NamedAddr(s"DESC:$name.name"), IRUMap(Ty("PropertyDescriptor"), Map(
  //         Str("Value") -> Str(propName),
  //         Str("Writable") -> Bool(false),
  //         Str("Enumerable") -> Bool(false),
  //         Str("Configurable") -> Bool(true)
  //       ))),
  //       NamedAddr(s"DESC:$name.length") -> m.getOrElse(NamedAddr(s"DESC:$name.length"), IRUMap(Ty("PropertyDescriptor"), Map(
  //         Str("Value") -> Num(length),
  //         Str("Writable") -> Bool(false),
  //         Str("Enumerable") -> Bool(false),
  //         Str("Configurable") -> Bool(true)
  //       )))
  //     )
  // }

  private val notSupportedSyntaxPrefixList = List("RegularExpression")
  def checkSupported(ast: AST): AST = {
    ast.exists(name => notSupportedSyntaxPrefixList.exists(pre => {
      if (name.startsWith(pre)) throw NotSupported(pre)
      false
    }))
    ast
  }
}
