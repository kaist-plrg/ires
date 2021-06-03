package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::OrdinaryCallBindThis` extends Algo {
  val head = NormalHead("OrdinaryCallBindThis", List(Param("F", Normal), Param("calleeContext", Normal), Param("thisArgument", Normal)))
  val ids = List(
    "sec-ordinarycallbindthis",
    "sec-ecmascript-function-objects-call-thisargument-argumentslist",
    "sec-ecmascript-function-objects",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:let thisMode = F.ThisMode
  |  1:if (= thisMode CONST_lexical) return undefined else 14:{}
  |  2:let calleeRealm = F.Realm
  |  3:access __x0__ = (calleeContext "LexicalEnvironment")
  |  3:let localEnv = __x0__
  |  5:if (= thisMode CONST_strict) let thisValue = thisArgument else if (|| (= thisArgument undefined) (= thisArgument null)) {
  |    7:let globalEnv = calleeRealm.GlobalEnv
  |    8:assert (is-instance-of globalEnv GlobalEnvironmentRecord)
  |    9:let thisValue = globalEnv.GlobalThisValue
  |  } else {
  |    11:app __x1__ = (ToObject thisArgument)
  |    11:let thisValue = [! __x1__]
  |  }
  |  13:assert (is-instance-of localEnv FunctionEnvironmentRecord)
  |  15:app __x2__ = (localEnv.BindThisValue localEnv thisValue)
  |  15:return __x2__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _thisMode_ be _F_.[[ThisMode]].""",
    """          1. If _thisMode_ is ~lexical~, return NormalCompletion(*undefined*).""",
    """          1. Let _calleeRealm_ be _F_.[[Realm]].""",
    """          1. Let _localEnv_ be the LexicalEnvironment of _calleeContext_.""",
    """          1. If _thisMode_ is ~strict~, let _thisValue_ be _thisArgument_.""",
    """          1. Else,""",
    """            1. If _thisArgument_ is *undefined* or *null*, then""",
    """              1. Let _globalEnv_ be _calleeRealm_.[[GlobalEnv]].""",
    """              1. Assert: _globalEnv_ is a global Environment Record.""",
    """              1. Let _thisValue_ be _globalEnv_.[[GlobalThisValue]].""",
    """            1. Else,""",
    """              1. Let _thisValue_ be ! ToObject(_thisArgument_).""",
    """              1. NOTE: ToObject produces wrapper objects using _calleeRealm_.""",
    """          1. Assert: _localEnv_ is a function Environment Record.""",
    """          1. Assert: The next step never returns an abrupt completion because _localEnv_.[[ThisBindingStatus]] is not ~initialized~.""",
    """          1. Return _localEnv_.BindThisValue(_thisValue_).""",
  )
}
