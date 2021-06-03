package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AssignmentExpression[8,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("AssignmentExpression", 8, 0, Rhs(List(NonTerminal("LeftHandSideExpression", List(""), false), Terminal("??="), NonTerminal("AssignmentExpression", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-assignment-operators-runtime-semantics-evaluation",
    "sec-assignment-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (LeftHandSideExpression "Evaluation")
  |  0:let lref = __x0__
  |  1:app __x1__ = (GetValue lref)
  |  1:let lval = [? __x1__]
  |  2:if (! (|| (= lval undefined) (= lval null))) return lval else 3:{}
  |  5:let __x2__ = true
  |  5:app __x3__ = (IsAnonymousFunctionDefinition AssignmentExpression)
  |  5:__x2__ = (= __x3__ true)
  |  5:if __x2__ {
  |    access __x4__ = (LeftHandSideExpression "IsIdentifierRef")
  |    __x2__ = (= __x4__ true)
  |  } else 3:{}
  |  5:if __x2__ {
  |    4:access __x5__ = (AssignmentExpression "NamedEvaluation" lref.ReferencedName)
  |    4:let rval = __x5__
  |  } else {
  |    6:access __x6__ = (AssignmentExpression "Evaluation")
  |    6:let rref = __x6__
  |    7:app __x7__ = (GetValue rref)
  |    7:let rval = [? __x7__]
  |  }
  |  8:app __x8__ = (PutValue lref rval)
  |  8:[? __x8__]
  |  9:return rval
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _lref_ be the result of evaluating |LeftHandSideExpression|.""",
    """        1. [id="step-assignmentexpression-evaluation-lgcl-nullish-getvalue"] Let _lval_ be ? GetValue(_lref_).""",
    """        1. If _lval_ is neither *undefined* nor *null*, return _lval_.""",
    """        1. If IsAnonymousFunctionDefinition(|AssignmentExpression|) is *true* and IsIdentifierRef of |LeftHandSideExpression| is *true*, then""",
    """          1. Let _rval_ be NamedEvaluation of |AssignmentExpression| with argument _lref_.[[ReferencedName]].""",
    """        1. Else,""",
    """          1. Let _rref_ be the result of evaluating |AssignmentExpression|.""",
    """          1. Let _rval_ be ? GetValue(_rref_).""",
    """        1. [id="step-assignmentexpression-evaluation-lgcl-nullish-putvalue"] Perform ? PutValue(_lref_, _rval_).""",
    """        1. Return _rval_.""",
  )
}
