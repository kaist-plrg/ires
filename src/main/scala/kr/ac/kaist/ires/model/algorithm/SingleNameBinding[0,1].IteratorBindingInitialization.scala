package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SingleNameBinding[0,1].IteratorBindingInitialization` extends Algo {
  val head = SyntaxDirectedHead("SingleNameBinding", 0, 1, Rhs(List(NonTerminal("BindingIdentifier", List(""), false), NonTerminal("Initializer", List(""), true)), None), "IteratorBindingInitialization", List(Param("iteratorRecord", Normal), Param("environment", Normal)))
  val ids = List(
    "sec-runtime-semantics-iteratorbindinginitialization",
    "sec-syntax-directed-operations-miscellaneous",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (BindingIdentifier "StringValue")
  |  0:let bindingId = __x0__
  |  1:app __x1__ = (ResolveBinding bindingId environment)
  |  1:let lhs = [? __x1__]
  |  2:if (= iteratorRecord.Done false) {
  |    3:app __x2__ = (IteratorStep iteratorRecord)
  |    3:let next = __x2__
  |    4:app __x3__ = (IsAbruptCompletion next)
  |    4:if __x3__ iteratorRecord.Done = true else 1:{}
  |    5:[? next]
  |    7:if (= next false) iteratorRecord.Done = true else {
  |      8:app __x4__ = (IteratorValue next)
  |      8:let v = __x4__
  |      9:app __x5__ = (IsAbruptCompletion v)
  |      9:if __x5__ iteratorRecord.Done = true else 1:{}
  |      10:[? v]
  |    }
  |  } else 1:{}
  |  11:if (= iteratorRecord.Done true) let v = undefined else 1:{}
  |  12:if (&& (! (= Initializer absent)) (= v undefined)) {
  |    15:app __x6__ = (IsAnonymousFunctionDefinition Initializer)
  |    15:if (= __x6__ true) {
  |      14:access __x7__ = (Initializer "NamedEvaluation" bindingId)
  |      14:v = __x7__
  |    } else {
  |      16:access __x8__ = (Initializer "Evaluation")
  |      16:let defaultValue = __x8__
  |      17:app __x9__ = (GetValue defaultValue)
  |      17:v = [? __x9__]
  |    }
  |  } else 1:{}
  |  18:if (= environment undefined) {
  |    app __x10__ = (PutValue lhs v)
  |    return [? __x10__]
  |  } else 1:{}
  |  19:app __x11__ = (InitializeReferencedBinding lhs v)
  |  19:return __x11__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _bindingId_ be StringValue of |BindingIdentifier|.""",
    """        1. Let _lhs_ be ? ResolveBinding(_bindingId_, _environment_).""",
    """        1. If _iteratorRecord_.[[Done]] is *false*, then""",
    """          1. Let _next_ be IteratorStep(_iteratorRecord_).""",
    """          1. If _next_ is an abrupt completion, set _iteratorRecord_.[[Done]] to *true*.""",
    """          1. ReturnIfAbrupt(_next_).""",
    """          1. If _next_ is *false*, set _iteratorRecord_.[[Done]] to *true*.""",
    """          1. Else,""",
    """            1. Let _v_ be IteratorValue(_next_).""",
    """            1. If _v_ is an abrupt completion, set _iteratorRecord_.[[Done]] to *true*.""",
    """            1. ReturnIfAbrupt(_v_).""",
    """        1. If _iteratorRecord_.[[Done]] is *true*, let _v_ be *undefined*.""",
    """        1. If |Initializer| is present and _v_ is *undefined*, then""",
    """          1. If IsAnonymousFunctionDefinition(|Initializer|) is *true*, then""",
    """            1. Set _v_ to the result of performing NamedEvaluation for |Initializer| with argument _bindingId_.""",
    """          1. Else,""",
    """            1. Let _defaultValue_ be the result of evaluating |Initializer|.""",
    """            1. Set _v_ to ? GetValue(_defaultValue_).""",
    """        1. If _environment_ is *undefined*, return ? PutValue(_lhs_, _v_).""",
    """        1. Return InitializeReferencedBinding(_lhs_, _v_).""",
  )
}
