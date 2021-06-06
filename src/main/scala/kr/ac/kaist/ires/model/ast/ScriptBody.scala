package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait ScriptBody extends AST {
  val kind: String = "ScriptBody"
}

case class ScriptBody0(x0: StatementList, parserParams: List[Boolean]) extends ScriptBody {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("StatementList", x0, Nil).reverse
  val info: ASTInfo = ScriptBody0
}
object ScriptBody0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "LexicallyDeclaredNames0" -> `AL::ScriptBody[0,0].LexicallyDeclaredNames`,
    "LexicallyScopedDeclarations0" -> `AL::ScriptBody[0,0].LexicallyScopedDeclarations`,
    "VarDeclaredNames0" -> `AL::ScriptBody[0,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::ScriptBody[0,0].VarScopedDeclarations`,
    "EarlyErrors0" -> `AL::ScriptBody[0,0].EarlyErrors`,
  )
}
