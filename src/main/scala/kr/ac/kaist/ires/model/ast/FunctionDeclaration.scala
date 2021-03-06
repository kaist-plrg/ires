package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait FunctionDeclaration extends AST {
  val kind: String = "FunctionDeclaration"
}
object FunctionDeclaration extends ASTHelper {
  def apply(v: JsValue): FunctionDeclaration = v match {
    case JsSeq(JsInt(0), JsSeq(x1, x3, x6), JsBoolSeq(params), JsSpan(span)) =>
      FunctionDeclaration0(BindingIdentifier(x1), FormalParameters(x3), FunctionBody(x6), params, span)
    case JsSeq(JsInt(1), JsSeq(x2, x5), JsBoolSeq(params), JsSpan(span)) =>
      FunctionDeclaration1(FormalParameters(x2), FunctionBody(x5), params, span)
    case _ => throw InvalidAST
  }
}

case class FunctionDeclaration0(x1: BindingIdentifier, x3: FormalParameters, x6: FunctionBody, parserParams: List[Boolean], span: Span) extends FunctionDeclaration {
  x1.parent = Some(this)
  x3.parent = Some(this)
  x6.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"function $x1 ( $x3 ) { $x6 }"
  }
  val k: Int = d(x6, d(x3, d(x1, 0)))
  val fullList: List[(String, Value)] = l("FunctionBody", x6, l("FormalParameters", x3, l("BindingIdentifier", x1, Nil))).reverse
  val info: ASTInfo = FunctionDeclaration0
}
object FunctionDeclaration0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::FunctionDeclaration[0,0].BoundNames`,
    "IsConstantDeclaration0" -> `AL::FunctionDeclaration[0,0].IsConstantDeclaration`,
    "Contains0" -> `AL::FunctionDeclaration[0,0].Contains`,
    "InstantiateFunctionObject0" -> `AL::FunctionDeclaration[0,0].InstantiateFunctionObject`,
    "InstantiateOrdinaryFunctionObject0" -> `AL::FunctionDeclaration[0,0].InstantiateOrdinaryFunctionObject`,
    "Evaluation0" -> `AL::FunctionDeclaration[0,0].Evaluation`,
    "EarlyErrors0" -> `AL::FunctionDeclaration[0,0].EarlyErrors`,
  )
}

case class FunctionDeclaration1(x2: FormalParameters, x5: FunctionBody, parserParams: List[Boolean], span: Span) extends FunctionDeclaration {
  x2.parent = Some(this)
  x5.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"function ( $x2 ) { $x5 }"
  }
  val k: Int = d(x5, d(x2, 0))
  val fullList: List[(String, Value)] = l("FunctionBody", x5, l("FormalParameters", x2, Nil)).reverse
  val info: ASTInfo = FunctionDeclaration1
}
object FunctionDeclaration1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::FunctionDeclaration[1,0].BoundNames`,
    "IsConstantDeclaration0" -> `AL::FunctionDeclaration[1,0].IsConstantDeclaration`,
    "Contains0" -> `AL::FunctionDeclaration[1,0].Contains`,
    "InstantiateFunctionObject0" -> `AL::FunctionDeclaration[1,0].InstantiateFunctionObject`,
    "InstantiateOrdinaryFunctionObject0" -> `AL::FunctionDeclaration[1,0].InstantiateOrdinaryFunctionObject`,
    "Evaluation0" -> `AL::FunctionDeclaration[1,0].Evaluation`,
    "EarlyErrors0" -> `AL::FunctionDeclaration[1,0].EarlyErrors`,
  )
}
