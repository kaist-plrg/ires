package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait ArrowParameters extends AST {
  val kind: String = "ArrowParameters"
}

case class ArrowParameters0(x0: BindingIdentifier, parserParams: List[Boolean]) extends ArrowParameters {
  x0.parent = Some(this)
  val name: String = "ArrowParameters0"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("BindingIdentifier", x0, Nil).reverse
  val info: ASTInfo = ArrowParameters0
}
object ArrowParameters0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IteratorBindingInitialization0" -> `AL::ArrowParameters[0,0].IteratorBindingInitialization`,
    "ContainsExpression0" -> `AL::ArrowParameters[0,0].ContainsExpression`,
    "IsSimpleParameterList0" -> `AL::ArrowParameters[0,0].IsSimpleParameterList`,
    "ExpectedArgumentCount0" -> `AL::ArrowParameters[0,0].ExpectedArgumentCount`,
    "CoveredFormalsList0" -> `AL::ArrowParameters[0,0].CoveredFormalsList`,
  )
}

case class ArrowParameters1(x0: CoverParenthesizedExpressionAndArrowParameterList, parserParams: List[Boolean]) extends ArrowParameters {
  x0.parent = Some(this)
  val name: String = "ArrowParameters1"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("CoverParenthesizedExpressionAndArrowParameterList", x0, Nil).reverse
  val info: ASTInfo = ArrowParameters1
}
object ArrowParameters1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::ArrowParameters[1,0].BoundNames`,
    "Contains0" -> `AL::ArrowParameters[1,0].Contains`,
    "IteratorBindingInitialization0" -> `AL::ArrowParameters[1,0].IteratorBindingInitialization`,
    "ContainsExpression0" -> `AL::ArrowParameters[1,0].ContainsExpression`,
    "IsSimpleParameterList0" -> `AL::ArrowParameters[1,0].IsSimpleParameterList`,
    "ExpectedArgumentCount0" -> `AL::ArrowParameters[1,0].ExpectedArgumentCount`,
    "EarlyErrors0" -> `AL::ArrowParameters[1,0].EarlyErrors`,
  )
}
