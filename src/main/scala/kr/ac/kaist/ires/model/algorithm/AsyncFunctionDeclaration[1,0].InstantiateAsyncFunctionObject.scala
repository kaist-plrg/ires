package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncFunctionDeclaration[1,0].InstantiateAsyncFunctionObject` extends Algo {
  val head = SyntaxDirectedHead("AsyncFunctionDeclaration", 1, 0, Rhs(List(Terminal("async"), Terminal("function"), Terminal("("), NonTerminal("FormalParameters", List(""), false), Terminal(")"), Terminal("{"), NonTerminal("AsyncFunctionBody", List(""), false), Terminal("}")), None), "InstantiateAsyncFunctionObject", List(Param("scope", Normal)))
  val ids = List(
    "sec-runtime-semantics-instantiateasyncfunctionobject",
    "sec-async-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:let sourceText = (get-syntax AsyncFunctionDeclaration)
  |  1:app __x0__ = (OrdinaryFunctionCreate INTRINSIC_AsyncFunction_prototype sourceText FormalParameters AsyncFunctionBody CONST_nonDASHlexicalDASHthis scope)
  |  1:let F = [! __x0__]
  |  2:app __x1__ = (SetFunctionName F "default")
  |  2:[! __x1__]
  |  3:return F
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _sourceText_ be the source text matched by |AsyncFunctionDeclaration|.""",
    """        1. Let _F_ be ! OrdinaryFunctionCreate(%AsyncFunction.prototype%, _sourceText_, |FormalParameters|, |AsyncFunctionBody|, ~non-lexical-this~, _scope_).""",
    """        1. Perform ! SetFunctionName(_F_, *"default"*).""",
    """        1. Return _F_.""",
  )
}
