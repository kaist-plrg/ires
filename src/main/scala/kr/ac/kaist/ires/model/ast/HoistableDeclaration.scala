package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait HoistableDeclaration extends AST {
  val kind: String = "HoistableDeclaration"
}

case class HoistableDeclaration0(x0: FunctionDeclaration, parserParams: List[Boolean]) extends HoistableDeclaration {
  x0.parent = Some(this)
  val name: String = "HoistableDeclaration0"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("FunctionDeclaration", x0, Nil).reverse
  val info: ASTInfo = HoistableDeclaration0
}
object HoistableDeclaration0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "DeclarationPart0" -> `AL::HoistableDeclaration[0,0].DeclarationPart`,
    "Evaluation0" -> `AL::HoistableDeclaration[0,0].Evaluation`,
  )
}

case class HoistableDeclaration1(x0: GeneratorDeclaration, parserParams: List[Boolean]) extends HoistableDeclaration {
  x0.parent = Some(this)
  val name: String = "HoistableDeclaration1"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("GeneratorDeclaration", x0, Nil).reverse
  val info: ASTInfo = HoistableDeclaration1
}
object HoistableDeclaration1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "DeclarationPart0" -> `AL::HoistableDeclaration[1,0].DeclarationPart`,
    "Evaluation0" -> `AL::HoistableDeclaration[1,0].Evaluation`,
  )
}

case class HoistableDeclaration2(x0: AsyncFunctionDeclaration, parserParams: List[Boolean]) extends HoistableDeclaration {
  x0.parent = Some(this)
  val name: String = "HoistableDeclaration2"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("AsyncFunctionDeclaration", x0, Nil).reverse
  val info: ASTInfo = HoistableDeclaration2
}
object HoistableDeclaration2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "DeclarationPart0" -> `AL::HoistableDeclaration[2,0].DeclarationPart`,
    "Evaluation0" -> `AL::HoistableDeclaration[2,0].Evaluation`,
  )
}

case class HoistableDeclaration3(x0: AsyncGeneratorDeclaration, parserParams: List[Boolean]) extends HoistableDeclaration {
  x0.parent = Some(this)
  val name: String = "HoistableDeclaration3"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("AsyncGeneratorDeclaration", x0, Nil).reverse
  val info: ASTInfo = HoistableDeclaration3
}
object HoistableDeclaration3 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "DeclarationPart0" -> `AL::HoistableDeclaration[3,0].DeclarationPart`,
    "Evaluation0" -> `AL::HoistableDeclaration[3,0].Evaluation`,
  )
}
