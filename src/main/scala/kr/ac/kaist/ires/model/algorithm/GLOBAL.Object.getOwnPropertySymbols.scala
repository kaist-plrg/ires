package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Object.getOwnPropertySymbols` extends Algo {
  val head = BuiltinHead(parseRef("""Object.getOwnPropertySymbols"""), List(Param("O", Normal)))
  val ids = List(
    "sec-object.getownpropertysymbols",
    "sec-properties-of-the-object-constructor",
    "sec-object-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (GetOwnPropertyKeys O CONST_symbol)
  |  0:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return ? GetOwnPropertyKeys(_O_, ~symbol~).""",
  )
}
