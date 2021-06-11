package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait AssignmentOperator extends AST {
  val kind: String = "AssignmentOperator"
}
object AssignmentOperator extends ASTHelper {
  def apply(v: JsValue): AssignmentOperator = v match {
    case JsSeq(JsInt(0), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      AssignmentOperator0(params, span)
    case JsSeq(JsInt(1), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      AssignmentOperator1(params, span)
    case JsSeq(JsInt(2), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      AssignmentOperator2(params, span)
    case JsSeq(JsInt(3), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      AssignmentOperator3(params, span)
    case JsSeq(JsInt(4), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      AssignmentOperator4(params, span)
    case JsSeq(JsInt(5), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      AssignmentOperator5(params, span)
    case JsSeq(JsInt(6), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      AssignmentOperator6(params, span)
    case JsSeq(JsInt(7), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      AssignmentOperator7(params, span)
    case JsSeq(JsInt(8), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      AssignmentOperator8(params, span)
    case JsSeq(JsInt(9), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      AssignmentOperator9(params, span)
    case JsSeq(JsInt(10), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      AssignmentOperator10(params, span)
    case JsSeq(JsInt(11), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      AssignmentOperator11(params, span)
    case _ => throw InvalidAST
  }
}

case class AssignmentOperator0(parserParams: List[Boolean], span: Span) extends AssignmentOperator {
  val idx: Int = 0
  override def toString: String = {
    s"*="
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = AssignmentOperator0
}
object AssignmentOperator0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class AssignmentOperator1(parserParams: List[Boolean], span: Span) extends AssignmentOperator {
  val idx: Int = 1
  override def toString: String = {
    s"/="
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = AssignmentOperator1
}
object AssignmentOperator1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class AssignmentOperator2(parserParams: List[Boolean], span: Span) extends AssignmentOperator {
  val idx: Int = 2
  override def toString: String = {
    s"%="
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = AssignmentOperator2
}
object AssignmentOperator2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class AssignmentOperator3(parserParams: List[Boolean], span: Span) extends AssignmentOperator {
  val idx: Int = 3
  override def toString: String = {
    s"+="
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = AssignmentOperator3
}
object AssignmentOperator3 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class AssignmentOperator4(parserParams: List[Boolean], span: Span) extends AssignmentOperator {
  val idx: Int = 4
  override def toString: String = {
    s"-="
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = AssignmentOperator4
}
object AssignmentOperator4 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class AssignmentOperator5(parserParams: List[Boolean], span: Span) extends AssignmentOperator {
  val idx: Int = 5
  override def toString: String = {
    s"<<="
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = AssignmentOperator5
}
object AssignmentOperator5 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class AssignmentOperator6(parserParams: List[Boolean], span: Span) extends AssignmentOperator {
  val idx: Int = 6
  override def toString: String = {
    s">>="
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = AssignmentOperator6
}
object AssignmentOperator6 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class AssignmentOperator7(parserParams: List[Boolean], span: Span) extends AssignmentOperator {
  val idx: Int = 7
  override def toString: String = {
    s">>>="
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = AssignmentOperator7
}
object AssignmentOperator7 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class AssignmentOperator8(parserParams: List[Boolean], span: Span) extends AssignmentOperator {
  val idx: Int = 8
  override def toString: String = {
    s"&="
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = AssignmentOperator8
}
object AssignmentOperator8 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class AssignmentOperator9(parserParams: List[Boolean], span: Span) extends AssignmentOperator {
  val idx: Int = 9
  override def toString: String = {
    s"^="
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = AssignmentOperator9
}
object AssignmentOperator9 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class AssignmentOperator10(parserParams: List[Boolean], span: Span) extends AssignmentOperator {
  val idx: Int = 10
  override def toString: String = {
    s"|="
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = AssignmentOperator10
}
object AssignmentOperator10 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class AssignmentOperator11(parserParams: List[Boolean], span: Span) extends AssignmentOperator {
  val idx: Int = 11
  override def toString: String = {
    s"**="
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = AssignmentOperator11
}
object AssignmentOperator11 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}
