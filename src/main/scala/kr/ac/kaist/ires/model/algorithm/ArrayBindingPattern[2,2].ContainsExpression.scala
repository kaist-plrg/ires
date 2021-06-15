package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArrayBindingPattern[2,2].ContainsExpression` extends Algo {
  val head = SyntaxDirectedHead("ArrayBindingPattern", 2, 2, Rhs(List(Terminal("["), NonTerminal("BindingElementList", List(""), false), Terminal(","), NonTerminal("Elision", List(""), true), Terminal("]")), None), "ContainsExpression", List())
  val ids = List(
    "sec-static-semantics-containsexpression",
    "sec-parameter-lists",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (BindingElementList "ContainsExpression")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return ContainsExpression of |BindingElementList|.""",
  )
}
