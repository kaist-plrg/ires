package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IsUnsignedElementType` extends Algo {
  val head = NormalHead("IsUnsignedElementType", List(Param("type", Normal)))
  val ids = List(
    "sec-isunsignedelementtype",
    "sec-abstract-operations-for-arraybuffer-objects",
    "sec-arraybuffer-objects",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:if (|| (|| (|| (|| (= type CONST_Uint8) (= type CONST_Uint8C)) (= type CONST_Uint16)) (= type CONST_Uint32)) (= type CONST_BigUint64)) return true else 0:{}
  |  1:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If _type_ is ~Uint8~, ~Uint8C~, ~Uint16~, ~Uint32~, or ~BigUint64~, return *true*.""",
    """          1. Return *false*.""",
  )
}
