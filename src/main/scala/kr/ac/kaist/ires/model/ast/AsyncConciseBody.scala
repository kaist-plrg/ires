package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait AsyncConciseBody extends AST {
  val kind: String = "AsyncConciseBody"
}

case class AsyncConciseBody0(x1: ExpressionBody, parserParams: List[Boolean]) extends AsyncConciseBody {
  x1.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("ExpressionBody", x1, Nil).reverse
  val info: ASTInfo = AsyncConciseBody0
}
object AsyncConciseBody0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "LexicallyDeclaredNames0" -> `AL::AsyncConciseBody[0,0].LexicallyDeclaredNames`,
    "LexicallyScopedDeclarations0" -> `AL::AsyncConciseBody[0,0].LexicallyScopedDeclarations`,
    "VarDeclaredNames0" -> `AL::AsyncConciseBody[0,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::AsyncConciseBody[0,0].VarScopedDeclarations`,
    "EvaluateBody0" -> `AL::AsyncConciseBody[0,0].EvaluateBody`,
    "AsyncConciseBodyContainsUseStrict0" -> `AL::AsyncConciseBody[0,0].AsyncConciseBodyContainsUseStrict`,
    "EvaluateAsyncConciseBody0" -> `AL::AsyncConciseBody[0,0].EvaluateAsyncConciseBody`,
  )
}

case class AsyncConciseBody1(x1: AsyncFunctionBody, parserParams: List[Boolean]) extends AsyncConciseBody {
  x1.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"{ $x1 }"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("AsyncFunctionBody", x1, Nil).reverse
  val info: ASTInfo = AsyncConciseBody1
}
object AsyncConciseBody1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "AsyncConciseBodyContainsUseStrict0" -> `AL::AsyncConciseBody[1,0].AsyncConciseBodyContainsUseStrict`,
  )
}
