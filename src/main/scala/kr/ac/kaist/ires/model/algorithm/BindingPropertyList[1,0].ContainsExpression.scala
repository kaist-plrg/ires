package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BindingPropertyList[1,0].ContainsExpression` extends Algo {
  val head = SyntaxDirectedHead("BindingPropertyList", 1, 0, Rhs(List(NonTerminal("BindingPropertyList", List(""), false), Terminal(","), NonTerminal("BindingProperty", List(""), false)), None), "ContainsExpression", List())
  val ids = List(
    "sec-static-semantics-containsexpression",
    "sec-parameter-lists",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (BindingPropertyList "ContainsExpression")
  |  0:let has = __x0__
  |  1:if (= has true) return true else 0:{}
  |  2:access __x1__ = (BindingProperty "ContainsExpression")
  |  2:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _has_ be ContainsExpression of |BindingPropertyList|.""",
    """        1. If _has_ is *true*, return *true*.""",
    """        1. Return ContainsExpression of |BindingProperty|.""",
  )
}
