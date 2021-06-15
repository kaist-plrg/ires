package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::StringExoticObject.DefineOwnProperty` extends Algo {
  val head = MethodHead("StringExoticObject", "DefineOwnProperty", Param("S", Normal), List(Param("P", Normal), Param("Desc", Normal)))
  val ids = List(
    "sec-string-exotic-objects-defineownproperty-p-desc",
    "sec-string-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsPropertyKey P)
  |  0:assert (= __x0__ true)
  |  1:app __x1__ = (StringGetOwnProperty S P)
  |  1:let stringDesc = [! __x1__]
  |  2:if (! (= stringDesc undefined)) {
  |    3:let extensible = S.Extensible
  |    4:app __x2__ = (IsCompatiblePropertyDescriptor extensible Desc stringDesc)
  |    4:return [! __x2__]
  |  } else 16:{}
  |  5:app __x3__ = (OrdinaryDefineOwnProperty S P Desc)
  |  5:return [! __x3__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: IsPropertyKey(_P_) is *true*.""",
    """          1. Let _stringDesc_ be ! StringGetOwnProperty(_S_, _P_).""",
    """          1. If _stringDesc_ is not *undefined*, then""",
    """            1. Let _extensible_ be _S_.[[Extensible]].""",
    """            1. Return ! IsCompatiblePropertyDescriptor(_extensible_, _Desc_, _stringDesc_).""",
    """          1. Return ! OrdinaryDefineOwnProperty(_S_, _P_, _Desc_).""",
  )
}
