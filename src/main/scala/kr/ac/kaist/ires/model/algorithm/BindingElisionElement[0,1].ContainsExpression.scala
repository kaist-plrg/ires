package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BindingElisionElement[0,1].ContainsExpression` extends Algo {
  val head = SyntaxDirectedHead("BindingElisionElement", 0, 1, Rhs(List(NonTerminal("Elision", List(""), true), NonTerminal("BindingElement", List(""), false)), None), "ContainsExpression", List())
  val ids = List(
    "sec-static-semantics-containsexpression",
    "sec-parameter-lists",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (BindingElement "ContainsExpression")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return ContainsExpression of |BindingElement|.""",
  )
}
