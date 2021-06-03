package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FunctionExpression[0,0].InstantiateOrdinaryFunctionExpression` extends Algo {
  val head = SyntaxDirectedHead("FunctionExpression", 0, 0, Rhs(List(Terminal("function"), Terminal("("), NonTerminal("FormalParameters", List(""), false), Terminal(")"), Terminal("{"), NonTerminal("FunctionBody", List(""), false), Terminal("}")), None), "InstantiateOrdinaryFunctionExpression", List(Param("name", Optional)))
  val ids = List(
    "sec-runtime-semantics-instantiateordinaryfunctionexpression",
    "sec-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:if (= name absent) name = "" else 0:{}
  |  1:access __x0__ = (CONTEXT "LexicalEnvironment")
  |  1:let scope = __x0__
  |  2:let sourceText = (get-syntax FunctionExpression)
  |  3:app __x1__ = (OrdinaryFunctionCreate INTRINSIC_Function_prototype sourceText FormalParameters FunctionBody CONST_nonDASHlexicalDASHthis scope)
  |  3:let closure = __x1__
  |  4:app __x2__ = (SetFunctionName closure name)
  |  4:__x2__
  |  5:app __x3__ = (MakeConstructor closure)
  |  5:__x3__
  |  6:return closure
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If _name_ is not present, set _name_ to *""*.""",
    """        1. Let _scope_ be the LexicalEnvironment of the running execution context.""",
    """        1. Let _sourceText_ be the source text matched by |FunctionExpression|.""",
    """        1. Let _closure_ be OrdinaryFunctionCreate(%Function.prototype%, _sourceText_, |FormalParameters|, |FunctionBody|, ~non-lexical-this~, _scope_).""",
    """        1. Perform SetFunctionName(_closure_, _name_).""",
    """        1. Perform MakeConstructor(_closure_).""",
    """        1. Return _closure_.""",
  )
}
