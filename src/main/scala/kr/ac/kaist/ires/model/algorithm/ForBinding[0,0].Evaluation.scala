package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ForBinding[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("ForBinding", 0, 0, Rhs(List(NonTerminal("BindingIdentifier", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-for-in-and-for-of-statements-runtime-semantics-evaluation",
    "sec-for-in-and-for-of-statements",
    "sec-iteration-statements",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (BindingIdentifier "StringValue")
  |  0:let bindingId = __x0__
  |  1:app __x1__ = (ResolveBinding bindingId)
  |  1:return [? __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _bindingId_ be StringValue of |BindingIdentifier|.""",
    """          1. Return ? ResolveBinding(_bindingId_).""",
  )
}
