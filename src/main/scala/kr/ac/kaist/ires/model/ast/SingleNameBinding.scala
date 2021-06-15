package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait SingleNameBinding extends AST {
  val kind: String = "SingleNameBinding"
}
object SingleNameBinding extends ASTHelper {
  def apply(v: JsValue): SingleNameBinding = v match {
    case JsSeq(JsInt(0), JsSeq(x0, x1), JsBoolSeq(params), JsSpan(span)) =>
      SingleNameBinding0(BindingIdentifier(x0), opt(x1, Initializer.apply), params, span)
    case _ => throw InvalidAST
  }
}

case class SingleNameBinding0(x0: BindingIdentifier, x1: Option[Initializer], parserParams: List[Boolean], span: Span) extends SingleNameBinding {
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
