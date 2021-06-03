package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BoundFunctionExoticObject.Construct` extends Algo {
  val head = MethodHead("BoundFunctionExoticObject", "Construct", Param("F", Normal), List(Param("argumentsList", Normal), Param("newTarget", Normal)))
  val ids = List(
    "sec-bound-function-exotic-objects-construct-argumentslist-newtarget",
    "sec-bound-function-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:let target = F.BoundTargetFunction
  |  1:app __x0__ = (IsConstructor target)
  |  1:assert (= __x0__ true)
  |  2:let boundArgs = F.BoundArguments
  |  3:let __x1__ = (copy-obj boundArgs)
  |  3:append argumentsList -> __x1__
  |  3:let args = __x1__
  |  4:app __x2__ = (SameValue F newTarget)
  |  4:if (= __x2__ true) newTarget = target else 0:{}
  |  5:app __x3__ = (Construct target args newTarget)
  |  5:return [? __x3__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _target_ be _F_.[[BoundTargetFunction]].""",
    """          1. Assert: IsConstructor(_target_) is *true*.""",
    """          1. Let _boundArgs_ be _F_.[[BoundArguments]].""",
    """          1. Let _args_ be a List whose elements are the elements of _boundArgs_, followed by the elements of _argumentsList_.""",
    """          1. If SameValue(_F_, _newTarget_) is *true*, set _newTarget_ to _target_.""",
    """          1. Return ? Construct(_target_, _args_, _newTarget_).""",
  )
}
