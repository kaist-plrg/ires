package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IsBigIntElementType` extends Algo {
  val head = NormalHead("IsBigIntElementType", List(Param("type", Normal)))
  val ids = List(
    "sec-isbigintelementtype",
    "sec-abstract-operations-for-arraybuffer-objects",
    "sec-arraybuffer-objects",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:if (|| (= type CONST_BigUint64) (= type CONST_BigInt64)) return true else 0:{}
  |  1:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If _type_ is ~BigUint64~ or ~BigInt64~, return *true*.""",
    """          1. Return *false*.""",
  )
}
