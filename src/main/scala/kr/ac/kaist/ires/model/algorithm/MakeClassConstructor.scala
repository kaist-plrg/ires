package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::MakeClassConstructor` extends Algo {
  val head = NormalHead("MakeClassConstructor", List(Param("F", Normal)))
  val ids = List(
    "sec-makeclassconstructor",
    "sec-ecmascript-function-objects",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:assert (is-instance-of F ECMAScriptFunctionObject)
  |  1:assert (= F.IsClassConstructor false)
  |  2:F.IsClassConstructor = true
  |  3:return undefined
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: _F_ is an ECMAScript function object.""",
    """        1. Assert: _F_.[[IsClassConstructor]] is *false*.""",
    """        1. Set _F_.[[IsClassConstructor]] to *true*.""",
    """        1. Return NormalCompletion(*undefined*).""",
  )
}
