package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AssignmentExpression[4,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("AssignmentExpression", 4, 0, Rhs(List(NonTerminal("LeftHandSideExpression", List(""), false), Terminal("="), NonTerminal("AssignmentExpression", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-assignment-operators-runtime-semantics-evaluation",
    "sec-assignment-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:if (! (|| (is-instance-of LeftHandSideExpression ObjectLiteral) (is-instance-of LeftHandSideExpression ArrayLiteral))) {
  |    1:access __x0__ = (LeftHandSideExpression "Evaluation")
  |    1:let lref = __x0__
  |    2:[? lref]
  |    5:app __x1__ = (IsAnonymousFunctionDefinition AssignmentExpression)
  |    5:access __x2__ = (LeftHandSideExpression "IsIdentifierRef")
  |    5:if (&& (= __x1__ true) (= __x2__ true)) {
  |      4:access __x3__ = (AssignmentExpression "NamedEvaluation" lref.ReferencedName)
  |      4:let rval = __x3__
  |    } else {
  |      6:access __x4__ = (AssignmentExpression "Evaluation")
  |      6:let rref = __x4__
  |      7:app __x5__ = (GetValue rref)
  |      7:let rval = [? __x5__]
  |    }
  |    8:app __x6__ = (PutValue lref rval)
  |    8:[? __x6__]
  |    9:return rval
  |  } else 3:{}
  |  10:let assignmentPattern = (parse-syntax LeftHandSideExpression "AssignmentPattern" (new []))
  |  11:access __x7__ = (AssignmentExpression "Evaluation")
  |  11:let rref = __x7__
  |  12:app __x8__ = (GetValue rref)
  |  12:let rval = [? __x8__]
  |  13:access __x9__ = (assignmentPattern "DestructuringAssignmentEvaluation" rval)
  |  13:[? __x9__]
  |  14:return rval
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If |LeftHandSideExpression| is neither an |ObjectLiteral| nor an |ArrayLiteral|, then""",
    """          1. Let _lref_ be the result of evaluating |LeftHandSideExpression|.""",
    """          1. ReturnIfAbrupt(_lref_).""",
    """          1. If IsAnonymousFunctionDefinition(|AssignmentExpression|) and IsIdentifierRef of |LeftHandSideExpression| are both *true*, then""",
    """            1. Let _rval_ be NamedEvaluation of |AssignmentExpression| with argument _lref_.[[ReferencedName]].""",
    """          1. Else,""",
    """            1. Let _rref_ be the result of evaluating |AssignmentExpression|.""",
    """            1. Let _rval_ be ? GetValue(_rref_).""",
    """          1. [id="step-assignmentexpression-evaluation-simple-putvalue"] Perform ? PutValue(_lref_, _rval_).""",
    """          1. Return _rval_.""",
    """        1. Let _assignmentPattern_ be the |AssignmentPattern| that is covered by |LeftHandSideExpression|.""",
    """        1. Let _rref_ be the result of evaluating |AssignmentExpression|.""",
    """        1. Let _rval_ be ? GetValue(_rref_).""",
    """        1. Perform ? DestructuringAssignmentEvaluation of _assignmentPattern_ using _rval_ as the argument.""",
    """        1. Return _rval_.""",
  )
}
