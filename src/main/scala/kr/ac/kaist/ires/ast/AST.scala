package kr.ac.kaist.ires.ast

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.util.{ Span, Pos }
import spray.json._

trait AST {
  var parent: Option[AST] = None
  val kind: String
  val idx: Int
  val k: Int
  val span: Span
  val parserParams: List[Boolean]
  val info: ASTInfo
  val fullList: List[(String, Value)]

  // position
  var start: Int = 0
  var end: Int = 0

  // name
  def name: String = kind + idx

  // to JSON format
  def toJson: JsValue = JsArray(
    JsNumber(idx),
    JsArray(fullList.map {
      case (_, ASTVal(ast)) => ast.toJson
      case _ => JsNull
    }: _*),
    JsArray(parserParams.map(p => JsNumber(if (p) 1 else 0)): _*),
    JsArray(JsNumber(-1), JsNumber(-1), JsNumber(-1), JsNumber(-1)),
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
  def semantics(fname: String): Option[(Algo, List[Value])] = {
    info.semMap.get(fname + k.toString) match {
      case Some(f) => Some((f, ASTVal(this) :: list.map(_._2)))
      case None => info.semMap.get(fname + info.maxK.toString) match {
        case Some(f) => Some((f, ASTVal(this) :: fullList.map(_._2)))
        case None => list match {
          case List((_, ASTVal(x))) => x.semantics(fname)
          case _ => None
        }
      }
    }
  }

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
