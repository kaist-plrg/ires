package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ForDeclaration extends AST {
  val kind: String = "ForDeclaration"
}
object ForDeclaration extends ASTHelper {
  def apply(v: JsValue): ForDeclaration = v match {
    case JsSeq(JsInt(0), JsSeq(x0, x1), JsBoolSeq(params), JsSpan(span)) =>
      ForDeclaration0(LetOrConst(x0), ForBinding(x1), params)
    case _ => throw InvalidAST
  }
}

case class ForDeclaration0(x0: LetOrConst, x1: ForBinding, parserParams: List[Boolean]) extends ForDeclaration {
  x0.parent = Some(this)
  x1.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0 $x1"
  }
  val k: Int = d(x1, d(x0, 0))
  val fullList: List[(String, Value)] = l("ForBinding", x1, l("LetOrConst", x0, Nil)).reverse
  val info: ASTInfo = ForDeclaration0
}
object ForDeclaration0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::ForDeclaration[0,0].BoundNames`,
    "IsDestructuring0" -> `AL::ForDeclaration[0,0].IsDestructuring`,
    "ForDeclarationBindingInitialization0" -> `AL::ForDeclaration[0,0].ForDeclarationBindingInitialization`,
    "ForDeclarationBindingInstantiation0" -> `AL::ForDeclaration[0,0].ForDeclarationBindingInstantiation`,
  )
}
