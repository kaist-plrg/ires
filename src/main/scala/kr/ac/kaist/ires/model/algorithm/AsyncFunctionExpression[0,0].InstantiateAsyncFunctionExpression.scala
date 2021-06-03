package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncFunctionExpression[0,0].InstantiateAsyncFunctionExpression` extends Algo {
  val head = SyntaxDirectedHead("AsyncFunctionExpression", 0, 0, Rhs(List(Terminal("async"), Terminal("function"), Terminal("("), NonTerminal("FormalParameters", List(""), false), Terminal(")"), Terminal("{"), NonTerminal("AsyncFunctionBody", List(""), false), Terminal("}")), None), "InstantiateAsyncFunctionExpression", List(Param("name", Optional)))
  val ids = List(
    "sec-runtime-semantics-instantiateasyncfunctionexpression",
    "sec-async-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:if (= name absent) name = "" else 8:{}
  |  1:access __x0__ = (CONTEXT "LexicalEnvironment")
  |  1:let scope = __x0__
  |  2:let sourceText = (get-syntax AsyncFunctionExpression)
  |  3:app __x1__ = (OrdinaryFunctionCreate INTRINSIC_AsyncFunction_prototype sourceText FormalParameters AsyncFunctionBody CONST_nonDASHlexicalDASHthis scope)
  |  3:let closure = [! __x1__]
  |  4:app __x2__ = (SetFunctionName closure name)
  |  4:__x2__
  |  5:return closure
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If _name_ is not present, set _name_ to *""*.""",
    """        1. Let _scope_ be the LexicalEnvironment of the running execution context.""",
    """        1. Let _sourceText_ be the source text matched by |AsyncFunctionExpression|.""",
    """        1. Let _closure_ be ! OrdinaryFunctionCreate(%AsyncFunction.prototype%, _sourceText_, |FormalParameters|, |AsyncFunctionBody|, ~non-lexical-this~, _scope_).""",
    """        1. Perform SetFunctionName(_closure_, _name_).""",
    """        1. Return _closure_.""",
  )
}
