package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ModuleBody[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("ModuleBody", 0, 0, Rhs(List(NonTerminal("ModuleItemList", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-module-semantics-runtime-semantics-evaluation",
    "sec-module-semantics",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ModuleItemList "Evaluation")
  |  0:let result = __x0__
  |  1:if (&& (= result.Type CONST_normal) (= result.Value CONST_empty)) return undefined else 1:{}
  |  3:return result
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _result_ be the result of evaluating |ModuleItemList|.""",
    """          1. If _result_.[[Type]] is ~normal~ and _result_.[[Value]] is ~empty~, then""",
    """            1. Return NormalCompletion(*undefined*).""",
    """          1. Return Completion(_result_).""",
  )
}
