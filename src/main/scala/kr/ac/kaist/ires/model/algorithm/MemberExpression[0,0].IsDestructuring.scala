package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::MemberExpression[0,0].IsDestructuring` extends Algo {
  val head = SyntaxDirectedHead("MemberExpression", 0, 0, Rhs(List(NonTerminal("PrimaryExpression", List(""), false)), None), "IsDestructuring", List())
  val ids = List(
    "sec-static-semantics-isdestructuring",
    "sec-for-in-and-for-of-statements",
    "sec-iteration-statements",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:if (|| (is-instance-of PrimaryExpression ObjectLiteral) (is-instance-of PrimaryExpression ArrayLiteral)) return true else 10:{}
  |  1:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If |PrimaryExpression| is either an |ObjectLiteral| or an |ArrayLiteral|, return *true*.""",
    """          1. Return *false*.""",
  )
}
