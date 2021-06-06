package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait BindingIdentifier extends AST {
  val kind: String = "BindingIdentifier"
}

case class BindingIdentifier0(x0: Identifier, parserParams: List[Boolean]) extends BindingIdentifier {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("Identifier", x0, Nil).reverse
  val info: ASTInfo = BindingIdentifier0
}
object BindingIdentifier0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::BindingIdentifier[0,0].BoundNames`,
    "BindingInitialization0" -> `AL::BindingIdentifier[0,0].BindingInitialization`,
    "EarlyErrors0" -> `AL::BindingIdentifier[0,0].EarlyErrors`,
    "EarlyErrors0" -> `AL::BindingIdentifier[0,0].EarlyErrors`,
  )
}

case class BindingIdentifier1(parserParams: List[Boolean]) extends BindingIdentifier {
  val idx: Int = 1
  override def toString: String = {
    s"yield"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = BindingIdentifier1
}
object BindingIdentifier1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::BindingIdentifier[1,0].BoundNames`,
    "BindingInitialization0" -> `AL::BindingIdentifier[1,0].BindingInitialization`,
    "StringValue0" -> `AL::BindingIdentifier[1,0].StringValue`,
    "EarlyErrors0" -> `AL::BindingIdentifier[1,0].EarlyErrors`,
    "EarlyErrors0" -> `AL::BindingIdentifier[1,0].EarlyErrors`,
  )
}

case class BindingIdentifier2(parserParams: List[Boolean]) extends BindingIdentifier {
  val idx: Int = 2
  override def toString: String = {
    s"await"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = BindingIdentifier2
}
object BindingIdentifier2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::BindingIdentifier[2,0].BoundNames`,
    "BindingInitialization0" -> `AL::BindingIdentifier[2,0].BindingInitialization`,
    "StringValue0" -> `AL::BindingIdentifier[2,0].StringValue`,
    "EarlyErrors0" -> `AL::BindingIdentifier[2,0].EarlyErrors`,
    "EarlyErrors0" -> `AL::BindingIdentifier[2,0].EarlyErrors`,
  )
}
