package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ScriptBody extends AST {
  val kind: String = "ScriptBody"
}
object ScriptBody extends ASTHelper {
  def apply(v: JsValue): ScriptBody = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      ScriptBody0(StatementList(x0), params)
    case _ => throw InvalidAST
  }
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
