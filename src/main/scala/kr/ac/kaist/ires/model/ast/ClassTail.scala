package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ClassTail extends AST {
  val kind: String = "ClassTail"
}
object ClassTail extends ASTHelper {
  def apply(v: JsValue): ClassTail = v match {
    case JsSeq(JsInt(0), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      ClassTail0(opt(x0, ClassHeritage.apply), opt(x2, ClassBody.apply), params)
    case _ => throw InvalidAST
  }
}

case class ClassTail0(x0: Option[ClassHeritage], x2: Option[ClassBody], parserParams: List[Boolean]) extends ClassTail {
  x0.foreach((m) => m.parent = Some(this))
  x2.foreach((m) => m.parent = Some(this))
  val idx: Int = 0
  override def toString: String = {
    s"${x0.getOrElse("")} { ${x2.getOrElse("")} }"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("Option[ClassBody]", x2, l("Option[ClassHeritage]", x0, Nil)).reverse
  val info: ASTInfo = ClassTail0
}
object ClassTail0 extends ASTInfo {
  val maxK: Int = 3
  val semMap: Map[String, Algo] = Map(
    "Contains3" -> `AL::ClassTail[0,3].Contains`,
    "ClassDefinitionEvaluation3" -> `AL::ClassTail[0,3].ClassDefinitionEvaluation`,
  )
}
