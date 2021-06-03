package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IsUnclampedIntegerElementType` extends Algo {
  val head = NormalHead("IsUnclampedIntegerElementType", List(Param("type", Normal)))
  val ids = List(
    "sec-isunclampedintegerelementtype",
    "sec-abstract-operations-for-arraybuffer-objects",
    "sec-arraybuffer-objects",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:if (|| (|| (|| (|| (|| (= type CONST_Int8) (= type CONST_Uint8)) (= type CONST_Int16)) (= type CONST_Uint16)) (= type CONST_Int32)) (= type CONST_Uint32)) return true else 0:{}
  |  1:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If _type_ is ~Int8~, ~Uint8~, ~Int16~, ~Uint16~, ~Int32~, or ~Uint32~, return *true*.""",
    """          1. Return *false*.""",
  )
}
