package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BindingPattern[0,0].BindingInitialization` extends Algo {
  val head = SyntaxDirectedHead("BindingPattern", 0, 0, Rhs(List(NonTerminal("ObjectBindingPattern", List(""), false)), None), "BindingInitialization", List(Param("value", Normal), Param("environment", Normal)))
  val ids = List(
    "sec-runtime-semantics-bindinginitialization",
    "sec-syntax-directed-operations-miscellaneous",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (RequireObjectCoercible value)
  |  0:[? __x0__]
  |  1:access __x1__ = (ObjectBindingPattern "BindingInitialization" value environment)
  |  1:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Perform ? RequireObjectCoercible(_value_).""",
    """        1. Return the result of performing BindingInitialization for |ObjectBindingPattern| using _value_ and _environment_ as arguments.""",
  )
}
