package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::LogicalANDExpression[1,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("LogicalANDExpression", 1, 0, Rhs(List(NonTerminal("LogicalANDExpression", List(""), false), Terminal("&&"), NonTerminal("BitwiseORExpression", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-binary-logical-operators-runtime-semantics-evaluation",
    "sec-binary-logical-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (LogicalANDExpression "Evaluation")
  |  0:let lref = __x0__
  |  1:app __x1__ = (GetValue lref)
  |  1:let lval = [? __x1__]
  |  2:app __x2__ = (ToBoolean lval)
  |  2:let lbool = [! __x2__]
  |  3:if (= lbool false) return lval else 3:{}
  |  4:access __x3__ = (BitwiseORExpression "Evaluation")
  |  4:let rref = __x3__
  |  5:app __x4__ = (GetValue rref)
  |  5:return [? __x4__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _lref_ be the result of evaluating |LogicalANDExpression|.""",
    """        1. Let _lval_ be ? GetValue(_lref_).""",
    """        1. Let _lbool_ be ! ToBoolean(_lval_).""",
    """        1. If _lbool_ is *false*, return _lval_.""",
    """        1. Let _rref_ be the result of evaluating |BitwiseORExpression|.""",
    """        1. Return ? GetValue(_rref_).""",
  )
}
