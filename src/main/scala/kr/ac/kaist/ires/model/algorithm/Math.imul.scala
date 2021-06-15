package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Math.imul` extends Algo {
  val head = BuiltinHead(parseRef("""Math.imul"""), List(Param("x", Normal), Param("y", Normal)))
  val ids = List(
    "sec-math.imul",
    "sec-function-properties-of-the-math-object",
    "sec-math-object",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToUint32 x)
  |  0:let a = [? __x0__]
  |  1:app __x1__ = (ToUint32 y)
  |  1:let b = [? __x1__]
  |  2:let product = (%% (* a b) (** 2.0 32i))
  |  3:if (! (< product (** 2.0 31i))) return (- product (** 2.0 32i)) else return product
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _a_ be ℝ(? ToUint32(_x_)).""",
    """          1. Let _b_ be ℝ(? ToUint32(_y_)).""",
    """          1. Let _product_ be (_a_ × _b_) modulo 2<sup>32</sup>.""",
    """          1. If _product_ ≥ 2<sup>31</sup>, return 𝔽(_product_ - 2<sup>32</sup>); otherwise return 𝔽(_product_).""",
  )
}
