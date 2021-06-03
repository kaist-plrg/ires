package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::LabelledStatement[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("LabelledStatement", 0, 0, Rhs(List(NonTerminal("LabelIdentifier", List(""), false), Terminal(":"), NonTerminal("LabelledItem", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-labelled-statements-runtime-semantics-evaluation",
    "sec-labelled-statements",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:let newLabelSet = (new [])
  |  1:access __x0__ = (this "LabelledEvaluation" newLabelSet)
  |  1:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _newLabelSet_ be a new empty List.""",
    """        1. Return LabelledEvaluation of this |LabelledStatement| with argument _newLabelSet_.""",
  )
}
