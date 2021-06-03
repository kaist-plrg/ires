package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ToBigUint64` extends Algo {
  val head = NormalHead("ToBigUint64", List(Param("argument", Normal)))
  val ids = List(
    "sec-tobiguint64",
    "sec-type-conversion",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToBigInt argument)
  |  0:let n = [? __x0__]
  |  1:let int64bit = (%% n (** 2.0 64i))
  |  2:return (convert int64bit num2bigint )
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _n_ be ? ToBigInt(_argument_).""",
    """        1. Let _int64bit_ be ℝ(_n_) modulo 2<sup>64</sup>.""",
    """        1. Return ℤ(_int64bit_).""",
  )
}
