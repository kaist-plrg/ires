package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::LabelledStatement[0,0].ContainsDuplicateLabels` extends Algo {
  val head = SyntaxDirectedHead("LabelledStatement", 0, 0, Rhs(List(NonTerminal("LabelIdentifier", List(""), false), Terminal(":"), NonTerminal("LabelledItem", List(""), false)), None), "ContainsDuplicateLabels", List(Param("labelSet", Normal)))
  val ids = List(
    "sec-static-semantics-containsduplicatelabels",
    "sec-syntax-directed-operations-labels",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (LabelIdentifier "StringValue")
  |  0:let label = __x0__
  |  1:if (contains labelSet label) return true else 1:{}
  |  2:let __x1__ = (copy-obj labelSet)
  |  2:append label -> __x1__
  |  2:let newLabelSet = __x1__
  |  3:access __x2__ = (LabelledItem "ContainsDuplicateLabels" newLabelSet)
  |  3:return __x2__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _label_ be the StringValue of |LabelIdentifier|.""",
    """        1. If _label_ is an element of _labelSet_, return *true*.""",
    """        1. Let _newLabelSet_ be a copy of _labelSet_ with _label_ appended.""",
    """        1. Return ContainsDuplicateLabels of |LabelledItem| with argument _newLabelSet_.""",
  )
}
