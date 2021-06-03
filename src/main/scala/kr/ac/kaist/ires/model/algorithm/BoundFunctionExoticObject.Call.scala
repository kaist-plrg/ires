package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BoundFunctionExoticObject.Call` extends Algo {
  val head = MethodHead("BoundFunctionExoticObject", "Call", Param("F", Normal), List(Param("thisArgument", Normal), Param("argumentsList", Normal)))
  val ids = List(
    "sec-bound-function-exotic-objects-call-thisargument-argumentslist",
    "sec-bound-function-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:let target = F.BoundTargetFunction
  |  1:let boundThis = F.BoundThis
  |  2:let boundArgs = F.BoundArguments
  |  3:let __x0__ = (copy-obj boundArgs)
  |  3:append argumentsList -> __x0__
  |  3:let args = __x0__
  |  4:app __x1__ = (Call target boundThis args)
  |  4:return [? __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _target_ be _F_.[[BoundTargetFunction]].""",
    """          1. Let _boundThis_ be _F_.[[BoundThis]].""",
    """          1. Let _boundArgs_ be _F_.[[BoundArguments]].""",
    """          1. Let _args_ be a List whose elements are the elements of _boundArgs_, followed by the elements of _argumentsList_.""",
    """          1. Return ? Call(_target_, _boundThis_, _args_).""",
  )
}
