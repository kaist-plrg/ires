package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SplitMatch` extends Algo {
  val head = NormalHead("SplitMatch", List(Param("S", Normal), Param("q", Normal), Param("R", Normal)))
  val ids = List(
    "sec-splitmatch",
    "sec-string.prototype.split",
    "sec-properties-of-the-string-prototype-object",
    "sec-string-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:let r = R.length
  |  1:let s = S.length
  |  2:if (< s (+ q r)) return CONST_notDASHmatched else 25:{}
  |  3:??? "If there exists an integer id:{i} between 0 ( inclusive ) and id:{r} ( exclusive ) such that the code unit at index id:{q} + id:{i} within id:{S} is different from the code unit at index id:{i} within id:{R} , return const:{not-matched} ."
  |  4:return (+ q r)
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _r_ be the number of code units in _R_.""",
    """            1. Let _s_ be the number of code units in _S_.""",
    """            1. If _q_ + _r_ > _s_, return ~not-matched~.""",
    """            1. If there exists an integer _i_ between 0 (inclusive) and _r_ (exclusive) such that the code unit at index _q_ + _i_ within _S_ is different from the code unit at index _i_ within _R_, return ~not-matched~.""",
    """            1. Return _q_ + _r_.""",
  )
}
