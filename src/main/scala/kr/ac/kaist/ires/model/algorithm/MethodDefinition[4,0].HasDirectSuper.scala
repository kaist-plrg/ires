package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::MethodDefinition[4,0].HasDirectSuper` extends Algo {
  val head = SyntaxDirectedHead("MethodDefinition", 4, 0, Rhs(List(Terminal("get"), NonTerminal("PropertyName", List(""), false), Terminal("("), Terminal(")"), Terminal("{"), NonTerminal("FunctionBody", List(""), false), Terminal("}")), None), "HasDirectSuper", List())
  val ids = List(
    "sec-static-semantics-hasdirectsuper",
    "sec-method-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (FunctionBody "Contains" "SuperCall")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return |FunctionBody| Contains |SuperCall|.""",
  )
}
