package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BigInt.asUintN` extends Algo {
  val head = BuiltinHead(parseRef("""BigInt.asUintN"""), List(Param("bits", Normal), Param("bigint", Normal)))
  val ids = List(
    "sec-bigint.asuintn",
    "sec-properties-of-the-bigint-constructor",
    "sec-bigint-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToIndex bits)
  |  0:bits = [? __x0__]
  |  1:app __x1__ = (ToBigInt bigint)
  |  1:bigint = [? __x1__]
  |  2:return (convert (%% bigint (** 2.0 bits)) num2bigint )
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Set _bits_ to ? ToIndex(_bits_).""",
    """          1. Set _bigint_ to ? ToBigInt(_bigint_).""",
    """          1. Return the BigInt value that represents ℝ(_bigint_) modulo 2<sup>_bits_</sup>.""",
  )
}
