package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Function.prototype[SYMBOL_hasInstance]` extends Algo {
  val head = BuiltinHead(parseRef("""Function.prototype[SYMBOL_hasInstance]"""), List(Param("V", Normal)))
  val ids = List(
    "sec-function.prototype-@@hasinstance",
    "sec-properties-of-the-function-prototype-object",
    "sec-function-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:let F = this
  |  1:app __x0__ = (OrdinaryHasInstance F V)
  |  1:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _F_ be the *this* value.""",
    """          1. Return ? OrdinaryHasInstance(_F_, _V_).""",
  )
}
