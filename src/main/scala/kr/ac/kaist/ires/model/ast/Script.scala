package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait Script extends AST {
  val kind: String = "Script"
}
object Script extends ASTHelper {
  def apply(v: JsValue): Script = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      Script0(opt(x0, ScriptBody.apply), params, span)
    case _ => throw InvalidAST
  }
}

case class Script0(x0: Option[ScriptBody], parserParams: List[Boolean], span: Span) extends Script {
  x0.foreach((m) => m.parent = Some(this))
  val idx: Int = 0
  override def toString: String = {
    s"${x0.getOrElse("")}"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("Option[ScriptBody]", x0, Nil).reverse
  val info: ASTInfo = Script0
}
object Script0 extends ASTInfo {
  val maxK: Int = 1
  val semMap: Map[String, Algo] = Map(
    "IsStrict1" -> `AL::Script[0,1].IsStrict`,
    "Evaluation0" -> `AL::Script[0,0].Evaluation`,
    "EarlyErrors1" -> `AL::Script[0,1].EarlyErrors`,
  )
}
