package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::OrdinaryDefineOwnProperty` extends Algo {
  val head = NormalHead("OrdinaryDefineOwnProperty", List(Param("O", Normal), Param("P", Normal), Param("Desc", Normal)))
  val ids = List(
    "sec-ordinarydefineownproperty",
    "sec-ordinary-object-internal-methods-and-internal-slots-defineownproperty-p-desc",
    "sec-ordinary-object-internal-methods-and-internal-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (O.GetOwnProperty O P)
  |  0:let current = [? __x0__]
  |  1:app __x1__ = (IsExtensible O)
  |  1:let extensible = [? __x1__]
  |  2:app __x2__ = (ValidateAndApplyPropertyDescriptor O P extensible Desc current)
  |  2:return __x2__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _current_ be ? _O_.[[GetOwnProperty]](_P_).""",
    """          1. Let _extensible_ be ? IsExtensible(_O_).""",
    """          1. Return ValidateAndApplyPropertyDescriptor(_O_, _P_, _extensible_, _Desc_, _current_).""",
  )
}
