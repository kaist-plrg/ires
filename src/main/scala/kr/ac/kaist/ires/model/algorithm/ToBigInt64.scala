package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ToBigInt64` extends Algo {
  val head = NormalHead("ToBigInt64", List(Param("argument", Normal)))
  val ids = List(
    "sec-tobigint64",
    "sec-type-conversion",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToBigInt argument)
  |  0:let n = [? __x0__]
  |  1:let int64bit = (%% n (** 2.0 64i))
  |  2:if (! (< int64bit (** 2.0 63i))) return (convert (- int64bit (** 2.0 64i)) num2bigint ) else return (convert int64bit num2bigint )
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _n_ be ? ToBigInt(_argument_).""",
    """        1. Let _int64bit_ be ℝ(_n_) modulo 2<sup>64</sup>.""",
    """        1. If _int64bit_ ≥ 2<sup>63</sup>, return ℤ(_int64bit_ - 2<sup>64</sup>); otherwise return ℤ(_int64bit_).""",
  )
}
