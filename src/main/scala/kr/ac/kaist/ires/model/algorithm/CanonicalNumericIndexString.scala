package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CanonicalNumericIndexString` extends Algo {
  val head = NormalHead("CanonicalNumericIndexString", List(Param("argument", Normal)))
  val ids = List(
    "sec-canonicalnumericindexstring",
    "sec-type-conversion",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof argument) String)
  |  1:if (= argument "-0") return -0.0 else 0:{}
  |  2:app __x0__ = (ToNumber argument)
  |  2:let n = [! __x0__]
  |  3:app __x1__ = (ToString n)
  |  3:app __x2__ = (SameValue [! __x1__] argument)
  |  3:if (= __x2__ false) return undefined else 0:{}
  |  4:return n
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: Type(_argument_) is String.""",
    """        1. If _argument_ is *"-0"*, return *-0*<sub>𝔽</sub>.""",
    """        1. Let _n_ be ! ToNumber(_argument_).""",
    """        1. If SameValue(! ToString(_n_), _argument_) is *false*, return *undefined*.""",
    """        1. Return _n_.""",
  )
}
