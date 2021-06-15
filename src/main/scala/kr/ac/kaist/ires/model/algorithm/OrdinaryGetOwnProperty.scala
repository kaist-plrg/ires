package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::OrdinaryGetOwnProperty` extends Algo {
  val head = NormalHead("OrdinaryGetOwnProperty", List(Param("O", Normal), Param("P", Normal)))
  val ids = List(
    "sec-ordinarygetownproperty",
    "sec-ordinary-object-internal-methods-and-internal-slots-getownproperty-p",
    "sec-ordinary-object-internal-methods-and-internal-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsPropertyKey P)
  |  0:assert (= __x0__ true)
  |  1:if (= O.SubMap[P] absent) return undefined else 0:{}
  |  2:let D = (new PropertyDescriptor("SubMap" -> (new SubMap())))
  |  3:let X = O.SubMap[P]
  |  7:app __x1__ = (IsDataDescriptor X)
  |  7:if __x1__ {
  |    5:D.Value = X.Value
  |    6:D.Writable = X.Writable
  |  } else {
  |    8:app __x2__ = (IsAccessorDescriptor X)
  |    8:assert __x2__
  |    9:D.Get = X.Get
  |    10:D.Set = X.Set
  |  }
  |  11:D.Enumerable = X.Enumerable
  |  12:D.Configurable = X.Configurable
  |  13:return D
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: IsPropertyKey(_P_) is *true*.""",
    """          1. If _O_ does not have an own property with key _P_, return *undefined*.""",
    """          1. Let _D_ be a newly created Property Descriptor with no fields.""",
    """          1. Let _X_ be _O_'s own property whose key is _P_.""",
    """          1. If _X_ is a data property, then""",
    """            1. Set _D_.[[Value]] to the value of _X_'s [[Value]] attribute.""",
    """            1. Set _D_.[[Writable]] to the value of _X_'s [[Writable]] attribute.""",
    """          1. Else,""",
    """            1. Assert: _X_ is an accessor property.""",
    """            1. Set _D_.[[Get]] to the value of _X_'s [[Get]] attribute.""",
    """            1. Set _D_.[[Set]] to the value of _X_'s [[Set]] attribute.""",
    """          1. Set _D_.[[Enumerable]] to the value of _X_'s [[Enumerable]] attribute.""",
    """          1. Set _D_.[[Configurable]] to the value of _X_'s [[Configurable]] attribute.""",
    """          1. Return _D_.""",
  )
}
