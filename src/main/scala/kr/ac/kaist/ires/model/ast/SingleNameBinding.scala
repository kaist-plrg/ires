package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait SingleNameBinding extends AST {
  val kind: String = "SingleNameBinding"
}

case class SingleNameBinding0(x0: BindingIdentifier, x1: Option[Initializer], parserParams: List[Boolean]) extends SingleNameBinding {
  x0.parent = Some(this)
  x1.foreach((m) => m.parent = Some(this))
  val idx: Int = 0
  override def toString: String = {
    s"$x0 ${x1.getOrElse("")}"
  }
  val k: Int = d(x1, d(x0, 0))
  val fullList: List[(String, Value)] = l("Option[Initializer]", x1, l("BindingIdentifier", x0, Nil)).reverse
  val info: ASTInfo = SingleNameBinding0
}
object SingleNameBinding0 extends ASTInfo {
  val maxK: Int = 1
  val semMap: Map[String, Algo] = Map(
    "BoundNames1" -> `AL::SingleNameBinding[0,1].BoundNames`,
    "IteratorBindingInitialization1" -> `AL::SingleNameBinding[0,1].IteratorBindingInitialization`,
    "KeyedBindingInitialization1" -> `AL::SingleNameBinding[0,1].KeyedBindingInitialization`,
    "ContainsExpression0" -> `AL::SingleNameBinding[0,0].ContainsExpression`,
    "ContainsExpression1" -> `AL::SingleNameBinding[0,1].ContainsExpression`,
    "IsSimpleParameterList0" -> `AL::SingleNameBinding[0,0].IsSimpleParameterList`,
    "IsSimpleParameterList1" -> `AL::SingleNameBinding[0,1].IsSimpleParameterList`,
    "HasInitializer0" -> `AL::SingleNameBinding[0,0].HasInitializer`,
    "HasInitializer1" -> `AL::SingleNameBinding[0,1].HasInitializer`,
  )
}
