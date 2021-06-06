package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait ArgumentList extends AST {
  val kind: String = "ArgumentList"
}

case class ArgumentList0(x0: AssignmentExpression, parserParams: List[Boolean]) extends ArgumentList {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("AssignmentExpression", x0, Nil).reverse
  val info: ASTInfo = ArgumentList0
}
object ArgumentList0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ArgumentListEvaluation0" -> `AL::ArgumentList[0,0].ArgumentListEvaluation`,
  )
}

case class ArgumentList1(x1: AssignmentExpression, parserParams: List[Boolean]) extends ArgumentList {
  x1.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"... $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("AssignmentExpression", x1, Nil).reverse
  val info: ASTInfo = ArgumentList1
}
object ArgumentList1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ArgumentListEvaluation0" -> `AL::ArgumentList[1,0].ArgumentListEvaluation`,
  )
}

case class ArgumentList2(x0: ArgumentList, x2: AssignmentExpression, parserParams: List[Boolean]) extends ArgumentList {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 2
  override def toString: String = {
    s"$x0 , $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("AssignmentExpression", x2, l("ArgumentList", x0, Nil)).reverse
  val info: ASTInfo = ArgumentList2
}
object ArgumentList2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ArgumentListEvaluation0" -> `AL::ArgumentList[2,0].ArgumentListEvaluation`,
  )
}

case class ArgumentList3(x0: ArgumentList, x3: AssignmentExpression, parserParams: List[Boolean]) extends ArgumentList {
  x0.parent = Some(this)
  x3.parent = Some(this)
  val idx: Int = 3
  override def toString: String = {
    s"$x0 , ... $x3"
  }
  val k: Int = d(x3, d(x0, 0))
  val fullList: List[(String, Value)] = l("AssignmentExpression", x3, l("ArgumentList", x0, Nil)).reverse
  val info: ASTInfo = ArgumentList3
}
object ArgumentList3 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ArgumentListEvaluation0" -> `AL::ArgumentList[3,0].ArgumentListEvaluation`,
  )
}
