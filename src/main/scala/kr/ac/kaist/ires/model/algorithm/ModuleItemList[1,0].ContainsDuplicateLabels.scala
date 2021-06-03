package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ModuleItemList[1,0].ContainsDuplicateLabels` extends Algo {
  val head = SyntaxDirectedHead("ModuleItemList", 1, 0, Rhs(List(NonTerminal("ModuleItemList", List(""), false), NonTerminal("ModuleItem", List(""), false)), None), "ContainsDuplicateLabels", List(Param("labelSet", Normal)))
  val ids = List(
    "sec-static-semantics-containsduplicatelabels",
    "sec-syntax-directed-operations-labels",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ModuleItemList "ContainsDuplicateLabels" labelSet)
  |  0:let hasDuplicates = __x0__
  |  1:if (= hasDuplicates true) return true else 1:{}
  |  2:access __x1__ = (ModuleItem "ContainsDuplicateLabels" labelSet)
  |  2:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _hasDuplicates_ be ContainsDuplicateLabels of |ModuleItemList| with argument _labelSet_.""",
    """        1. If _hasDuplicates_ is *true*, return *true*.""",
    """        1. Return ContainsDuplicateLabels of |ModuleItem| with argument _labelSet_.""",
  )
}
