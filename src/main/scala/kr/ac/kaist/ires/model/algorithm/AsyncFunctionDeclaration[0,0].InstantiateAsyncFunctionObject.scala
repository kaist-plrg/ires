package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncFunctionDeclaration[0,0].InstantiateAsyncFunctionObject` extends Algo {
  val head = SyntaxDirectedHead("AsyncFunctionDeclaration", 0, 0, Rhs(List(Terminal("async"), Terminal("function"), NonTerminal("BindingIdentifier", List(""), false), Terminal("("), NonTerminal("FormalParameters", List(""), false), Terminal(")"), Terminal("{"), NonTerminal("AsyncFunctionBody", List(""), false), Terminal("}")), None), "InstantiateAsyncFunctionObject", List(Param("scope", Normal)))
  val ids = List(
    "sec-runtime-semantics-instantiateasyncfunctionobject",
    "sec-async-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (BindingIdentifier "StringValue")
  |  0:let name = __x0__
  |  1:let sourceText = (get-syntax AsyncFunctionDeclaration)
  |  2:app __x1__ = (OrdinaryFunctionCreate INTRINSIC_AsyncFunction_prototype sourceText FormalParameters AsyncFunctionBody CONST_nonDASHlexicalDASHthis scope)
  |  2:let F = [! __x1__]
  |  3:app __x2__ = (SetFunctionName F name)
  |  3:[! __x2__]
  |  4:return F
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _name_ be StringValue of |BindingIdentifier|.""",
    """        1. Let _sourceText_ be the source text matched by |AsyncFunctionDeclaration|.""",
    """        1. Let _F_ be ! OrdinaryFunctionCreate(%AsyncFunction.prototype%, _sourceText_, |FormalParameters|, |AsyncFunctionBody|, ~non-lexical-this~, _scope_).""",
    """        1. Perform ! SetFunctionName(_F_, _name_).""",
    """        1. Return _F_.""",
  )
}
