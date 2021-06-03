package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait FormalParameters extends AST {
  val kind: String = "FormalParameters"
}

case class FormalParameters0(parserParams: List[Boolean]) extends FormalParameters {
  val name: String = "FormalParameters0"
  override def toString: String = {
    s""
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = FormalParameters0
}
object FormalParameters0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::FormalParameters[0,0].BoundNames`,
    "IteratorBindingInitialization0" -> `AL::FormalParameters[0,0].IteratorBindingInitialization`,
    "ContainsExpression0" -> `AL::FormalParameters[0,0].ContainsExpression`,
    "IsSimpleParameterList0" -> `AL::FormalParameters[0,0].IsSimpleParameterList`,
    "ExpectedArgumentCount0" -> `AL::FormalParameters[0,0].ExpectedArgumentCount`,
  )
}

case class FormalParameters1(x0: FunctionRestParameter, parserParams: List[Boolean]) extends FormalParameters {
  x0.parent = Some(this)
  val name: String = "FormalParameters1"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("FunctionRestParameter", x0, Nil).reverse
  val info: ASTInfo = FormalParameters1
}
object FormalParameters1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsSimpleParameterList0" -> `AL::FormalParameters[1,0].IsSimpleParameterList`,
    "ExpectedArgumentCount0" -> `AL::FormalParameters[1,0].ExpectedArgumentCount`,
  )
}

case class FormalParameters2(x0: FormalParameterList, parserParams: List[Boolean]) extends FormalParameters {
  x0.parent = Some(this)
  val name: String = "FormalParameters2"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("FormalParameterList", x0, Nil).reverse
  val info: ASTInfo = FormalParameters2
}
object FormalParameters2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "EarlyErrors0" -> `AL::FormalParameters[2,0].EarlyErrors`,
  )
}

case class FormalParameters3(x0: FormalParameterList, parserParams: List[Boolean]) extends FormalParameters {
  x0.parent = Some(this)
  val name: String = "FormalParameters3"
  override def toString: String = {
    s"$x0 ,"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("FormalParameterList", x0, Nil).reverse
  val info: ASTInfo = FormalParameters3
}
object FormalParameters3 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class FormalParameters4(x0: FormalParameterList, x2: FunctionRestParameter, parserParams: List[Boolean]) extends FormalParameters {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val name: String = "FormalParameters4"
  override def toString: String = {
    s"$x0 , $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("FunctionRestParameter", x2, l("FormalParameterList", x0, Nil)).reverse
  val info: ASTInfo = FormalParameters4
}
object FormalParameters4 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::FormalParameters[4,0].BoundNames`,
    "IteratorBindingInitialization0" -> `AL::FormalParameters[4,0].IteratorBindingInitialization`,
    "ContainsExpression0" -> `AL::FormalParameters[4,0].ContainsExpression`,
    "IsSimpleParameterList0" -> `AL::FormalParameters[4,0].IsSimpleParameterList`,
    "ExpectedArgumentCount0" -> `AL::FormalParameters[4,0].ExpectedArgumentCount`,
  )
}
