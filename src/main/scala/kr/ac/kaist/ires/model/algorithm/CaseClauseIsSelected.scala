package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CaseClauseIsSelected` extends Algo {
  val head = NormalHead("CaseClauseIsSelected", List(Param("C", Normal), Param("input", Normal)))
  val ids = List(
    "sec-runtime-semantics-caseclauseisselected",
    "sec-switch-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  1:access __x0__ = (C "Expression")
  |  1:access __x1__ = (__x0__ "Evaluation")
  |  1:let exprRef = __x1__
  |  2:app __x2__ = (GetValue exprRef)
  |  2:let clauseSelector = [? __x2__]
  |  3:app __x3__ = (StrictEqualityComparison input clauseSelector)
  |  3:return __x3__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: _C_ is an instance of the production <emu-grammar>CaseClause : `case` Expression `:` StatementList?</emu-grammar>.""",
    """        1. Let _exprRef_ be the result of evaluating the |Expression| of _C_.""",
    """        1. Let _clauseSelector_ be ? GetValue(_exprRef_).""",
    """        1. Return the result of performing Strict Equality Comparison _input_ === _clauseSelector_.""",
  )
}
