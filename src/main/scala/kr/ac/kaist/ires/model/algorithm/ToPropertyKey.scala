package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ToPropertyKey` extends Algo {
  val head = NormalHead("ToPropertyKey", List(Param("argument", Normal)))
  val ids = List(
    "sec-topropertykey",
    "sec-type-conversion",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToPrimitive argument CONST_string)
  |  0:let key = [? __x0__]
  |  1:if (= (typeof key) Symbol) return key else 0:{}
  |  3:app __x1__ = (ToString key)
  |  3:return [! __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _key_ be ? ToPrimitive(_argument_, ~string~).""",
    """        1. If Type(_key_) is Symbol, then""",
    """          1. Return _key_.""",
    """        1. Return ! ToString(_key_).""",
  )
}
