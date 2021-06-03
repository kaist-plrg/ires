package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IsPropertyKey` extends Algo {
  val head = NormalHead("IsPropertyKey", List(Param("argument", Normal)))
  val ids = List(
    "sec-ispropertykey",
    "sec-testing-and-comparison-operations",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:if (= (typeof argument) String) return true else 0:{}
  |  1:if (= (typeof argument) Symbol) return true else 0:{}
  |  2:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If Type(_argument_) is String, return *true*.""",
    """        1. If Type(_argument_) is Symbol, return *true*.""",
    """        1. Return *false*.""",
  )
}
