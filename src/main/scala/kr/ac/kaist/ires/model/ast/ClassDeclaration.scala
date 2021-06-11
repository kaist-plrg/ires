package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ClassDeclaration extends AST {
  val kind: String = "ClassDeclaration"
}
object ClassDeclaration extends ASTHelper {
  def apply(v: JsValue): ClassDeclaration = v match {
    case JsSeq(JsInt(0), JsSeq(x1, x2), JsBoolSeq(params), JsSpan(span)) =>
      ClassDeclaration0(BindingIdentifier(x1), ClassTail(x2), params)
    case JsSeq(JsInt(1), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      ClassDeclaration1(ClassTail(x1), params)
    case _ => throw InvalidAST
  }
}

case class ClassDeclaration0(x1: BindingIdentifier, x2: ClassTail, parserParams: List[Boolean]) extends ClassDeclaration {
  x1.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"class $x1 $x2"
  }
  val k: Int = d(x2, d(x1, 0))
  val fullList: List[(String, Value)] = l("ClassTail", x2, l("BindingIdentifier", x1, Nil)).reverse
  val info: ASTInfo = ClassDeclaration0
}
object ClassDeclaration0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::ClassDeclaration[0,0].BoundNames`,
    "IsConstantDeclaration0" -> `AL::ClassDeclaration[0,0].IsConstantDeclaration`,
    "BindingClassDeclarationEvaluation0" -> `AL::ClassDeclaration[0,0].BindingClassDeclarationEvaluation`,
    "Evaluation0" -> `AL::ClassDeclaration[0,0].Evaluation`,
  )
}

case class ClassDeclaration1(x1: ClassTail, parserParams: List[Boolean]) extends ClassDeclaration {
  x1.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"class $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("ClassTail", x1, Nil).reverse
  val info: ASTInfo = ClassDeclaration1
}
object ClassDeclaration1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::ClassDeclaration[1,0].BoundNames`,
    "IsConstantDeclaration0" -> `AL::ClassDeclaration[1,0].IsConstantDeclaration`,
    "BindingClassDeclarationEvaluation0" -> `AL::ClassDeclaration[1,0].BindingClassDeclarationEvaluation`,
  )
}
