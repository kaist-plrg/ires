package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::EvaluateCall` extends Algo {
  val head = NormalHead("EvaluateCall", List(Param("func", Normal), Param("ref", Normal), Param("arguments", Normal), Param("tailPosition", Normal)))
  val ids = List(
    "sec-evaluatecall",
    "sec-function-calls",
    "sec-left-hand-side-expressions",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  7:if (is-instance-of ref ReferenceRecord) {
  |    3:app __x0__ = (IsPropertyReference ref)
  |    3:if (= __x0__ true) {
  |      2:app __x1__ = (GetThisValue ref)
  |      2:let thisValue = __x1__
  |    } else {
  |      4:let refEnv = ref.Base
  |      5:assert (is-instance-of refEnv EnvironmentRecord)
  |      6:app __x2__ = (refEnv.WithBaseObject refEnv)
  |      6:let thisValue = __x2__
  |    }
  |  } else let thisValue = undefined
  |  9:access __x3__ = (arguments "ArgumentListEvaluation")
  |  9:let argList = [? __x3__]
  |  10:if (! (= (typeof func) Object)) throw TypeError else 15:{}
  |  11:app __x4__ = (IsCallable func)
  |  11:if (= __x4__ false) throw TypeError else 15:{}
  |  12:if (= tailPosition true) {
  |    app __x5__ = (PrepareForTailCall)
  |    __x5__
  |  } else 15:{}
  |  13:app __x6__ = (Call func thisValue argList)
  |  13:let result = __x6__
  |  16:return result
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If _ref_ is a Reference Record, then""",
    """            1. If IsPropertyReference(_ref_) is *true*, then""",
    """              1. Let _thisValue_ be GetThisValue(_ref_).""",
    """            1. Else,""",
    """              1. Let _refEnv_ be _ref_.[[Base]].""",
    """              1. Assert: _refEnv_ is an Environment Record.""",
    """              1. Let _thisValue_ be _refEnv_.WithBaseObject().""",
    """          1. Else,""",
    """            1. Let _thisValue_ be *undefined*.""",
    """          1. Let _argList_ be ? ArgumentListEvaluation of _arguments_.""",
    """          1. If Type(_func_) is not Object, throw a *TypeError* exception.""",
    """          1. If IsCallable(_func_) is *false*, throw a *TypeError* exception.""",
    """          1. If _tailPosition_ is *true*, perform PrepareForTailCall().""",
    """          1. Let _result_ be Call(_func_, _thisValue_, _argList_).""",
    """          1. Assert: If _tailPosition_ is *true*, the above call will not return here, but instead evaluation will continue as if the following return has already occurred.""",
    """          1. Assert: If _result_ is not an abrupt completion, then Type(_result_) is an ECMAScript language type.""",
    """          1. Return _result_.""",
  )
}
