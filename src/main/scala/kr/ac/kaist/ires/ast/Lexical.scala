package kr.ac.kaist.ires.ast

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.util.{ Span, Pos }
import spray.json._

case class Lexical(kind: String, str: String) extends AST {
  val idx: Int = 0
  val k: Int = 0
  val parserParams: List[Boolean] = Nil
  val info: ASTInfo = LexicalInfo
  val span: Span = Span()
  val fullList: List[(String, Value)] = Nil

  // name
  override def name: String = kind

  // to JSON format
  override def toJson: JsValue = JsString(str)

  // conversion to string
  override def toString: String = str
}
