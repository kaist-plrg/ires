package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait LexicalDeclaration extends AST {
  val kind: String = "LexicalDeclaration"
}
object LexicalDeclaration extends ASTHelper {
  def apply(v: JsValue): LexicalDeclaration = v match {
    case JsSeq(JsInt(0), JsSeq(x0, x1), JsBoolSeq(params), JsSpan(span)) =>
      LexicalDeclaration0(LetOrConst(x0), BindingList(x1), params, span)
    case _ => throw InvalidAST
  }
}

case class LexicalDeclaration0(x0: LetOrConst, x1: BindingList, parserParams: List[Boolean], span: Span) extends LexicalDeclaration {
  x0.parent = Some(this)
  x1.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0 $x1 ;"
  }
  val k: Int = d(x1, d(x0, 0))
  val fullList: List[(String, Value)] = l("BindingList", x1, l("LetOrConst", x0, Nil)).reverse
  val info: ASTInfo = LexicalDeclaration0
}
object LexicalDeclaration0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::LexicalDeclaration[0,0].BoundNames`,
    "IsConstantDeclaration0" -> `AL::LexicalDeclaration[0,0].IsConstantDeclaration`,
    "Evaluation0" -> `AL::LexicalDeclaration[0,0].Evaluation`,
    "EarlyErrors0" -> `AL::LexicalDeclaration[0,0].EarlyErrors`,
  )
}
