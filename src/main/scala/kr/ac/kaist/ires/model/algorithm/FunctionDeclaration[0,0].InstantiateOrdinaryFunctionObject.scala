package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FunctionDeclaration[0,0].InstantiateOrdinaryFunctionObject` extends Algo {
  val head = SyntaxDirectedHead("FunctionDeclaration", 0, 0, Rhs(List(Terminal("function"), NonTerminal("BindingIdentifier", List(""), false), Terminal("("), NonTerminal("FormalParameters", List(""), false), Terminal(")"), Terminal("{"), NonTerminal("FunctionBody", List(""), false), Terminal("}")), None), "InstantiateOrdinaryFunctionObject", List(Param("scope", Normal)))
  val ids = List(
    "sec-runtime-semantics-instantiateordinaryfunctionobject",
    "sec-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (BindingIdentifier "StringValue")
  |  0:let name = __x0__
  |  1:let sourceText = (get-syntax FunctionDeclaration)
  |  2:app __x1__ = (OrdinaryFunctionCreate INTRINSIC_Function_prototype sourceText FormalParameters FunctionBody CONST_nonDASHlexicalDASHthis scope)
  |  2:let F = __x1__
  |  3:app __x2__ = (SetFunctionName F name)
  |  3:__x2__
  |  4:app __x3__ = (MakeConstructor F)
  |  4:__x3__
  |  5:return F
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _name_ be StringValue of |BindingIdentifier|.""",
    """        1. Let _sourceText_ be the source text matched by |FunctionDeclaration|.""",
    """        1. Let _F_ be OrdinaryFunctionCreate(%Function.prototype%, _sourceText_, |FormalParameters|, |FunctionBody|, ~non-lexical-this~, _scope_).""",
    """        1. Perform SetFunctionName(_F_, _name_).""",
    """        1. Perform MakeConstructor(_F_).""",
    """        1. Return _F_.""",
  )
}
