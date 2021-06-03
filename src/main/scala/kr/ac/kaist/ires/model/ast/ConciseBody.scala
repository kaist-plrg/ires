package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait ConciseBody extends AST {
  val kind: String = "ConciseBody"
}

case class ConciseBody0(x1: ExpressionBody, parserParams: List[Boolean]) extends ConciseBody {
  x1.parent = Some(this)
  val name: String = "ConciseBody0"
  override def toString: String = {
    s"$x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("ExpressionBody", x1, Nil).reverse
  val info: ASTInfo = ConciseBody0
}
object ConciseBody0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "LexicallyDeclaredNames0" -> `AL::ConciseBody[0,0].LexicallyDeclaredNames`,
    "LexicallyScopedDeclarations0" -> `AL::ConciseBody[0,0].LexicallyScopedDeclarations`,
    "VarDeclaredNames0" -> `AL::ConciseBody[0,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::ConciseBody[0,0].VarScopedDeclarations`,
    "EvaluateBody0" -> `AL::ConciseBody[0,0].EvaluateBody`,
    "ConciseBodyContainsUseStrict0" -> `AL::ConciseBody[0,0].ConciseBodyContainsUseStrict`,
    "EvaluateConciseBody0" -> `AL::ConciseBody[0,0].EvaluateConciseBody`,
  )
}

case class ConciseBody1(x1: FunctionBody, parserParams: List[Boolean]) extends ConciseBody {
  x1.parent = Some(this)
  val name: String = "ConciseBody1"
  override def toString: String = {
    s"{ $x1 }"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("FunctionBody", x1, Nil).reverse
  val info: ASTInfo = ConciseBody1
}
object ConciseBody1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ConciseBodyContainsUseStrict0" -> `AL::ConciseBody[1,0].ConciseBodyContainsUseStrict`,
  )
}
