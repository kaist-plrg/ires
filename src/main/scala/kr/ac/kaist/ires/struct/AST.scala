package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import spray.json._

trait AST {
  var parent: Option[AST] = None
  val kind: String
  val idx: Int
  val k: Int
  val parserParams: List[Boolean]
  val info: ASTInfo
  val fullList: List[(String, Value)]

  // position
  var start: Int = 0
  var end: Int = 0

  // name
  def name: String = kind + idx

  // to JSON format
  def toJson: JsValue = JsObject(
    "kind" -> JsString(kind),
    "index" -> JsNumber(idx),
    "children" -> JsArray(fullList.map {
      case (_, ASTVal(ast)) => ast.toJson
      case _ => JsNull
    }: _*),
  )

  // get possible kinds
  def getKinds: Set[String] = (list match {
    case List((_, ASTVal(ast))) => ast.getKinds
    case _ => Set()
  }) ++ Set(kind)

  // get element list for the given kind
  def getElems(given: String): List[AST] = {
    if (given == kind) List(this)
    else list.foldLeft(List[AST]()) {
      case (l, (_, ASTVal(ast))) => l ++ ast.getElems(given)
      case (l, _) => l
    }
  }

  // list of actual values
  lazy val list: List[(String, Value)] = fullList.filter {
    case (_, Absent) => false
    case _ => true
  }

  // get semantics
  def semantics(fname: String): Option[(Func, List[Value])] = ???
  // {
  //   (info.semMap.get(fname + k.toString) match {
  //     case Some(f) => Some((f, ASTVal(this) :: list.map(_._2)))
  //     case None => info.semMap.get(fname + info.maxK.toString).map((f) => (f, ASTVal(this) :: fullList.map(_._2)))
  //   }) match {
  //     case Some(f) => Some(f)
  //     // `Contains` static semantics
  //     case None => if (fname == "Contains") Some((Func(
  //       name + fname,
  //       Id("this") :: (list.map { case (x, _) => Id(x) } :+ Id("symbol")),
  //       None,
  //       list.foldLeft[Inst](IReturn(EBool(false))) {
  //         case (base, (kind, value)) => IIf(
  //           EBOp(OEq, ERef(RefId(Id("symbol"))), EStr(kind)),
  //           IReturn(EBool(true)),
  //           ISeq(List(
  //             IAccess(Id("res"), ERef(RefId(Id(kind))), EStr("Contains")),
  //             IApp(Id("res"), ERef(RefId(Id("res"))), List(ERef(RefId(Id("symbol"))))),
  //             IIf(
  //               ERef(RefId(Id("res"))),
  //               IReturn(EBool(true)),
  //               base
  //             )
  //           ))
  //         )
  //       }
  //     ), ASTVal(this) :: list.map(_._2)))
  //     else (list match {
  //       // case None => (list match {
  //       case List((_, ASTVal(x))) => x.semantics(fname)
  //       case _ => None
  //     })
  //   }
  // }

  // existence check
  def exists(kindFilter: String => Boolean): Boolean = kindFilter(kind) || list.exists {
    case (_, ASTVal(ast)) => ast.exists(kindFilter)
    case _ => false
  }

  // get sub-AST
  def subs(name: String): Option[Value] = list.toMap.get(name)

  // Helpers
  protected def d(x: Any, n: Int): Int = x match {
    case Some(_) => 2 * n + 1
    case None => 2 * n
    case _ => n
  }
  protected def l(name: String, x: Any, list: List[(String, Value)]): List[(String, Value)] = x match {
    case Some(a: AST) => (name.substring(7, name.length - 1), ASTVal(a)) :: list
    case None => (name.substring(7, name.length - 1), Absent) :: list
    case a: AST => (name, ASTVal(a)) :: list
    case _ => list
  }
}

trait ASTInfo {
  val maxK: Int
  val semMap: Map[String, Algo]
}

case class Lexical(kind: String, str: String) extends AST {
  val idx: Int = 0
  val k: Int = 0
  val parserParams: List[Boolean] = Nil
  val info: ASTInfo = LexicalInfo
  val fullList: List[(String, Value)] = Nil

  // name
  override def name: String = kind

  // to JSON format
  override def toJson: JsValue = JsString(str)

  // conversion to string
  override def toString: String = str
}
object LexicalInfo extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}
