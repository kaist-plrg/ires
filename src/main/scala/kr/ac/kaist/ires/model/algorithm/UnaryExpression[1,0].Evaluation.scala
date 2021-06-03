package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::UnaryExpression[1,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("UnaryExpression", 1, 0, Rhs(List(Terminal("delete"), NonTerminal("UnaryExpression", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-delete-operator-runtime-semantics-evaluation",
    "sec-delete-operator",
    "sec-unary-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (UnaryExpression "Evaluation")
  |  0:let ref = __x0__
  |  1:[? ref]
  |  2:if (! (is-instance-of ref ReferenceRecord)) return true else 3:{}
  |  3:app __x1__ = (IsUnresolvableReference ref)
  |  3:if (= __x1__ true) {
  |    4:assert (= ref.Strict false)
  |    5:return true
  |  } else 3:{}
  |  12:app __x2__ = (IsPropertyReference ref)
  |  12:if (= __x2__ true) {
  |    7:app __x3__ = (IsSuperReference ref)
  |    7:if (= __x3__ true) throw ReferenceError else 3:{}
  |    8:app __x4__ = (ToObject ref.Base)
  |    8:let baseObj = [! __x4__]
  |    9:app __x5__ = (baseObj.Delete baseObj ref.ReferencedName)
  |    9:let deleteStatus = [? __x5__]
  |    10:if (&& (= deleteStatus false) (= ref.Strict true)) throw TypeError else 3:{}
  |    11:return deleteStatus
  |  } else {
  |    13:let base = ref.Base
  |    14:assert (is-instance-of base EnvironmentRecord)
  |    15:app __x6__ = (base.DeleteBinding base ref.ReferencedName)
  |    15:return [? __x6__]
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _ref_ be the result of evaluating |UnaryExpression|.""",
    """          1. ReturnIfAbrupt(_ref_).""",
    """          1. If _ref_ is not a Reference Record, return *true*.""",
    """          1. If IsUnresolvableReference(_ref_) is *true*, then""",
    """            1. Assert: _ref_.[[Strict]] is *false*.""",
    """            1. Return *true*.""",
    """          1. If IsPropertyReference(_ref_) is *true*, then""",
    """            1. If IsSuperReference(_ref_) is *true*, throw a *ReferenceError* exception.""",
    """            1. [id="step-delete-operator-toobject"] Let _baseObj_ be ! ToObject(_ref_.[[Base]]).""",
    """            1. Let _deleteStatus_ be ? _baseObj_.[[Delete]](_ref_.[[ReferencedName]]).""",
    """            1. If _deleteStatus_ is *false* and _ref_.[[Strict]] is *true*, throw a *TypeError* exception.""",
    """            1. Return _deleteStatus_.""",
    """          1. Else,""",
    """            1. Let _base_ be _ref_.[[Base]].""",
    """            1. Assert: _base_ is an Environment Record.""",
    """            1. Return ? _base_.DeleteBinding(_ref_.[[ReferencedName]]).""",
  )
}
