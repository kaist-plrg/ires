package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ClassBody extends AST {
  val kind: String = "ClassBody"
}
object ClassBody extends ASTHelper {
  def apply(v: JsValue): ClassBody = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      ClassBody0(ClassElementList(x0), params, span)
    case _ => throw InvalidAST
  }
}

case class ClassBody0(x0: ClassElementList, parserParams: List[Boolean], span: Span) extends ClassBody {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ClassElementList", x0, Nil).reverse
  val info: ASTInfo = ClassBody0
}
object ClassBody0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "EarlyErrors0" -> `AL::ClassBody[0,0].EarlyErrors`,
  )
}
