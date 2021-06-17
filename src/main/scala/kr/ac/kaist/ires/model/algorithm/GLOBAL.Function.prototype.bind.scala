package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Function.prototype.bind` extends Algo {
  val head = BuiltinHead(parseRef("""Function.prototype.bind"""), List(Param("thisArg", Normal), Param("args", Variadic)))
  val ids = List(
    "sec-function.prototype.bind",
    "sec-properties-of-the-function-prototype-object",
    "sec-function-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:let Target = this
  |  1:app __x0__ = (IsCallable Target)
  |  1:if (= __x0__ false) throw TypeError else 73:{}
  |  2:app __x1__ = (BoundFunctionCreate Target thisArg args)
  |  2:let F = [? __x1__]
  |  3:let L = 0i
  |  4:app __x2__ = (HasOwnProperty Target "length")
  |  4:let targetHasLength = [? __x2__]
  |  5:if (= targetHasLength true) {
  |    6:app __x3__ = (Get Target "length")
  |    6:let targetLen = [? __x3__]
  |    7:if (= (typeof targetLen) Number) if (= targetLen Infinity) L = Infinity else if (= targetLen -Infinity) L = 0i else {
  |      11:app __x4__ = (ToIntegerOrInfinity targetLen)
  |      11:let targetLenAsInt = [! __x4__]
  |      12:assert (! (|| (= targetLenAsInt Infinity) (= targetLenAsInt -Infinity)))
  |      13:let argCount = args.length
  |      14:app __x5__ = (max (- targetLenAsInt argCount) 0i)
  |      14:L = __x5__
  |    } else 73:{}
  |  } else 73:{}
  |  15:app __x6__ = (SetFunctionLength F L)
  |  15:[! __x6__]
  |  16:app __x7__ = (Get Target "name")
  |  16:let targetName = [? __x7__]
  |  17:if (! (= (typeof targetName) String)) targetName = "" else 73:{}
  |  18:app __x8__ = (SetFunctionName F targetName "bound")
  |  18:__x8__
  |  19:return F
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _Target_ be the *this* value.""",
    """          1. If IsCallable(_Target_) is *false*, throw a *TypeError* exception.""",
    """          1. Let _F_ be ? BoundFunctionCreate(_Target_, _thisArg_, _args_).""",
    """          1. Let _L_ be 0.""",
    """          1. Let _targetHasLength_ be ? HasOwnProperty(_Target_, *"length"*).""",
    """          1. If _targetHasLength_ is *true*, then""",
    """            1. Let _targetLen_ be ? Get(_Target_, *"length"*).""",
    """            1. If Type(_targetLen_) is Number, then""",
    """              1. If _targetLen_ is *+∞*<sub>𝔽</sub>, set _L_ to +∞.""",
    """              1. Else if _targetLen_ is *-∞*<sub>𝔽</sub>, set _L_ to 0.""",
    """              1. Else,""",
    """                1. Let _targetLenAsInt_ be ! ToIntegerOrInfinity(_targetLen_).""",
    """                1. Assert: _targetLenAsInt_ is finite.""",
    """                1. Let _argCount_ be the number of elements in _args_.""",
    """                1. Set _L_ to max(_targetLenAsInt_ - _argCount_, 0).""",
    """          1. Perform ! SetFunctionLength(_F_, _L_).""",
    """          1. Let _targetName_ be ? Get(_Target_, *"name"*).""",
    """          1. If Type(_targetName_) is not String, set _targetName_ to the empty String.""",
    """          1. Perform SetFunctionName(_F_, _targetName_, *"bound"*).""",
    """          1. Return _F_.""",
  )
}
