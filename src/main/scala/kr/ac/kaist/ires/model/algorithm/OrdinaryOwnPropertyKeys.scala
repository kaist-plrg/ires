package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::OrdinaryOwnPropertyKeys` extends Algo {
  val head = NormalHead("OrdinaryOwnPropertyKeys", List(Param("O", Normal)))
  val ids = List(
    "sec-ordinaryownpropertykeys",
    "sec-ordinary-object-internal-methods-and-internal-slots-ownpropertykeys",
    "sec-ordinary-object-internal-methods-and-internal-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:let keys = (new [])
  |  1:??? "For each own property key id:{P} of id:{O} such that id:{P} is an array index , in ascending numeric index order , do in:{} out:{}"
  |  3:??? "For each own property key id:{P} of id:{O} such that Type ( id:{P} ) is String and id:{P} is not an array index , in ascending chronological order of property creation , do in:{} out:{}"
  |  5:??? "For each own property key id:{P} of id:{O} such that Type ( id:{P} ) is Symbol , in ascending chronological order of property creation , do in:{} out:{}"
  |  7:return keys
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _keys_ be a new empty List.""",
    """          1. For each own property key _P_ of _O_ such that _P_ is an array index, in ascending numeric index order, do""",
    """            1. Add _P_ as the last element of _keys_.""",
    """          1. For each own property key _P_ of _O_ such that Type(_P_) is String and _P_ is not an array index, in ascending chronological order of property creation, do""",
    """            1. Add _P_ as the last element of _keys_.""",
    """          1. For each own property key _P_ of _O_ such that Type(_P_) is Symbol, in ascending chronological order of property creation, do""",
    """            1. Add _P_ as the last element of _keys_.""",
    """          1. Return _keys_.""",
  )
}
