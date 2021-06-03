package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait ImportMeta extends AST {
  val kind: String = "ImportMeta"
}

case class ImportMeta0(parserParams: List[Boolean]) extends ImportMeta {
  val name: String = "ImportMeta0"
  override def toString: String = {
    s"import . meta"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = ImportMeta0
}
object ImportMeta0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "AssignmentTargetType0" -> `AL::ImportMeta[0,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::ImportMeta[0,0].Evaluation`,
    "EarlyErrors0" -> `AL::ImportMeta[0,0].EarlyErrors`,
  )
}
