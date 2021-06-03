package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BigInt::unsignedRightShift` extends Algo {
  val head = NormalHead("BigInt::unsignedRightShift", List(Param("x", Normal), Param("y", Normal)))
  val ids = List(
    "sec-numeric-types-bigint-unsignedRightShift",
    "sec-ecmascript-language-types-bigint-type",
    "sec-numeric-types",
    "sec-ecmascript-language-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""throw TypeError""".stripMargin)
  val code = scala.Array[String](
    """            1. Throw a *TypeError* exception.""",
  )
}
