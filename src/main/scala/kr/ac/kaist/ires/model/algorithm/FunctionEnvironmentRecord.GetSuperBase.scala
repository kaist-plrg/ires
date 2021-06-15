package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FunctionEnvironmentRecord.GetSuperBase` extends Algo {
  val head = MethodHead("FunctionEnvironmentRecord", "GetSuperBase", Param("envRec", Normal), List())
  val ids = List(
    "sec-getsuperbase",
    "sec-function-environment-records",
    "sec-the-environment-record-type-hierarchy",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:let home = envRec.FunctionObject.HomeObject
  |  1:if (= home undefined) return undefined else 0:{}
  |  2:assert (= (typeof home) Object)
  |  3:app __x0__ = (home.GetPrototypeOf home)
  |  3:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _home_ be _envRec_.[[FunctionObject]].[[HomeObject]].""",
    """            1. If _home_ has the value *undefined*, return *undefined*.""",
    """            1. Assert: Type(_home_) is Object.""",
    """            1. Return ? _home_.[[GetPrototypeOf]]().""",
  )
}
