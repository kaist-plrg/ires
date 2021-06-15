package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FormalParameter[0,0].IsSimpleParameterList` extends Algo {
  val head = SyntaxDirectedHead("FormalParameter", 0, 0, Rhs(List(NonTerminal("BindingElement", List(""), false)), None), "IsSimpleParameterList", List())
  val ids = List(
    "sec-static-semantics-issimpleparameterlist",
    "sec-parameter-lists",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (BindingElement "IsSimpleParameterList")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return IsSimpleParameterList of |BindingElement|.""",
  )
}
