package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::EqualityExpression[4,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("EqualityExpression", 4, 0, Rhs(List(NonTerminal("EqualityExpression", List(""), false), Terminal("!=="), NonTerminal("RelationalExpression", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-equality-operators-runtime-semantics-evaluation",
    "sec-equality-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (EqualityExpression "Evaluation")
  |  0:let lref = __x0__
  |  1:app __x1__ = (GetValue lref)
  |  1:let lval = [? __x1__]
  |  2:access __x2__ = (RelationalExpression "Evaluation")
  |  2:let rref = __x2__
  |  3:app __x3__ = (GetValue rref)
  |  3:let rval = [? __x3__]
  |  4:app __x4__ = (StrictEqualityComparison rval lval)
  |  4:let r = __x4__
  |  5:assert (&& (is-completion r) (= r.Type CONST_normal))
  |  6:if (= r.Value true) return false else return true
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _lref_ be the result of evaluating |EqualityExpression|.""",
    """        1. Let _lval_ be ? GetValue(_lref_).""",
    """        1. Let _rref_ be the result of evaluating |RelationalExpression|.""",
    """        1. Let _rval_ be ? GetValue(_rref_).""",
    """        1. Let _r_ be the result of performing Strict Equality Comparison _rval_ === _lval_.""",
    """        1. Assert: _r_ is a normal completion.""",
    """        1. If _r_.[[Value]] is *true*, return *false*. Otherwise, return *true*.""",
  )
}
