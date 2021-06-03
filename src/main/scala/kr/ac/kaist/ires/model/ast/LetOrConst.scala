package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait LetOrConst extends AST {
  val kind: String = "LetOrConst"
}

case class LetOrConst0(parserParams: List[Boolean]) extends LetOrConst {
  val name: String = "LetOrConst0"
  override def toString: String = {
    s"let"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = LetOrConst0
}
object LetOrConst0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsConstantDeclaration0" -> `AL::LetOrConst[0,0].IsConstantDeclaration`,
  )
}

case class LetOrConst1(parserParams: List[Boolean]) extends LetOrConst {
  val name: String = "LetOrConst1"
  override def toString: String = {
    s"const"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = LetOrConst1
}
object LetOrConst1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsConstantDeclaration0" -> `AL::LetOrConst[1,0].IsConstantDeclaration`,
  )
}
