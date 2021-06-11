package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ModuleBody extends AST {
  val kind: String = "ModuleBody"
}
object ModuleBody extends ASTHelper {
  def apply(v: JsValue): ModuleBody = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      ModuleBody0(ModuleItemList(x0), params, span)
    case _ => throw InvalidAST
  }
}

case class ModuleBody0(x0: ModuleItemList, parserParams: List[Boolean], span: Span) extends ModuleBody {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ModuleItemList", x0, Nil).reverse
  val info: ASTInfo = ModuleBody0
}
object ModuleBody0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "Evaluation0" -> `AL::ModuleBody[0,0].Evaluation`,
    "EarlyErrors0" -> `AL::ModuleBody[0,0].EarlyErrors`,
  )
}
