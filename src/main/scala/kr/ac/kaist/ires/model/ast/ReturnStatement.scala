package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ReturnStatement extends AST {
  val kind: String = "ReturnStatement"
}
object ReturnStatement extends ASTHelper {
  def apply(v: JsValue): ReturnStatement = v match {
    case JsSeq(JsInt(0), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      ReturnStatement0(params, span)
    case JsSeq(JsInt(1), JsSeq(x2), JsBoolSeq(params), JsSpan(span)) =>
      ReturnStatement1(Expression(x2), params, span)
    case _ => throw InvalidAST
  }
}

case class ReturnStatement0(parserParams: List[Boolean], span: Span) extends ReturnStatement {
  val idx: Int = 0
  override def toString: String = {
    s"return ;"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = ReturnStatement0
}
object ReturnStatement0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "Evaluation0" -> `AL::ReturnStatement[0,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::ReturnStatement[0,0].HasCallInTailPosition`,
  )
}

case class ReturnStatement1(x2: Expression, parserParams: List[Boolean], span: Span) extends ReturnStatement {
  x2.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"return $x2 ;"
  }
  val k: Int = d(x2, 0)
  val fullList: List[(String, Value)] = l("Expression", x2, Nil).reverse
  val info: ASTInfo = ReturnStatement1
}
object ReturnStatement1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "Evaluation0" -> `AL::ReturnStatement[1,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::ReturnStatement[1,0].HasCallInTailPosition`,
  )
}
