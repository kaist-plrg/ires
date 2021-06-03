package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BindingProperty[1,0].PropertyBindingInitialization` extends Algo {
  val head = SyntaxDirectedHead("BindingProperty", 1, 0, Rhs(List(NonTerminal("PropertyName", List(""), false), Terminal(":"), NonTerminal("BindingElement", List(""), false)), None), "PropertyBindingInitialization", List(Param("value", Normal), Param("environment", Normal)))
  val ids = List(
    "sec-destructuring-binding-patterns-runtime-semantics-propertybindinginitialization",
    "sec-destructuring-binding-patterns",
    "sec-declarations-and-the-variable-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (PropertyName "Evaluation")
  |  0:let P = __x0__
  |  1:[? P]
  |  2:access __x1__ = (BindingElement "KeyedBindingInitialization" value environment P)
  |  2:[? __x1__]
  |  3:return (new [P])
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _P_ be the result of evaluating |PropertyName|.""",
    """          1. ReturnIfAbrupt(_P_).""",
    """          1. Perform ? KeyedBindingInitialization of |BindingElement| with _value_, _environment_, and _P_ as the arguments.""",
    """          1. Return a List whose sole element is _P_.""",
  )
}
