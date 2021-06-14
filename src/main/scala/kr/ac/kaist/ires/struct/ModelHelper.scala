package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error._
import kr.ac.kaist.ires.util.Useful._
import kr.ac.kaist.ires.util.Span
import kr.ac.kaist.ires.parser.UnicodeRegex

trait ModelHelper {
  def getInitState(
    program: Program,
    globals: Map[Id, Value] = Map()
  ): State = initState.copy(
    context = initState.context.copy(insts = program.insts),
    globals = initState.globals ++ globals
  )

  private val initState: State = ???
  // State(
  //   context = Context(),
  //   globals = initGlobal,
  //   heap = initHeap
  // )

  def flattenStList(s: StatementList): List[StatementListItem] = s match {
    case StatementList0(x0, _, _) => List(x0)
    case StatementList1(x0, x1, _, _) => flattenStList(x0) :+ x1
  }

  def flattenStatement(s: Script) = s match {
    case Script0(Some(ScriptBody0(stlist, _, _)), _, _) =>
      flattenStList(stlist)
    case _ => List()
  }

  def mergeStatement(l: List[StatementListItem]): Script = {
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

  val SYMBOL_PREFIX = "GLOBAL.Symbol."
  def getPropStr(value: Value): String = value match {
    case Str(str) => s".$str"
    case _ => s"[${value.beautified}]"
  }

  def addBuiltin(
    map: Map[Addr, Obj],
    builtinMethods: List[(String, Int, Func)]
  ): Map[Addr, Obj] = ???
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
