package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ObjectBindingPattern[3,1].ContainsExpression` extends Algo {
  val head = SyntaxDirectedHead("ObjectBindingPattern", 3, 1, Rhs(List(Terminal("{"), NonTerminal("BindingPropertyList", List(""), false), Terminal(","), NonTerminal("BindingRestProperty", List(""), false), Terminal("}")), None), "ContainsExpression", List())
  val ids = List(
    "sec-static-semantics-containsexpression",
    "sec-parameter-lists",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (BindingPropertyList "ContainsExpression")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return ContainsExpression of |BindingPropertyList|.""",
  )
}
