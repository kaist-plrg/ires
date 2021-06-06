package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait ImportCall extends AST {
  val kind: String = "ImportCall"
}

case class ImportCall0(x2: AssignmentExpression, parserParams: List[Boolean]) extends ImportCall {
  x2.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"import ( $x2 )"
  }
  val k: Int = d(x2, 0)
  val fullList: List[(String, Value)] = l("AssignmentExpression", x2, Nil).reverse
  val info: ASTInfo = ImportCall0
}
object ImportCall0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "Evaluation0" -> `AL::ImportCall[0,0].Evaluation`,
  )
}
