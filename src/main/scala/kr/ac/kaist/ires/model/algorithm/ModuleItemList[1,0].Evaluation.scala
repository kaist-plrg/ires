package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ModuleItemList[1,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("ModuleItemList", 1, 0, Rhs(List(NonTerminal("ModuleItemList", List(""), false), NonTerminal("ModuleItem", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-module-semantics-runtime-semantics-evaluation",
    "sec-module-semantics",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ModuleItemList "Evaluation")
  |  0:let sl = __x0__
  |  1:[? sl]
  |  2:access __x1__ = (ModuleItem "Evaluation")
  |  2:let s = __x1__
  |  3:app __x2__ = (UpdateEmpty s sl)
  |  3:return __x2__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _sl_ be the result of evaluating |ModuleItemList|.""",
    """          1. ReturnIfAbrupt(_sl_).""",
    """          1. Let _s_ be the result of evaluating |ModuleItem|.""",
    """          1. Return Completion(UpdateEmpty(_s_, _sl_)).""",
  )
}
