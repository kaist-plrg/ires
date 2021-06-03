package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BindingRestElement[1,0].ContainsExpression` extends Algo {
  val head = SyntaxDirectedHead("BindingRestElement", 1, 0, Rhs(List(Terminal("..."), NonTerminal("BindingPattern", List(""), false)), None), "ContainsExpression", List())
  val ids = List(
    "sec-static-semantics-containsexpression",
    "sec-parameter-lists",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (BindingPattern "ContainsExpression")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return ContainsExpression of |BindingPattern|.""",
  )
}
