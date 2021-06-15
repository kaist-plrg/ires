package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FormalParameters[0,0].ExpectedArgumentCount` extends Algo {
  val head = SyntaxDirectedHead("FormalParameters", 0, 0, Rhs(List(EmptyToken), None), "ExpectedArgumentCount", List())
  val ids = List(
    "sec-static-semantics-expectedargumentcount",
    "sec-parameter-lists",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""return 0i""".stripMargin)
  val code = scala.Array[String](
    """        1. Return 0.""",
  )
}
