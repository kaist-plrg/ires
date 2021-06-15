package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncArrowFunction[1,0].InstantiateAsyncArrowFunctionExpression` extends Algo {
  val head = SyntaxDirectedHead("AsyncArrowFunction", 1, 0, Rhs(List(NonTerminal("CoverCallExpressionAndAsyncArrowHead", List(""), false), Terminal("=>"), NonTerminal("AsyncConciseBody", List(""), false)), None), "InstantiateAsyncArrowFunctionExpression", List(Param("name", Optional)))
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
  |  3:access __x1__ = (CoverCallExpressionAndAsyncArrowHead "CoveredAsyncArrowHead")
  |  3:let head = __x1__
  |  4:access __x2__ = (head "ArrowFormalParameters")
  |  4:let parameters = __x2__
  |  5:app __x3__ = (OrdinaryFunctionCreate INTRINSIC_AsyncFunction_prototype sourceText parameters AsyncConciseBody CONST_lexicalDASHthis scope)
  |  5:let closure = [! __x3__]
  |  6:app __x4__ = (SetFunctionName closure name)
  |  6:__x4__
  |  7:return closure
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If _name_ is not present, set _name_ to *""*.""",
    """        1. Let _scope_ be the LexicalEnvironment of the running execution context.""",
    """        1. Let _sourceText_ be the source text matched by |AsyncArrowFunction|.""",
    """        1. Let _head_ be CoveredAsyncArrowHead of |CoverCallExpressionAndAsyncArrowHead|.""",
    """        1. Let _parameters_ be the |ArrowFormalParameters| of _head_.""",
    """        1. Let _closure_ be ! OrdinaryFunctionCreate(%AsyncFunction.prototype%, _sourceText_, _parameters_, |AsyncConciseBody|, ~lexical-this~, _scope_).""",
    """        1. Perform SetFunctionName(_closure_, _name_).""",
    """        1. Return _closure_.""",
  )
}
