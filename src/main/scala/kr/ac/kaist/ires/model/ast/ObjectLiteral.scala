package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ObjectLiteral extends AST {
  val kind: String = "ObjectLiteral"
}
object ObjectLiteral extends ASTHelper {
  def apply(v: JsValue): ObjectLiteral = v match {
    case JsSeq(JsInt(0), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      ObjectLiteral0(params)
    case JsSeq(JsInt(1), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      ObjectLiteral1(PropertyDefinitionList(x1), params)
    case JsSeq(JsInt(2), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      ObjectLiteral2(PropertyDefinitionList(x1), params)
    case _ => throw InvalidAST
  }
}

case class ObjectLiteral0(parserParams: List[Boolean]) extends ObjectLiteral {
  val idx: Int = 0
  override def toString: String = {
    s"{ }"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = ObjectLiteral0
}
object ObjectLiteral0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "Evaluation0" -> `AL::ObjectLiteral[0,0].Evaluation`,
  )
}

case class ObjectLiteral1(x1: PropertyDefinitionList, parserParams: List[Boolean]) extends ObjectLiteral {
  x1.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"{ $x1 }"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("PropertyDefinitionList", x1, Nil).reverse
  val info: ASTInfo = ObjectLiteral1
}
object ObjectLiteral1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "Evaluation0" -> `AL::ObjectLiteral[1,0].Evaluation`,
  )
}

case class ObjectLiteral2(x1: PropertyDefinitionList, parserParams: List[Boolean]) extends ObjectLiteral {
  x1.parent = Some(this)
  val idx: Int = 2
  override def toString: String = {
    s"{ $x1 , }"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("PropertyDefinitionList", x1, Nil).reverse
  val info: ASTInfo = ObjectLiteral2
}
object ObjectLiteral2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "Evaluation0" -> `AL::ObjectLiteral[2,0].Evaluation`,
  )
}
