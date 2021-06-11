package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait Module extends AST {
  val kind: String = "Module"
}
object Module extends ASTHelper {
  def apply(v: JsValue): Module = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      Module0(opt(x0, ModuleBody.apply), params, span)
    case _ => throw InvalidAST
  }
}

case class Module0(x0: Option[ModuleBody], parserParams: List[Boolean], span: Span) extends Module {
  x0.foreach((m) => m.parent = Some(this))
  val idx: Int = 0
  override def toString: String = {
    s"${x0.getOrElse("")}"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("Option[ModuleBody]", x0, Nil).reverse
  val info: ASTInfo = Module0
}
object Module0 extends ASTInfo {
  val maxK: Int = 1
  val semMap: Map[String, Algo] = Map(
    "LexicallyScopedDeclarations0" -> `AL::Module[0,0].LexicallyScopedDeclarations`,
    "VarDeclaredNames0" -> `AL::Module[0,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::Module[0,0].VarScopedDeclarations`,
    "ModuleRequests0" -> `AL::Module[0,0].ModuleRequests`,
    "Evaluation0" -> `AL::Module[0,0].Evaluation`,
    "ImportEntries0" -> `AL::Module[0,0].ImportEntries`,
    "ExportEntries0" -> `AL::Module[0,0].ExportEntries`,
  )
}
