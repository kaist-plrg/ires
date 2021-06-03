package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait Module extends AST {
  val kind: String = "Module"
}

case class Module0(x0: Option[ModuleBody], parserParams: List[Boolean]) extends Module {
  x0.foreach((m) => m.parent = Some(this))
  val name: String = "Module0"
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
