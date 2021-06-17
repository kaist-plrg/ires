package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Number` extends Algo {
  val head = BuiltinHead(parseRef("""Number"""), List(Param("value", Normal)))
  val ids = List(
    "sec-number-constructor-number-value",
    "sec-number-constructor",
    "sec-number-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  4:if (! (= value absent)) {
  |    1:app __x0__ = (ToNumeric value)
  |    1:let prim = [? __x0__]
  |    3:if (= (typeof prim) BigInt) let n = prim else let n = prim
  |  } else let n = 0i
  |  6:if (= NewTarget undefined) return n else 3:{}
  |  7:app __x1__ = (OrdinaryCreateFromConstructor NewTarget "%Number.prototype%" (new ["NumberData"]))
  |  7:let O = [? __x1__]
  |  8:O.NumberData = n
  |  9:return O
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If _value_ is present, then""",
    """            1. Let _prim_ be ? ToNumeric(_value_).""",
    """            1. If Type(_prim_) is BigInt, let _n_ be 𝔽(ℝ(_prim_)).""",
    """            1. Otherwise, let _n_ be _prim_.""",
    """          1. Else,""",
    """            1. Let _n_ be *+0*<sub>𝔽</sub>.""",
    """          1. If NewTarget is *undefined*, return _n_.""",
    """          1. Let _O_ be ? OrdinaryCreateFromConstructor(NewTarget, *"%Number.prototype%"*, « [[NumberData]] »).""",
    """          1. Set _O_.[[NumberData]] to _n_.""",
    """          1. Return _O_.""",
  )
}
