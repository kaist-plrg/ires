package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait OptionalChain extends AST {
  val kind: String = "OptionalChain"
}
object OptionalChain extends ASTHelper {
  def apply(v: JsValue): OptionalChain = v match {
    case JsSeq(JsInt(0), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      OptionalChain0(Arguments(x1), params)
    case JsSeq(JsInt(1), JsSeq(x2), JsBoolSeq(params), JsSpan(span)) =>
      OptionalChain1(Expression(x2), params)
    case JsSeq(JsInt(2), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      OptionalChain2(lex("IdentifierName", x1), params)
    case JsSeq(JsInt(3), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      OptionalChain3(TemplateLiteral(x1), params)
    case JsSeq(JsInt(4), JsSeq(x0, x1), JsBoolSeq(params), JsSpan(span)) =>
      OptionalChain4(OptionalChain(x0), Arguments(x1), params)
    case JsSeq(JsInt(5), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      OptionalChain5(OptionalChain(x0), Expression(x2), params)
    case JsSeq(JsInt(6), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      OptionalChain6(OptionalChain(x0), lex("IdentifierName", x2), params)
    case JsSeq(JsInt(7), JsSeq(x0, x1), JsBoolSeq(params), JsSpan(span)) =>
      OptionalChain7(OptionalChain(x0), TemplateLiteral(x1), params)
    case _ => throw InvalidAST
  }
}

case class OptionalChain0(x1: Arguments, parserParams: List[Boolean]) extends OptionalChain {
  x1.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"?. $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("Arguments", x1, Nil).reverse
  val info: ASTInfo = OptionalChain0
}
object OptionalChain0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ChainEvaluation0" -> `AL::OptionalChain[0,0].ChainEvaluation`,
    "HasCallInTailPosition0" -> `AL::OptionalChain[0,0].HasCallInTailPosition`,
  )
}

case class OptionalChain1(x2: Expression, parserParams: List[Boolean]) extends OptionalChain {
  x2.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"?. [ $x2 ]"
  }
  val k: Int = d(x2, 0)
  val fullList: List[(String, Value)] = l("Expression", x2, Nil).reverse
  val info: ASTInfo = OptionalChain1
}
object OptionalChain1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ChainEvaluation0" -> `AL::OptionalChain[1,0].ChainEvaluation`,
    "HasCallInTailPosition0" -> `AL::OptionalChain[1,0].HasCallInTailPosition`,
  )
}

case class OptionalChain2(x1: Lexical, parserParams: List[Boolean]) extends OptionalChain {
  x1.parent = Some(this)
  val idx: Int = 2
  override def toString: String = {
    s"?. $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("Lexical", x1, Nil).reverse
  val info: ASTInfo = OptionalChain2
}
object OptionalChain2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "Contains0" -> `AL::OptionalChain[2,0].Contains`,
    "ChainEvaluation0" -> `AL::OptionalChain[2,0].ChainEvaluation`,
    "HasCallInTailPosition0" -> `AL::OptionalChain[2,0].HasCallInTailPosition`,
  )
}

case class OptionalChain3(x1: TemplateLiteral, parserParams: List[Boolean]) extends OptionalChain {
  x1.parent = Some(this)
  val idx: Int = 3
  override def toString: String = {
    s"?. $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("TemplateLiteral", x1, Nil).reverse
  val info: ASTInfo = OptionalChain3
}
object OptionalChain3 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "EarlyErrors0" -> `AL::OptionalChain[3,0].EarlyErrors`,
  )
}

case class OptionalChain4(x0: OptionalChain, x1: Arguments, parserParams: List[Boolean]) extends OptionalChain {
  x0.parent = Some(this)
  x1.parent = Some(this)
  val idx: Int = 4
  override def toString: String = {
    s"$x0 $x1"
  }
  val k: Int = d(x1, d(x0, 0))
  val fullList: List[(String, Value)] = l("Arguments", x1, l("OptionalChain", x0, Nil)).reverse
  val info: ASTInfo = OptionalChain4
}
object OptionalChain4 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ChainEvaluation0" -> `AL::OptionalChain[4,0].ChainEvaluation`,
    "HasCallInTailPosition0" -> `AL::OptionalChain[4,0].HasCallInTailPosition`,
  )
}

case class OptionalChain5(x0: OptionalChain, x2: Expression, parserParams: List[Boolean]) extends OptionalChain {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 5
  override def toString: String = {
    s"$x0 [ $x2 ]"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("Expression", x2, l("OptionalChain", x0, Nil)).reverse
  val info: ASTInfo = OptionalChain5
}
object OptionalChain5 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ChainEvaluation0" -> `AL::OptionalChain[5,0].ChainEvaluation`,
    "HasCallInTailPosition0" -> `AL::OptionalChain[5,0].HasCallInTailPosition`,
  )
}

case class OptionalChain6(x0: OptionalChain, x2: Lexical, parserParams: List[Boolean]) extends OptionalChain {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 6
  override def toString: String = {
    s"$x0 . $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("Lexical", x2, l("OptionalChain", x0, Nil)).reverse
  val info: ASTInfo = OptionalChain6
}
object OptionalChain6 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "Contains0" -> `AL::OptionalChain[6,0].Contains`,
    "ChainEvaluation0" -> `AL::OptionalChain[6,0].ChainEvaluation`,
    "HasCallInTailPosition0" -> `AL::OptionalChain[6,0].HasCallInTailPosition`,
  )
}

case class OptionalChain7(x0: OptionalChain, x1: TemplateLiteral, parserParams: List[Boolean]) extends OptionalChain {
  x0.parent = Some(this)
  x1.parent = Some(this)
  val idx: Int = 7
  override def toString: String = {
    s"$x0 $x1"
  }
  val k: Int = d(x1, d(x0, 0))
  val fullList: List[(String, Value)] = l("TemplateLiteral", x1, l("OptionalChain", x0, Nil)).reverse
  val info: ASTInfo = OptionalChain7
}
object OptionalChain7 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "EarlyErrors0" -> `AL::OptionalChain[7,0].EarlyErrors`,
  )
}
