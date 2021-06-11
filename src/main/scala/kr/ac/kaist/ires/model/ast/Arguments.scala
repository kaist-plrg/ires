package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait Arguments extends AST {
  val kind: String = "Arguments"
}
object Arguments extends ASTHelper {
  def apply(v: JsValue): Arguments = v match {
    case JsSeq(JsInt(0), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      Arguments0(params)
    case JsSeq(JsInt(1), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      Arguments1(ArgumentList(x1), params)
    case JsSeq(JsInt(2), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      Arguments2(ArgumentList(x1), params)
    case _ => throw InvalidAST
  }
}

case class Arguments0(parserParams: List[Boolean]) extends Arguments {
  val idx: Int = 0
  override def toString: String = {
    s"( )"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = Arguments0
}
object Arguments0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ArgumentListEvaluation0" -> `AL::Arguments[0,0].ArgumentListEvaluation`,
  )
}

case class Arguments1(x1: ArgumentList, parserParams: List[Boolean]) extends Arguments {
  x1.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"( $x1 )"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("ArgumentList", x1, Nil).reverse
  val info: ASTInfo = Arguments1
}
object Arguments1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class Arguments2(x1: ArgumentList, parserParams: List[Boolean]) extends Arguments {
  x1.parent = Some(this)
  val idx: Int = 2
  override def toString: String = {
    s"( $x1 , )"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("ArgumentList", x1, Nil).reverse
  val info: ASTInfo = Arguments2
}
object Arguments2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}
