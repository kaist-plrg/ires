package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GetFunctionRealm` extends Algo {
  val head = NormalHead("GetFunctionRealm", List(Param("obj", Normal)))
  val ids = List(
    "sec-getfunctionrealm",
    "sec-operations-on-objects",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsCallable obj)
  |  0:assert (= [! __x0__] true)
  |  1:if (! (= obj.Realm absent)) return obj.Realm else 0:{}
  |  3:if (is-instance-of obj BoundFunctionExoticObject) {
  |    4:let target = obj.BoundTargetFunction
  |    5:app __x1__ = (GetFunctionRealm target)
  |    5:return [? __x1__]
  |  } else 0:{}
  |  6:if (is-instance-of obj ProxyExoticObject) {
  |    7:if (= obj.ProxyHandler null) throw TypeError else 0:{}
  |    8:let proxyTarget = obj.ProxyTarget
  |    9:app __x2__ = (GetFunctionRealm proxyTarget)
  |    9:return [? __x2__]
  |  } else 0:{}
  |  10:return REALM
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: ! IsCallable(_obj_) is *true*.""",
    """        1. If _obj_ has a [[Realm]] internal slot, then""",
    """          1. Return _obj_.[[Realm]].""",
    """        1. If _obj_ is a bound function exotic object, then""",
    """          1. Let _target_ be _obj_.[[BoundTargetFunction]].""",
    """          1. Return ? GetFunctionRealm(_target_).""",
    """        1. If _obj_ is a Proxy exotic object, then""",
    """          1. If _obj_.[[ProxyHandler]] is *null*, throw a *TypeError* exception.""",
    """          1. Let _proxyTarget_ be _obj_.[[ProxyTarget]].""",
    """          1. Return ? GetFunctionRealm(_proxyTarget_).""",
    """        1. [id="step-getfunctionrealm-default-return"] Return the current Realm Record.""",
  )
}
