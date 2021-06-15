package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GeneratorFunction` extends Algo {
  val head = BuiltinHead(parseRef("""GeneratorFunction"""), List())
  val ids = List(
    "sec-generatorfunction",
    "sec-generatorfunction-constructor",
    "sec-generatorfunction-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:let C = CONTEXT.Function
  |  1:let args = argumentsList
  |  2:app __x0__ = (CreateDynamicFunction C NewTarget CONST_generator args)
  |  2:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _C_ be the active function object.""",
    """          1. Let _args_ be the _argumentsList_ that was passed to this function by [[Call]] or [[Construct]].""",
    """          1. Return ? CreateDynamicFunction(_C_, NewTarget, ~generator~, _args_).""",
  )
}
