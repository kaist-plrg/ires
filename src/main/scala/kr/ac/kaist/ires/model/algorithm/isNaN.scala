package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::isNaN` extends Algo {
  val head = BuiltinHead(parseRef("""isNaN"""), List(Param("number", Normal)))
  val ids = List(
    "sec-isnan-number",
    "sec-function-properties-of-the-global-object",
    "sec-global-object",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToNumber number)
  |  0:let num = [? __x0__]
  |  2:if (= num NaN) return true else return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _num_ be ? ToNumber(_number_).""",
    """        1. If _num_ is *NaN*, return *true*.""",
    """        1. Otherwise, return *false*.""",
  )
}
