package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BigInt::remainder` extends Algo {
  val head = NormalHead("BigInt::remainder", List(Param("n", Normal), Param("d", Normal)))
  val ids = List(
    "sec-numeric-types-bigint-remainder",
    "sec-ecmascript-language-types-bigint-type",
    "sec-numeric-types",
    "sec-ecmascript-language-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:if (= d 0i) throw RangeError else 9:{}
  |  1:if (= n 0i) return 0i else 9:{}
  |  2:let r = (% n d)
  |  3:return r
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. If _d_ is *0*<sub>ℤ</sub>, throw a *RangeError* exception.""",
    """            1. If _n_ is *0*<sub>ℤ</sub>, return *0*<sub>ℤ</sub>.""",
    """            1. Let _r_ be the BigInt defined by the mathematical relation _r_ = _n_ - (_d_ × _q_) where _q_ is a BigInt that is negative only if _n_/_d_ is negative and positive only if _n_/_d_ is positive, and whose magnitude is as large as possible without exceeding the magnitude of the true mathematical quotient of _n_ and _d_.""",
    """            1. Return _r_.""",
  )
}
