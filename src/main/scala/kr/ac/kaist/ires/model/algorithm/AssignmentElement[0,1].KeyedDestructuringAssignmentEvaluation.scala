package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AssignmentElement[0,1].KeyedDestructuringAssignmentEvaluation` extends Algo {
  val head = SyntaxDirectedHead("AssignmentElement", 0, 1, Rhs(List(NonTerminal("DestructuringAssignmentTarget", List(""), false), NonTerminal("Initializer", List(""), true)), None), "KeyedDestructuringAssignmentEvaluation", List(Param("value", Normal), Param("propertyName", Normal)))
  val ids = List(
    "sec-runtime-semantics-keyeddestructuringassignmentevaluation",
    "sec-destructuring-assignment",
    "sec-assignment-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:if (! (|| (is-instance-of DestructuringAssignmentTarget ObjectLiteral) (is-instance-of DestructuringAssignmentTarget ArrayLiteral))) {
  |    1:access __x0__ = (DestructuringAssignmentTarget "Evaluation")
  |    1:let lref = __x0__
  |    2:[? lref]
  |  } else 10:{}
  |  3:app __x1__ = (GetV value propertyName)
  |  3:let v = [? __x1__]
  |  10:if (&& (! (= Initializer absent)) (= v undefined)) {
  |    7:app __x2__ = (IsAnonymousFunctionDefinition Initializer)
  |    7:access __x3__ = (DestructuringAssignmentTarget "IsIdentifierRef")
  |    7:if (&& (= __x2__ true) (= __x3__ true)) {
  |      6:access __x4__ = (Initializer "NamedEvaluation" lref.ReferencedName)
  |      6:let rhsValue = [? __x4__]
  |    } else {
  |      8:access __x5__ = (Initializer "Evaluation")
  |      8:let defaultValue = __x5__
  |      9:app __x6__ = (GetValue defaultValue)
  |      9:let rhsValue = [? __x6__]
  |    }
  |  } else let rhsValue = v
  |  11:if (|| (is-instance-of DestructuringAssignmentTarget ObjectLiteral) (is-instance-of DestructuringAssignmentTarget ArrayLiteral)) {
  |    12:let assignmentPattern = (parse-syntax DestructuringAssignmentTarget "AssignmentPattern" (new []))
  |    13:access __x7__ = (assignmentPattern "DestructuringAssignmentEvaluation" rhsValue)
  |    13:return __x7__
  |  } else 10:{}
  |  14:app __x8__ = (PutValue lref rhsValue)
  |  14:return [? __x8__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If |DestructuringAssignmentTarget| is neither an |ObjectLiteral| nor an |ArrayLiteral|, then""",
    """            1. Let _lref_ be the result of evaluating |DestructuringAssignmentTarget|.""",
    """            1. ReturnIfAbrupt(_lref_).""",
    """          1. Let _v_ be ? GetV(_value_, _propertyName_).""",
    """          1. If |Initializer| is present and _v_ is *undefined*, then""",
    """            1. If IsAnonymousFunctionDefinition(|Initializer|) and IsIdentifierRef of |DestructuringAssignmentTarget| are both *true*, then""",
    """              1. Let _rhsValue_ be ? NamedEvaluation of |Initializer| with argument _lref_.[[ReferencedName]].""",
    """            1. Else,""",
    """              1. Let _defaultValue_ be the result of evaluating |Initializer|.""",
    """              1. Let _rhsValue_ be ? GetValue(_defaultValue_).""",
    """          1. Else, let _rhsValue_ be _v_.""",
    """          1. If |DestructuringAssignmentTarget| is an |ObjectLiteral| or an |ArrayLiteral|, then""",
    """            1. Let _assignmentPattern_ be the |AssignmentPattern| that is covered by |DestructuringAssignmentTarget|.""",
    """            1. Return the result of performing DestructuringAssignmentEvaluation of _assignmentPattern_ with _rhsValue_ as the argument.""",
    """          1. Return ? PutValue(_lref_, _rhsValue_).""",
  )
}
