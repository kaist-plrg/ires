package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::LabelledStatement[0,0].ContainsUndefinedContinueTarget` extends Algo {
  val head = SyntaxDirectedHead("LabelledStatement", 0, 0, Rhs(List(NonTerminal("LabelIdentifier", List(""), false), Terminal(":"), NonTerminal("LabelledItem", List(""), false)), None), "ContainsUndefinedContinueTarget", List(Param("iterationSet", Normal), Param("labelSet", Normal)))
  val ids = List(
    "sec-static-semantics-containsundefinedcontinuetarget",
    "sec-syntax-directed-operations-labels",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (LabelIdentifier "StringValue")
  |  0:let label = __x0__
  |  1:let __x1__ = (copy-obj labelSet)
  |  1:append label -> __x1__
  |  1:let newLabelSet = __x1__
  |  2:access __x2__ = (LabelledItem "ContainsUndefinedContinueTarget" iterationSet newLabelSet)
  |  2:return __x2__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _label_ be the StringValue of |LabelIdentifier|.""",
    """        1. Let _newLabelSet_ be a copy of _labelSet_ with _label_ appended.""",
    """        1. Return ContainsUndefinedContinueTarget of |LabelledItem| with arguments _iterationSet_ and _newLabelSet_.""",
  )
}
