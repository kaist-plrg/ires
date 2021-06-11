package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait LetOrConst extends AST {
  val kind: String = "LetOrConst"
}
object LetOrConst extends ASTHelper {
  def apply(v: JsValue): LetOrConst = v match {
    case JsSeq(JsInt(0), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      LetOrConst0(params, span)
    case JsSeq(JsInt(1), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      LetOrConst1(params, span)
    case _ => throw InvalidAST
  }
}

case class LetOrConst0(parserParams: List[Boolean], span: Span) extends LetOrConst {
  val idx: Int = 0
  override def toString: String = {
    s"let"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = LetOrConst0
}
object LetOrConst0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsConstantDeclaration0" -> `AL::LetOrConst[0,0].IsConstantDeclaration`,
  )
}

case class LetOrConst1(parserParams: List[Boolean], span: Span) extends LetOrConst {
  val idx: Int = 1
  override def toString: String = {
    s"const"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = LetOrConst1
}
object LetOrConst1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsConstantDeclaration0" -> `AL::LetOrConst[1,0].IsConstantDeclaration`,
  )
}
