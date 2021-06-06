package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait ClassExpression extends AST {
  val kind: String = "ClassExpression"
}

case class ClassExpression0(x1: Option[BindingIdentifier], x2: ClassTail, parserParams: List[Boolean]) extends ClassExpression {
  x1.foreach((m) => m.parent = Some(this))
  x2.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"class ${x1.getOrElse("")} $x2"
  }
  val k: Int = d(x2, d(x1, 0))
  val fullList: List[(String, Value)] = l("ClassTail", x2, l("Option[BindingIdentifier]", x1, Nil)).reverse
  val info: ASTInfo = ClassExpression0
}
object ClassExpression0 extends ASTInfo {
  val maxK: Int = 1
  val semMap: Map[String, Algo] = Map(
    "HasName0" -> `AL::ClassExpression[0,0].HasName`,
    "HasName1" -> `AL::ClassExpression[0,1].HasName`,
    "IsFunctionDefinition1" -> `AL::ClassExpression[0,1].IsFunctionDefinition`,
    "NamedEvaluation0" -> `AL::ClassExpression[0,0].NamedEvaluation`,
    "Evaluation0" -> `AL::ClassExpression[0,0].Evaluation`,
    "Evaluation1" -> `AL::ClassExpression[0,1].Evaluation`,
  )
}
