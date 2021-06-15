package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncFunctionExpression[0,1].InstantiateAsyncFunctionExpression` extends Algo {
  val head = SyntaxDirectedHead("AsyncFunctionExpression", 0, 1, Rhs(List(Terminal("async"), Terminal("function"), NonTerminal("BindingIdentifier", List(""), false), Terminal("("), NonTerminal("FormalParameters", List(""), false), Terminal(")"), Terminal("{"), NonTerminal("AsyncFunctionBody", List(""), false), Terminal("}")), None), "InstantiateAsyncFunctionExpression", List(Param("name", Optional)))
  val ids = List(
    "sec-runtime-semantics-instantiateasyncfunctionexpression",
    "sec-async-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:assert (= name absent)
  |  1:access __x0__ = (BindingIdentifier "StringValue")
  |  1:name = __x0__
  |  2:access __x1__ = (CONTEXT "LexicalEnvironment")
  |  2:let scope = __x1__
  |  3:app __x2__ = (NewDeclarativeEnvironment scope)
  |  3:let funcEnv = [! __x2__]
  |  4:app __x3__ = (funcEnv.CreateImmutableBinding funcEnv name false)
  |  4:[! __x3__]
  |  5:let sourceText = (get-syntax AsyncFunctionExpression)
  |  6:app __x4__ = (OrdinaryFunctionCreate INTRINSIC_AsyncFunction_prototype sourceText FormalParameters AsyncFunctionBody CONST_nonDASHlexicalDASHthis funcEnv)
  |  6:let closure = [! __x4__]
  |  7:app __x5__ = (SetFunctionName closure name)
  |  7:[! __x5__]
  |  8:app __x6__ = (funcEnv.InitializeBinding funcEnv name closure)
  |  8:[! __x6__]
  |  9:return closure
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: _name_ is not present.""",
    """        1. Set _name_ to StringValue of |BindingIdentifier|.""",
    """        1. Let _scope_ be the LexicalEnvironment of the running execution context.""",
    """        1. Let _funcEnv_ be ! NewDeclarativeEnvironment(_scope_).""",
    """        1. Perform ! _funcEnv_.CreateImmutableBinding(_name_, *false*).""",
    """        1. Let _sourceText_ be the source text matched by |AsyncFunctionExpression|.""",
    """        1. Let _closure_ be ! OrdinaryFunctionCreate(%AsyncFunction.prototype%, _sourceText_, |FormalParameters|, |AsyncFunctionBody|, ~non-lexical-this~, _funcEnv_).""",
    """        1. Perform ! SetFunctionName(_closure_, _name_).""",
    """        1. Perform ! _funcEnv_.InitializeBinding(_name_, _closure_).""",
    """        1. Return _closure_.""",
  )
}
