package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ClassHeritage extends AST {
  val kind: String = "ClassHeritage"
}
object ClassHeritage extends ASTHelper {
  def apply(v: JsValue): ClassHeritage = v match {
    case JsSeq(JsInt(0), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      ClassHeritage0(LeftHandSideExpression(x1), params)
    case _ => throw InvalidAST
  }
}

case class ClassHeritage0(x1: LeftHandSideExpression, parserParams: List[Boolean]) extends ClassHeritage {
  x1.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"extends $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("LeftHandSideExpression", x1, Nil).reverse
  val info: ASTInfo = ClassHeritage0
}
object ClassHeritage0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}
