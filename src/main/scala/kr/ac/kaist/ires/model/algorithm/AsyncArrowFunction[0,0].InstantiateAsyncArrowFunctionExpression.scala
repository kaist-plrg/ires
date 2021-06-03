package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncArrowFunction[0,0].InstantiateAsyncArrowFunctionExpression` extends Algo {
  val head = SyntaxDirectedHead("AsyncArrowFunction", 0, 0, Rhs(List(Terminal("async"), NonTerminal("AsyncArrowBindingIdentifier", List(""), false), Terminal("=>"), NonTerminal("AsyncConciseBody", List(""), false)), None), "InstantiateAsyncArrowFunctionExpression", List(Param("name", Optional)))
  val ids = List(
    "sec-runtime-semantics-instantiateasyncarrowfunctionexpression",
    "sec-async-arrow-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:if (= name absent) name = "" else 8:{}
  |  1:access __x0__ = (CONTEXT "LexicalEnvironment")
  |  1:let scope = __x0__
  |  2:let sourceText = (get-syntax AsyncArrowFunction)
  |  3:let parameters = AsyncArrowBindingIdentifier
  |  4:app __x1__ = (OrdinaryFunctionCreate INTRINSIC_AsyncFunction_prototype sourceText parameters AsyncConciseBody CONST_lexicalDASHthis scope)
  |  4:let closure = [! __x1__]
  |  5:app __x2__ = (SetFunctionName closure name)
  |  5:__x2__
  |  6:return closure
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If _name_ is not present, set _name_ to *""*.""",
    """        1. Let _scope_ be the LexicalEnvironment of the running execution context.""",
    """        1. Let _sourceText_ be the source text matched by |AsyncArrowFunction|.""",
    """        1. Let _parameters_ be |AsyncArrowBindingIdentifier|.""",
    """        1. Let _closure_ be ! OrdinaryFunctionCreate(%AsyncFunction.prototype%, _sourceText_, _parameters_, |AsyncConciseBody|, ~lexical-this~, _scope_).""",
    """        1. Perform SetFunctionName(_closure_, _name_).""",
    """        1. Return _closure_.""",
  )
}
