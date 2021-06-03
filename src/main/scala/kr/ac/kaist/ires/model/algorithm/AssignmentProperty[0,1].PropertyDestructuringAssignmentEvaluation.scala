package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AssignmentProperty[0,1].PropertyDestructuringAssignmentEvaluation` extends Algo {
  val head = SyntaxDirectedHead("AssignmentProperty", 0, 1, Rhs(List(NonTerminal("IdentifierReference", List(""), false), NonTerminal("Initializer", List(""), true)), None), "PropertyDestructuringAssignmentEvaluation", List(Param("value", Normal)))
  val ids = List(
    "sec-runtime-semantics-propertydestructuringassignmentevaluation",
    "sec-destructuring-assignment",
    "sec-assignment-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (IdentifierReference "StringValue")
  |  0:let P = __x0__
  |  1:app __x1__ = (ResolveBinding P)
  |  1:let lref = [? __x1__]
  |  2:app __x2__ = (GetV value P)
  |  2:let v = [? __x2__]
  |  3:if (&& (! (= Initializer absent)) (= v undefined)) {
  |    6:app __x3__ = (IsAnonymousFunctionDefinition Initializer)
  |    6:if (= __x3__ true) {
  |      5:access __x4__ = (Initializer "NamedEvaluation" P)
  |      5:v = __x4__
  |    } else {
  |      7:access __x5__ = (Initializer "Evaluation")
  |      7:let defaultValue = __x5__
  |      8:app __x6__ = (GetValue defaultValue)
  |      8:v = [? __x6__]
  |    }
  |  } else 10:{}
  |  9:app __x7__ = (PutValue lref v)
  |  9:[? __x7__]
  |  10:return (new [P])
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _P_ be StringValue of |IdentifierReference|.""",
    """          1. Let _lref_ be ? ResolveBinding(_P_).""",
    """          1. Let _v_ be ? GetV(_value_, _P_).""",
    """          1. If |Initializer_opt| is present and _v_ is *undefined*, then""",
    """            1. If IsAnonymousFunctionDefinition(|Initializer|) is *true*, then""",
    """              1. Set _v_ to the result of performing NamedEvaluation for |Initializer| with argument _P_.""",
    """            1. Else,""",
    """              1. Let _defaultValue_ be the result of evaluating |Initializer|.""",
    """              1. Set _v_ to ? GetValue(_defaultValue_).""",
    """          1. Perform ? PutValue(_lref_, _v_).""",
    """          1. Return a List whose sole element is _P_.""",
  )
}
