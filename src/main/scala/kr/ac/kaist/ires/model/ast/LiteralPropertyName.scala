package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait LiteralPropertyName extends AST {
  val kind: String = "LiteralPropertyName"
}

case class LiteralPropertyName0(x0: Lexical, parserParams: List[Boolean]) extends LiteralPropertyName {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("Lexical", x0, Nil).reverse
  val info: ASTInfo = LiteralPropertyName0
}
object LiteralPropertyName0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "Contains0" -> `AL::LiteralPropertyName[0,0].Contains`,
    "PropName0" -> `AL::LiteralPropertyName[0,0].PropName`,
    "Evaluation0" -> `AL::LiteralPropertyName[0,0].Evaluation`,
  )
}

case class LiteralPropertyName1(x0: Lexical, parserParams: List[Boolean]) extends LiteralPropertyName {
  x0.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("Lexical", x0, Nil).reverse
  val info: ASTInfo = LiteralPropertyName1
}
object LiteralPropertyName1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "PropName0" -> `AL::LiteralPropertyName[1,0].PropName`,
    "Evaluation0" -> `AL::LiteralPropertyName[1,0].Evaluation`,
  )
}

case class LiteralPropertyName2(x0: Lexical, parserParams: List[Boolean]) extends LiteralPropertyName {
  x0.parent = Some(this)
  val idx: Int = 2
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("Lexical", x0, Nil).reverse
  val info: ASTInfo = LiteralPropertyName2
}
object LiteralPropertyName2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "PropName0" -> `AL::LiteralPropertyName[2,0].PropName`,
    "Evaluation0" -> `AL::LiteralPropertyName[2,0].Evaluation`,
  )
}
