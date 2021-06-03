package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SingleNameBinding[0,1].KeyedBindingInitialization` extends Algo {
  val head = SyntaxDirectedHead("SingleNameBinding", 0, 1, Rhs(List(NonTerminal("BindingIdentifier", List(""), false), NonTerminal("Initializer", List(""), true)), None), "KeyedBindingInitialization", List(Param("value", Normal), Param("environment", Normal), Param("propertyName", Normal)))
  val ids = List(
    "sec-runtime-semantics-keyedbindinginitialization",
    "sec-destructuring-binding-patterns",
    "sec-declarations-and-the-variable-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (BindingIdentifier "StringValue")
  |  0:let bindingId = __x0__
  |  1:app __x1__ = (ResolveBinding bindingId environment)
  |  1:let lhs = [? __x1__]
  |  2:app __x2__ = (GetV value propertyName)
  |  2:let v = [? __x2__]
  |  3:if (&& (! (= Initializer absent)) (= v undefined)) {
  |    6:app __x3__ = (IsAnonymousFunctionDefinition Initializer)
  |    6:if (= __x3__ true) {
  |      5:access __x4__ = (Initializer "NamedEvaluation" bindingId)
  |      5:v = __x4__
  |    } else {
  |      7:access __x5__ = (Initializer "Evaluation")
  |      7:let defaultValue = __x5__
  |      8:app __x6__ = (GetValue defaultValue)
  |      8:v = [? __x6__]
  |    }
  |  } else 10:{}
  |  9:if (= environment undefined) {
  |    app __x7__ = (PutValue lhs v)
  |    return [? __x7__]
  |  } else 10:{}
  |  10:app __x8__ = (InitializeReferencedBinding lhs v)
  |  10:return __x8__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _bindingId_ be StringValue of |BindingIdentifier|.""",
    """          1. Let _lhs_ be ? ResolveBinding(_bindingId_, _environment_).""",
    """          1. Let _v_ be ? GetV(_value_, _propertyName_).""",
    """          1. If |Initializer| is present and _v_ is *undefined*, then""",
    """            1. If IsAnonymousFunctionDefinition(|Initializer|) is *true*, then""",
    """              1. Set _v_ to the result of performing NamedEvaluation for |Initializer| with argument _bindingId_.""",
    """            1. Else,""",
    """              1. Let _defaultValue_ be the result of evaluating |Initializer|.""",
    """              1. Set _v_ to ? GetValue(_defaultValue_).""",
    """          1. If _environment_ is *undefined*, return ? PutValue(_lhs_, _v_).""",
    """          1. Return InitializeReferencedBinding(_lhs_, _v_).""",
  )
}
