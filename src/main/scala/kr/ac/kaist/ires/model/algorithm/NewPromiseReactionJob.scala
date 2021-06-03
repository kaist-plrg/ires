package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::NewPromiseReactionJob` extends Algo {
  val head = NormalHead("NewPromiseReactionJob", List(Param("reaction", Normal), Param("argument", Normal)))
  val ids = List(
    "sec-newpromisereactionjob",
    "sec-promise-jobs",
    "sec-promise-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:??? "Let id:{job} be a new Job Abstract Closure with no parameters that captures id:{reaction} and id:{argument} and performs the following steps when called : in:{} out:{}"
  |  20:let handlerRealm = null
  |  21:if (! (= reaction.Handler CONST_empty)) {
  |    22:app __x0__ = (GetFunctionRealm reaction.Handler.Callback)
  |    22:let getHandlerRealmResult = __x0__
  |    24:if (&& (is-completion getHandlerRealmResult) (= getHandlerRealmResult.Type CONST_normal)) handlerRealm = getHandlerRealmResult.Value else handlerRealm = REALM
  |  } else 25:{}
  |  26:return (new Record("Job" -> job, "Realm" -> handlerRealm))
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _job_ be a new Job Abstract Closure with no parameters that captures _reaction_ and _argument_ and performs the following steps when called:""",
    """            1. Assert: _reaction_ is a PromiseReaction Record.""",
    """            1. Let _promiseCapability_ be _reaction_.[[Capability]].""",
    """            1. Let _type_ be _reaction_.[[Type]].""",
    """            1. Let _handler_ be _reaction_.[[Handler]].""",
    """            1. If _handler_ is ~empty~, then""",
    """              1. If _type_ is ~Fulfill~, let _handlerResult_ be NormalCompletion(_argument_).""",
    """              1. Else,""",
    """                1. Assert: _type_ is ~Reject~.""",
    """                1. Let _handlerResult_ be ThrowCompletion(_argument_).""",
    """            1. Else, let _handlerResult_ be HostCallJobCallback(_handler_, *undefined*, « _argument_ »).""",
    """            1. If _promiseCapability_ is *undefined*, then""",
    """              1. Assert: _handlerResult_ is not an abrupt completion.""",
    """              1. Return NormalCompletion(~empty~).""",
    """            1. Assert: _promiseCapability_ is a PromiseCapability Record.""",
    """            1. If _handlerResult_ is an abrupt completion, then""",
    """              1. Let _status_ be Call(_promiseCapability_.[[Reject]], *undefined*, « _handlerResult_.[[Value]] »).""",
    """            1. Else,""",
    """              1. Let _status_ be Call(_promiseCapability_.[[Resolve]], *undefined*, « _handlerResult_.[[Value]] »).""",
    """            1. Return Completion(_status_).""",
    """          1. Let _handlerRealm_ be *null*.""",
    """          1. If _reaction_.[[Handler]] is not ~empty~, then""",
    """            1. Let _getHandlerRealmResult_ be GetFunctionRealm(_reaction_.[[Handler]].[[Callback]]).""",
    """            1. If _getHandlerRealmResult_ is a normal completion, set _handlerRealm_ to _getHandlerRealmResult_.[[Value]].""",
    """            1. Else, set _handlerRealm_ to the current Realm Record.""",
    """            1. NOTE: _handlerRealm_ is never *null* unless the handler is *undefined*. When the handler is a revoked Proxy and no ECMAScript code runs, _handlerRealm_ is used to create error objects.""",
    """          1. Return the Record { [[Job]]: _job_, [[Realm]]: _handlerRealm_ }.""",
  )
}
