package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait CoverParenthesizedExpressionAndArrowParameterList extends AST {
  val kind: String = "CoverParenthesizedExpressionAndArrowParameterList"
}

case class CoverParenthesizedExpressionAndArrowParameterList0(x1: Expression, parserParams: List[Boolean]) extends CoverParenthesizedExpressionAndArrowParameterList {
  x1.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"( $x1 )"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("Expression", x1, Nil).reverse
  val info: ASTInfo = CoverParenthesizedExpressionAndArrowParameterList0
}
object CoverParenthesizedExpressionAndArrowParameterList0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "CoveredParenthesizedExpression0" -> `AL::CoverParenthesizedExpressionAndArrowParameterList[0,0].CoveredParenthesizedExpression`,
    "CoveredFormalsList0" -> `AL::CoverParenthesizedExpressionAndArrowParameterList[0,0].CoveredFormalsList`,
  )
}

case class CoverParenthesizedExpressionAndArrowParameterList1(x1: Expression, parserParams: List[Boolean]) extends CoverParenthesizedExpressionAndArrowParameterList {
  x1.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"( $x1 , )"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("Expression", x1, Nil).reverse
  val info: ASTInfo = CoverParenthesizedExpressionAndArrowParameterList1
}
object CoverParenthesizedExpressionAndArrowParameterList1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "CoveredFormalsList0" -> `AL::CoverParenthesizedExpressionAndArrowParameterList[1,0].CoveredFormalsList`,
  )
}

case class CoverParenthesizedExpressionAndArrowParameterList2(parserParams: List[Boolean]) extends CoverParenthesizedExpressionAndArrowParameterList {
  val idx: Int = 2
  override def toString: String = {
    s"( )"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = CoverParenthesizedExpressionAndArrowParameterList2
}
object CoverParenthesizedExpressionAndArrowParameterList2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "CoveredFormalsList0" -> `AL::CoverParenthesizedExpressionAndArrowParameterList[2,0].CoveredFormalsList`,
  )
}

case class CoverParenthesizedExpressionAndArrowParameterList3(x2: BindingIdentifier, parserParams: List[Boolean]) extends CoverParenthesizedExpressionAndArrowParameterList {
  x2.parent = Some(this)
  val idx: Int = 3
  override def toString: String = {
    s"( ... $x2 )"
  }
  val k: Int = d(x2, 0)
  val fullList: List[(String, Value)] = l("BindingIdentifier", x2, Nil).reverse
  val info: ASTInfo = CoverParenthesizedExpressionAndArrowParameterList3
}
object CoverParenthesizedExpressionAndArrowParameterList3 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "CoveredFormalsList0" -> `AL::CoverParenthesizedExpressionAndArrowParameterList[3,0].CoveredFormalsList`,
  )
}

case class CoverParenthesizedExpressionAndArrowParameterList4(x2: BindingPattern, parserParams: List[Boolean]) extends CoverParenthesizedExpressionAndArrowParameterList {
  x2.parent = Some(this)
  val idx: Int = 4
  override def toString: String = {
    s"( ... $x2 )"
  }
  val k: Int = d(x2, 0)
  val fullList: List[(String, Value)] = l("BindingPattern", x2, Nil).reverse
  val info: ASTInfo = CoverParenthesizedExpressionAndArrowParameterList4
}
object CoverParenthesizedExpressionAndArrowParameterList4 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "CoveredFormalsList0" -> `AL::CoverParenthesizedExpressionAndArrowParameterList[4,0].CoveredFormalsList`,
  )
}

case class CoverParenthesizedExpressionAndArrowParameterList5(x1: Expression, x4: BindingIdentifier, parserParams: List[Boolean]) extends CoverParenthesizedExpressionAndArrowParameterList {
  x1.parent = Some(this)
  x4.parent = Some(this)
  val idx: Int = 5
  override def toString: String = {
    s"( $x1 , ... $x4 )"
  }
  val k: Int = d(x4, d(x1, 0))
  val fullList: List[(String, Value)] = l("BindingIdentifier", x4, l("Expression", x1, Nil)).reverse
  val info: ASTInfo = CoverParenthesizedExpressionAndArrowParameterList5
}
object CoverParenthesizedExpressionAndArrowParameterList5 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "CoveredFormalsList0" -> `AL::CoverParenthesizedExpressionAndArrowParameterList[5,0].CoveredFormalsList`,
  )
}

case class CoverParenthesizedExpressionAndArrowParameterList6(x1: Expression, x4: BindingPattern, parserParams: List[Boolean]) extends CoverParenthesizedExpressionAndArrowParameterList {
  x1.parent = Some(this)
  x4.parent = Some(this)
  val idx: Int = 6
  override def toString: String = {
    s"( $x1 , ... $x4 )"
  }
  val k: Int = d(x4, d(x1, 0))
  val fullList: List[(String, Value)] = l("BindingPattern", x4, l("Expression", x1, Nil)).reverse
  val info: ASTInfo = CoverParenthesizedExpressionAndArrowParameterList6
}
object CoverParenthesizedExpressionAndArrowParameterList6 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "CoveredFormalsList0" -> `AL::CoverParenthesizedExpressionAndArrowParameterList[6,0].CoveredFormalsList`,
  )
}
