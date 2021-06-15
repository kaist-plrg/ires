package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::OrdinaryCallEvaluateBody` extends Algo {
  val head = NormalHead("OrdinaryCallEvaluateBody", List(Param("F", Normal), Param("argumentsList", Normal)))
  val ids = List(
    "sec-ordinarycallevaluatebody",
    "sec-ecmascript-function-objects-call-thisargument-argumentslist",
    "sec-ecmascript-function-objects",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (F.ECMAScriptCode "EvaluateBody" F argumentsList)
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return the result of EvaluateBody of the parsed code that is _F_.[[ECMAScriptCode]] passing _F_ and _argumentsList_ as the arguments.""",
  )
}
