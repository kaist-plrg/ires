package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ImportMeta extends AST {
  val kind: String = "ImportMeta"
}
object ImportMeta extends ASTHelper {
  def apply(v: JsValue): ImportMeta = v match {
    case JsSeq(JsInt(0), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      ImportMeta0(params)
    case _ => throw InvalidAST
  }
}

case class ImportMeta0(parserParams: List[Boolean]) extends ImportMeta {
  val idx: Int = 0
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
