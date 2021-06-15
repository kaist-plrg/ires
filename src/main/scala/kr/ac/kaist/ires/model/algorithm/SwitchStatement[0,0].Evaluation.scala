package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SwitchStatement[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("SwitchStatement", 0, 0, Rhs(List(Terminal("switch"), Terminal("("), NonTerminal("Expression", List(""), false), Terminal(")"), NonTerminal("CaseBlock", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-switch-statement-runtime-semantics-evaluation",
    "sec-switch-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (Expression "Evaluation")
  |  0:let exprRef = __x0__
  |  1:app __x1__ = (GetValue exprRef)
  |  1:let switchValue = [? __x1__]
  |  2:let oldEnv = CONTEXT.LexicalEnvironment
  |  3:app __x2__ = (NewDeclarativeEnvironment oldEnv)
  |  3:let blockEnv = __x2__
  |  4:app __x3__ = (BlockDeclarationInstantiation CaseBlock blockEnv)
  |  4:__x3__
  |  5:CONTEXT.LexicalEnvironment = blockEnv
  |  6:access __x4__ = (CaseBlock "CaseBlockEvaluation" switchValue)
  |  6:let R = __x4__
  |  7:CONTEXT.LexicalEnvironment = oldEnv
  |  8:return R
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _exprRef_ be the result of evaluating |Expression|.""",
    """        1. Let _switchValue_ be ? GetValue(_exprRef_).""",
    """        1. Let _oldEnv_ be the running execution context's LexicalEnvironment.""",
    """        1. Let _blockEnv_ be NewDeclarativeEnvironment(_oldEnv_).""",
    """        1. Perform BlockDeclarationInstantiation(|CaseBlock|, _blockEnv_).""",
    """        1. Set the running execution context's LexicalEnvironment to _blockEnv_.""",
    """        1. Let _R_ be CaseBlockEvaluation of |CaseBlock| with argument _switchValue_.""",
    """        1. Set the running execution context's LexicalEnvironment to _oldEnv_.""",
    """        1. Return _R_.""",
  )
}
