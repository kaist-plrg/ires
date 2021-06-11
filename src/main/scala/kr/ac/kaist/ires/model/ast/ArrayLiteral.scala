package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ArrayLiteral extends AST {
  val kind: String = "ArrayLiteral"
}
object ArrayLiteral extends ASTHelper {
  def apply(v: JsValue): ArrayLiteral = v match {
    case JsSeq(JsInt(0), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      ArrayLiteral0(opt(x1, Elision.apply), params)
    case JsSeq(JsInt(1), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      ArrayLiteral1(ElementList(x1), params)
    case JsSeq(JsInt(2), JsSeq(x1, x3), JsBoolSeq(params), JsSpan(span)) =>
      ArrayLiteral2(ElementList(x1), opt(x3, Elision.apply), params)
    case _ => throw InvalidAST
  }
}

case class ArrayLiteral0(x1: Option[Elision], parserParams: List[Boolean]) extends ArrayLiteral {
  x1.foreach((m) => m.parent = Some(this))
  val idx: Int = 0
  override def toString: String = {
    s"[ ${x1.getOrElse("")} ]"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("Option[Elision]", x1, Nil).reverse
  val info: ASTInfo = ArrayLiteral0
}
object ArrayLiteral0 extends ASTInfo {
  val maxK: Int = 1
  val semMap: Map[String, Algo] = Map(
    "Evaluation1" -> `AL::ArrayLiteral[0,1].Evaluation`,
  )
}

case class ArrayLiteral1(x1: ElementList, parserParams: List[Boolean]) extends ArrayLiteral {
  x1.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"[ $x1 ]"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("ElementList", x1, Nil).reverse
  val info: ASTInfo = ArrayLiteral1
}
object ArrayLiteral1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "Evaluation0" -> `AL::ArrayLiteral[1,0].Evaluation`,
  )
}

case class ArrayLiteral2(x1: ElementList, x3: Option[Elision], parserParams: List[Boolean]) extends ArrayLiteral {
  x1.parent = Some(this)
  x3.foreach((m) => m.parent = Some(this))
  val idx: Int = 2
  override def toString: String = {
    s"[ $x1 , ${x3.getOrElse("")} ]"
  }
  val k: Int = d(x3, d(x1, 0))
  val fullList: List[(String, Value)] = l("Option[Elision]", x3, l("ElementList", x1, Nil)).reverse
  val info: ASTInfo = ArrayLiteral2
}
object ArrayLiteral2 extends ASTInfo {
  val maxK: Int = 1
  val semMap: Map[String, Algo] = Map(
    "Evaluation1" -> `AL::ArrayLiteral[2,1].Evaluation`,
  )
}
