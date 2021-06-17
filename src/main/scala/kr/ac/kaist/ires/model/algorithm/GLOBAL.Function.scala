package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Function` extends Algo {
  val head = BuiltinHead(parseRef("""Function"""), List())
  val ids = List(
    "sec-function-p1-p2-pn-body",
    "sec-function-constructor",
    "sec-function-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:let C = CONTEXT.Function
  |  1:let args = argumentsList
  |  2:app __x0__ = (CreateDynamicFunction C NewTarget CONST_normal args)
  |  2:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _C_ be the active function object.""",
    """          1. Let _args_ be the _argumentsList_ that was passed to this function by [[Call]] or [[Construct]].""",
    """          1. Return ? CreateDynamicFunction(_C_, NewTarget, ~normal~, _args_).""",
  )
}
