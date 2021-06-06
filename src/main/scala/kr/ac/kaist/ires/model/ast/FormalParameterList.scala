package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait FormalParameterList extends AST {
  val kind: String = "FormalParameterList"
}

case class FormalParameterList0(x0: FormalParameter, parserParams: List[Boolean]) extends FormalParameterList {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("FormalParameter", x0, Nil).reverse
  val info: ASTInfo = FormalParameterList0
}
object FormalParameterList0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ExpectedArgumentCount0" -> `AL::FormalParameterList[0,0].ExpectedArgumentCount`,
  )
}

case class FormalParameterList1(x0: FormalParameterList, x2: FormalParameter, parserParams: List[Boolean]) extends FormalParameterList {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0 , $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("FormalParameter", x2, l("FormalParameterList", x0, Nil)).reverse
  val info: ASTInfo = FormalParameterList1
}
object FormalParameterList1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::FormalParameterList[1,0].BoundNames`,
    "IteratorBindingInitialization0" -> `AL::FormalParameterList[1,0].IteratorBindingInitialization`,
    "ContainsExpression0" -> `AL::FormalParameterList[1,0].ContainsExpression`,
    "IsSimpleParameterList0" -> `AL::FormalParameterList[1,0].IsSimpleParameterList`,
    "HasInitializer0" -> `AL::FormalParameterList[1,0].HasInitializer`,
    "ExpectedArgumentCount0" -> `AL::FormalParameterList[1,0].ExpectedArgumentCount`,
  )
}
