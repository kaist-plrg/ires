package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArrowParameters[0,0].ExpectedArgumentCount` extends Algo {
  val head = SyntaxDirectedHead("ArrowParameters", 0, 0, Rhs(List(NonTerminal("BindingIdentifier", List(""), false)), None), "ExpectedArgumentCount", List())
  val ids = List(
    "sec-static-semantics-expectedargumentcount",
    "sec-parameter-lists",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""return 1i""".stripMargin)
  val code = scala.Array[String](
    """        1. Return 1.""",
  )
}
