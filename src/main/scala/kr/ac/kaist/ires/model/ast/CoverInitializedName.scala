package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait CoverInitializedName extends AST {
  val kind: String = "CoverInitializedName"
}
object CoverInitializedName extends ASTHelper {
  def apply(v: JsValue): CoverInitializedName = v match {
    case JsSeq(JsInt(0), JsSeq(x0, x1), JsBoolSeq(params), JsSpan(span)) =>
      CoverInitializedName0(IdentifierReference(x0), Initializer(x1), params, span)
    case _ => throw InvalidAST
  }
}

case class CoverInitializedName0(x0: IdentifierReference, x1: Initializer, parserParams: List[Boolean], span: Span) extends CoverInitializedName {
  x0.parent = Some(this)
  x1.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0 $x1"
  }
  val k: Int = d(x1, d(x0, 0))
  val fullList: List[(String, Value)] = l("Initializer", x1, l("IdentifierReference", x0, Nil)).reverse
  val info: ASTInfo = CoverInitializedName0
}
object CoverInitializedName0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}
