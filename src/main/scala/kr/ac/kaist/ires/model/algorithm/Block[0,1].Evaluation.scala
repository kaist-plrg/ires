package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Block[0,1].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("Block", 0, 1, Rhs(List(Terminal("{"), NonTerminal("StatementList", List(""), false), Terminal("}")), None), "Evaluation", List())
  val ids = List(
    "sec-block-runtime-semantics-evaluation",
    "sec-block",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:let oldEnv = CONTEXT.LexicalEnvironment
  |  1:app __x0__ = (NewDeclarativeEnvironment oldEnv)
  |  1:let blockEnv = __x0__
  |  2:app __x1__ = (BlockDeclarationInstantiation StatementList blockEnv)
  |  2:__x1__
  |  3:CONTEXT.LexicalEnvironment = blockEnv
  |  4:access __x2__ = (StatementList "Evaluation")
  |  4:let blockValue = __x2__
  |  5:CONTEXT.LexicalEnvironment = oldEnv
  |  6:return blockValue
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _oldEnv_ be the running execution context's LexicalEnvironment.""",
    """        1. Let _blockEnv_ be NewDeclarativeEnvironment(_oldEnv_).""",
    """        1. Perform BlockDeclarationInstantiation(|StatementList|, _blockEnv_).""",
    """        1. Set the running execution context's LexicalEnvironment to _blockEnv_.""",
    """        1. Let _blockValue_ be the result of evaluating |StatementList|.""",
    """        1. Set the running execution context's LexicalEnvironment to _oldEnv_.""",
    """        1. Return _blockValue_.""",
  )
}
