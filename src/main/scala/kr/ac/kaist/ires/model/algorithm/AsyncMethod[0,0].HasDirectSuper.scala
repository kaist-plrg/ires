package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncMethod[0,0].HasDirectSuper` extends Algo {
  val head = SyntaxDirectedHead("AsyncMethod", 0, 0, Rhs(List(Terminal("async"), NonTerminal("PropertyName", List(""), false), Terminal("("), NonTerminal("UniqueFormalParameters", List(""), false), Terminal(")"), Terminal("{"), NonTerminal("AsyncFunctionBody", List(""), false), Terminal("}")), None), "HasDirectSuper", List())
  val ids = List(
    "sec-static-semantics-hasdirectsuper",
    "sec-method-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (UniqueFormalParameters "Contains" "SuperCall")
  |  0:if (= __x0__ true) return true else 0:{}
  |  1:access __x1__ = (AsyncFunctionBody "Contains" "SuperCall")
  |  1:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If |UniqueFormalParameters| Contains |SuperCall| is *true*, return *true*.""",
    """        1. Return |AsyncFunctionBody| Contains |SuperCall|.""",
  )
}
