package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FormalParameters[0,0].IsSimpleParameterList` extends Algo {
  val head = SyntaxDirectedHead("FormalParameters", 0, 0, Rhs(List(EmptyToken), None), "IsSimpleParameterList", List())
  val ids = List(
    "sec-static-semantics-issimpleparameterlist",
    "sec-parameter-lists",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""return true""".stripMargin)
  val code = scala.Array[String](
    """        1. Return *true*.""",
  )
}
