package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait MultiplicativeOperator extends AST {
  val kind: String = "MultiplicativeOperator"
}
object MultiplicativeOperator extends ASTHelper {
  def apply(v: JsValue): MultiplicativeOperator = v match {
    case JsSeq(JsInt(0), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      MultiplicativeOperator0(params, span)
    case JsSeq(JsInt(1), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      MultiplicativeOperator1(params, span)
    case JsSeq(JsInt(2), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      MultiplicativeOperator2(params, span)
    case _ => throw InvalidAST
  }
}

case class MultiplicativeOperator0(parserParams: List[Boolean], span: Span) extends MultiplicativeOperator {
  val idx: Int = 0
  override def toString: String = {
    s"*"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = MultiplicativeOperator0
}
object MultiplicativeOperator0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class MultiplicativeOperator1(parserParams: List[Boolean], span: Span) extends MultiplicativeOperator {
  val idx: Int = 1
  override def toString: String = {
    s"/"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = MultiplicativeOperator1
}
object MultiplicativeOperator1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class MultiplicativeOperator2(parserParams: List[Boolean], span: Span) extends MultiplicativeOperator {
  val idx: Int = 2
  override def toString: String = {
    s"%"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = MultiplicativeOperator2
}
object MultiplicativeOperator2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}
