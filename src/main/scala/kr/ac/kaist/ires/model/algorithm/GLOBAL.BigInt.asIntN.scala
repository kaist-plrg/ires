package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.BigInt.asIntN` extends Algo {
  val head = BuiltinHead(parseRef("""BigInt.asIntN"""), List(Param("bits", Normal), Param("bigint", Normal)))
  val ids = List(
    "sec-bigint.asintn",
    "sec-properties-of-the-bigint-constructor",
    "sec-bigint-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToIndex bits)
  |  0:bits = [? __x0__]
  |  1:app __x1__ = (ToBigInt bigint)
  |  1:bigint = [? __x1__]
  |  2:let mod = (%% bigint (** 2.0 bits))
  |  3:if (! (< mod (** 2.0 (- bits 1i)))) return (convert (- mod (** 2.0 bits)) num2bigint ) else return (convert mod num2bigint )
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Set _bits_ to ? ToIndex(_bits_).""",
    """          1. Set _bigint_ to ? ToBigInt(_bigint_).""",
    """          1. Let _mod_ be ℝ(_bigint_) modulo 2<sup>_bits_</sup>.""",
    """          1. If _mod_ ≥ 2<sup>_bits_ - 1</sup>, return ℤ(_mod_ - 2<sup>_bits_</sup>); otherwise, return ℤ(_mod_).""",
  )
}
