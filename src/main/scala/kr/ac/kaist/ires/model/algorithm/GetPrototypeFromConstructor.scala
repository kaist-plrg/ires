package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GetPrototypeFromConstructor` extends Algo {
  val head = NormalHead("GetPrototypeFromConstructor", List(Param("constructor", Normal), Param("intrinsicDefaultProto", Normal)))
  val ids = List(
    "sec-getprototypefromconstructor",
    "sec-ordinary-object-internal-methods-and-internal-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  1:app __x0__ = (IsCallable constructor)
  |  1:assert (= __x0__ true)
  |  2:app __x1__ = (Get constructor "prototype")
  |  2:let proto = [? __x1__]
  |  3:if (! (= (typeof proto) Object)) {
  |    4:app __x2__ = (GetFunctionRealm constructor)
  |    4:let realm = [? __x2__]
  |    5:proto = INTRINSICS[intrinsicDefaultProto]
  |  } else 0:{}
  |  6:return proto
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: _intrinsicDefaultProto_ is a String value that is this specification's name of an intrinsic object. The corresponding object must be an intrinsic that is intended to be used as the [[Prototype]] value of an object.""",
    """        1. Assert: IsCallable(_constructor_) is *true*.""",
    """        1. Let _proto_ be ? Get(_constructor_, *"prototype"*).""",
    """        1. If Type(_proto_) is not Object, then""",
    """          1. Let _realm_ be ? GetFunctionRealm(_constructor_).""",
    """          1. Set _proto_ to _realm_'s intrinsic object named _intrinsicDefaultProto_.""",
    """        1. Return _proto_.""",
  )
}
