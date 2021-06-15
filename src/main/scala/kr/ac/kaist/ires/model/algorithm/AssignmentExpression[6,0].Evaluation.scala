package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AssignmentExpression[6,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("AssignmentExpression", 6, 0, Rhs(List(NonTerminal("LeftHandSideExpression", List(""), false), Terminal("&&="), NonTerminal("AssignmentExpression", List(""), false)), None), "Evaluation", List())
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
  |  2:app __x2__ = (ToBoolean lval)
  |  2:let lbool = [! __x2__]
  |  3:if (= lbool false) return lval else 3:{}
  |  6:let __x3__ = true
  |  6:app __x4__ = (IsAnonymousFunctionDefinition AssignmentExpression)
  |  6:__x3__ = (= __x4__ true)
  |  6:if __x3__ {
  |    access __x5__ = (LeftHandSideExpression "IsIdentifierRef")
  |    __x3__ = (= __x5__ true)
  |  } else 3:{}
  |  6:if __x3__ {
  |    5:access __x6__ = (AssignmentExpression "NamedEvaluation" lref.ReferencedName)
  |    5:let rval = __x6__
  |  } else {
  |    7:access __x7__ = (AssignmentExpression "Evaluation")
  |    7:let rref = __x7__
  |    8:app __x8__ = (GetValue rref)
  |    8:let rval = [? __x8__]
  |  }
  |  9:app __x9__ = (PutValue lref rval)
  |  9:[? __x9__]
  |  10:return rval
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _lref_ be the result of evaluating |LeftHandSideExpression|.""",
    """        1. [id="step-assignmentexpression-evaluation-lgcl-and-getvalue"] Let _lval_ be ? GetValue(_lref_).""",
    """        1. Let _lbool_ be ! ToBoolean(_lval_).""",
    """        1. If _lbool_ is *false*, return _lval_.""",
    """        1. If IsAnonymousFunctionDefinition(|AssignmentExpression|) is *true* and IsIdentifierRef of |LeftHandSideExpression| is *true*, then""",
    """          1. Let _rval_ be NamedEvaluation of |AssignmentExpression| with argument _lref_.[[ReferencedName]].""",
    """        1. Else,""",
    """          1. Let _rref_ be the result of evaluating |AssignmentExpression|.""",
    """          1. Let _rval_ be ? GetValue(_rref_).""",
    """        1. [id="step-assignmentexpression-evaluation-lgcl-and-putvalue"] Perform ? PutValue(_lref_, _rval_).""",
    """        1. Return _rval_.""",
  )
}
