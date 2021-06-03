package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait Identifier extends AST {
  val kind: String = "Identifier"
}

case class Identifier0(x0: Lexical, parserParams: List[Boolean]) extends Identifier {
  x0.parent = Some(this)
  val name: String = "Identifier0"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("Lexical", x0, Nil).reverse
  val info: ASTInfo = Identifier0
}
object Identifier0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "StringValue0" -> `AL::Identifier[0,0].StringValue`,
    "EarlyErrors0" -> `AL::Identifier[0,0].EarlyErrors`,
  )
}
