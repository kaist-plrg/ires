package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BindingProperty[1,0].ContainsExpression` extends Algo {
  val head = SyntaxDirectedHead("BindingProperty", 1, 0, Rhs(List(NonTerminal("PropertyName", List(""), false), Terminal(":"), NonTerminal("BindingElement", List(""), false)), None), "ContainsExpression", List())
  val ids = List(
    "sec-static-semantics-containsexpression",
    "sec-parameter-lists",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (PropertyName "IsComputedPropertyKey")
  |  0:let has = __x0__
  |  1:if (= has true) return true else 0:{}
  |  2:access __x1__ = (BindingElement "ContainsExpression")
  |  2:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _has_ be IsComputedPropertyKey of |PropertyName|.""",
    """        1. If _has_ is *true*, return *true*.""",
    """        1. Return ContainsExpression of |BindingElement|.""",
  )
}
