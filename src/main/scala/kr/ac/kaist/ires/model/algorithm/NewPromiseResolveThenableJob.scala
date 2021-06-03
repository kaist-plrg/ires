package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::NewPromiseResolveThenableJob` extends Algo {
  val head = NormalHead("NewPromiseResolveThenableJob", List(Param("promiseToResolve", Normal), Param("thenable", Normal), Param("then", Normal)))
  val ids = List(
    "sec-newpromiseresolvethenablejob",
    "sec-promise-jobs",
    "sec-promise-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:??? "Let id:{job} be a new Job Abstract Closure with no parameters that captures id:{promiseToResolve} , id:{thenable} , and id:{then} and performs the following steps when called : in:{} out:{}"
  |  7:app __x0__ = (GetFunctionRealm then.Callback)
  |  7:let getThenRealmResult = __x0__
  |  9:if (&& (is-completion getThenRealmResult) (= getThenRealmResult.Type CONST_normal)) let thenRealm = getThenRealmResult.Value else let thenRealm = REALM
  |  11:return (new Record("Job" -> job, "Realm" -> thenRealm))
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _job_ be a new Job Abstract Closure with no parameters that captures _promiseToResolve_, _thenable_, and _then_ and performs the following steps when called:""",
    """            1. Let _resolvingFunctions_ be CreateResolvingFunctions(_promiseToResolve_).""",
    """            1. Let _thenCallResult_ be HostCallJobCallback(_then_, _thenable_, « _resolvingFunctions_.[[Resolve]], _resolvingFunctions_.[[Reject]] »).""",
    """            1. If _thenCallResult_ is an abrupt completion, then""",
    """              1. Let _status_ be Call(_resolvingFunctions_.[[Reject]], *undefined*, « _thenCallResult_.[[Value]] »).""",
    """              1. Return Completion(_status_).""",
    """            1. Return Completion(_thenCallResult_).""",
    """          1. Let _getThenRealmResult_ be GetFunctionRealm(_then_.[[Callback]]).""",
    """          1. If _getThenRealmResult_ is a normal completion, let _thenRealm_ be _getThenRealmResult_.[[Value]].""",
    """          1. Else, let _thenRealm_ be the current Realm Record.""",
    """          1. NOTE: _thenRealm_ is never *null*. When _then_.[[Callback]] is a revoked Proxy and no code runs, _thenRealm_ is used to create error objects.""",
    """          1. Return the Record { [[Job]]: _job_, [[Realm]]: _thenRealm_ }.""",
  )
}
