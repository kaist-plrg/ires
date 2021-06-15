package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ModuleSpecifier extends AST {
  val kind: String = "ModuleSpecifier"
}
object ModuleSpecifier extends ASTHelper {
  def apply(v: JsValue): ModuleSpecifier = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      ModuleSpecifier0(lex("StringLiteral", x0), params, span)
    case _ => throw InvalidAST
  }
}

case class ModuleSpecifier0(x0: Lexical, parserParams: List[Boolean], span: Span) extends ModuleSpecifier {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("Lexical", x0, Nil).reverse
  val info: ASTInfo = ModuleSpecifier0
}
object ModuleSpecifier0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ModuleRequests0" -> `AL::ModuleSpecifier[0,0].ModuleRequests`,
  )
}
