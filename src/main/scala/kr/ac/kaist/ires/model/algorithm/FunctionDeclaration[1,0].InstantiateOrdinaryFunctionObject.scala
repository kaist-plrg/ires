package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FunctionDeclaration[1,0].InstantiateOrdinaryFunctionObject` extends Algo {
  val head = SyntaxDirectedHead("FunctionDeclaration", 1, 0, Rhs(List(Terminal("function"), Terminal("("), NonTerminal("FormalParameters", List(""), false), Terminal(")"), Terminal("{"), NonTerminal("FunctionBody", List(""), false), Terminal("}")), None), "InstantiateOrdinaryFunctionObject", List(Param("scope", Normal)))
  val ids = List(
    "sec-runtime-semantics-instantiateordinaryfunctionobject",
    "sec-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:let sourceText = (get-syntax FunctionDeclaration)
  |  1:app __x0__ = (OrdinaryFunctionCreate INTRINSIC_Function_prototype sourceText FormalParameters FunctionBody CONST_nonDASHlexicalDASHthis scope)
  |  1:let F = __x0__
  |  2:app __x1__ = (SetFunctionName F "default")
  |  2:__x1__
  |  3:app __x2__ = (MakeConstructor F)
  |  3:__x2__
  |  4:return F
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _sourceText_ be the source text matched by |FunctionDeclaration|.""",
    """        1. Let _F_ be OrdinaryFunctionCreate(%Function.prototype%, _sourceText_, |FormalParameters|, |FunctionBody|, ~non-lexical-this~, _scope_).""",
    """        1. Perform SetFunctionName(_F_, *"default"*).""",
    """        1. Perform MakeConstructor(_F_).""",
    """        1. Return _F_.""",
  )
}
