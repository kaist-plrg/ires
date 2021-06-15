package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::MakeArgGetter` extends Algo {
  val head = NormalHead("MakeArgGetter", List(Param("name", Normal), Param("env", Normal)))
  val ids = List(
    "sec-makearggetter",
    "sec-createmappedargumentsobject",
    "sec-arguments-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:let f = CONTEXT.Function
  |  1:let name = f.Name
  |  2:let env = f.Env
  |  3:app __x0__ = (env.GetBindingValue env name false)
  |  3:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _f_ be the active function object.""",
    """            1. Let _name_ be _f_.[[Name]].""",
    """            1. Let _env_ be _f_.[[Env]].""",
    """            1. Return _env_.GetBindingValue(_name_, *false*).""",
  )
}
