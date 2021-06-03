package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::TypedArrayCreate` extends Algo {
  val head = NormalHead("TypedArrayCreate", List(Param("constructor", Normal), Param("argumentList", Normal)))
  val ids = List(
    "typedarray-create",
    "sec-abstract-operations-for-typedarray-objects",
    "sec-typedarray-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (Construct constructor argumentList)
  |  0:let newTypedArray = [? __x0__]
  |  1:app __x1__ = (ValidateTypedArray newTypedArray)
  |  1:[? __x1__]
  |  2:??? "If id:{argumentList} is a List of a single Number , then in:{} out:{}"
  |  4:return newTypedArray
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _newTypedArray_ be ? Construct(_constructor_, _argumentList_).""",
    """          1. Perform ? ValidateTypedArray(_newTypedArray_).""",
    """          1. If _argumentList_ is a List of a single Number, then""",
    """            1. If _newTypedArray_.[[ArrayLength]] < ℝ(_argumentList_[0]), throw a *TypeError* exception.""",
    """          1. Return _newTypedArray_.""",
  )
}
