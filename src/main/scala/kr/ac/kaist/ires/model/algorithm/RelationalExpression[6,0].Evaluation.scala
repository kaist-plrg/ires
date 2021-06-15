package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::RelationalExpression[6,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("RelationalExpression", 6, 0, Rhs(List(NonTerminal("RelationalExpression", List(""), false), Terminal("in"), NonTerminal("ShiftExpression", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-relational-operators-runtime-semantics-evaluation",
    "sec-relational-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (RelationalExpression "Evaluation")
  |  0:let lref = __x0__
  |  1:app __x1__ = (GetValue lref)
  |  1:let lval = [? __x1__]
  |  2:access __x2__ = (ShiftExpression "Evaluation")
  |  2:let rref = __x2__
  |  3:app __x3__ = (GetValue rref)
  |  3:let rval = [? __x3__]
  |  4:if (! (= (typeof rval) Object)) throw TypeError else 3:{}
  |  5:app __x4__ = (ToPropertyKey lval)
  |  5:app __x5__ = (HasProperty rval [? __x4__])
  |  5:return [? __x5__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _lref_ be the result of evaluating |RelationalExpression|.""",
    """        1. Let _lval_ be ? GetValue(_lref_).""",
    """        1. Let _rref_ be the result of evaluating |ShiftExpression|.""",
    """        1. Let _rval_ be ? GetValue(_rref_).""",
    """        1. If Type(_rval_) is not Object, throw a *TypeError* exception.""",
    """        1. Return ? HasProperty(_rval_, ? ToPropertyKey(_lval_)).""",
  )
}
