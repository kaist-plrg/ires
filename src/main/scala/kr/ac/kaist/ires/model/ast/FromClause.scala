package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait FromClause extends AST {
  val kind: String = "FromClause"
}
object FromClause extends ASTHelper {
  def apply(v: JsValue): FromClause = v match {
    case JsSeq(JsInt(0), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      FromClause0(ModuleSpecifier(x1), params)
    case _ => throw InvalidAST
  }
}

case class FromClause0(x1: ModuleSpecifier, parserParams: List[Boolean]) extends FromClause {
  x1.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"from $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("ModuleSpecifier", x1, Nil).reverse
  val info: ASTInfo = FromClause0
}
object FromClause0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}
