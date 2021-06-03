package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::LexicalBinding[0,1].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("LexicalBinding", 0, 1, Rhs(List(NonTerminal("BindingIdentifier", List(""), false), NonTerminal("Initializer", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-let-and-const-declarations-runtime-semantics-evaluation",
    "sec-let-and-const-declarations",
    "sec-declarations-and-the-variable-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (BindingIdentifier "StringValue")
  |  0:let bindingId = __x0__
  |  1:app __x1__ = (ResolveBinding bindingId)
  |  1:let lhs = __x1__
  |  4:app __x2__ = (IsAnonymousFunctionDefinition Initializer)
  |  4:if (= __x2__ true) {
  |    3:access __x3__ = (Initializer "NamedEvaluation" bindingId)
  |    3:let value = __x3__
  |  } else {
  |    5:access __x4__ = (Initializer "Evaluation")
  |    5:let rhs = __x4__
  |    6:app __x5__ = (GetValue rhs)
  |    6:let value = [? __x5__]
  |  }
  |  7:app __x6__ = (InitializeReferencedBinding lhs value)
  |  7:return __x6__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _bindingId_ be StringValue of |BindingIdentifier|.""",
    """          1. Let _lhs_ be ResolveBinding(_bindingId_).""",
    """          1. If IsAnonymousFunctionDefinition(|Initializer|) is *true*, then""",
    """            1. Let _value_ be NamedEvaluation of |Initializer| with argument _bindingId_.""",
    """          1. Else,""",
    """            1. Let _rhs_ be the result of evaluating |Initializer|.""",
    """            1. Let _value_ be ? GetValue(_rhs_).""",
    """          1. Return InitializeReferencedBinding(_lhs_, _value_).""",
  )
}
