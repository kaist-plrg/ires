package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BindingPropertyList[1,0].PropertyBindingInitialization` extends Algo {
  val head = SyntaxDirectedHead("BindingPropertyList", 1, 0, Rhs(List(NonTerminal("BindingPropertyList", List(""), false), Terminal(","), NonTerminal("BindingProperty", List(""), false)), None), "PropertyBindingInitialization", List(Param("value", Normal), Param("environment", Normal)))
  val ids = List(
    "sec-destructuring-binding-patterns-runtime-semantics-propertybindinginitialization",
    "sec-destructuring-binding-patterns",
    "sec-declarations-and-the-variable-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (BindingPropertyList "PropertyBindingInitialization" value environment)
  |  0:let boundNames = [? __x0__]
  |  1:access __x1__ = (BindingProperty "PropertyBindingInitialization" value environment)
  |  1:let nextNames = [? __x1__]
  |  2:let __x2__ = nextNames
  |  2:let __x3__ = 0i
  |  2:while (< __x3__ __x2__.length) {
  |    let __x4__ = __x2__[__x3__]
  |    append __x4__ -> boundNames
  |    __x3__ = (+ __x3__ 1i)
  |  }
  |  3:return boundNames
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _boundNames_ be ? PropertyBindingInitialization of |BindingPropertyList| with arguments _value_ and _environment_.""",
    """          1. Let _nextNames_ be ? PropertyBindingInitialization of |BindingProperty| with arguments _value_ and _environment_.""",
    """          1. Append each item in _nextNames_ to the end of _boundNames_.""",
    """          1. Return _boundNames_.""",
  )
}
