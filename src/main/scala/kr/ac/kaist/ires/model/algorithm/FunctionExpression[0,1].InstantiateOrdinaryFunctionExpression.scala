package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FunctionExpression[0,1].InstantiateOrdinaryFunctionExpression` extends Algo {
  val head = SyntaxDirectedHead("FunctionExpression", 0, 1, Rhs(List(Terminal("function"), NonTerminal("BindingIdentifier", List(""), false), Terminal("("), NonTerminal("FormalParameters", List(""), false), Terminal(")"), Terminal("{"), NonTerminal("FunctionBody", List(""), false), Terminal("}")), None), "InstantiateOrdinaryFunctionExpression", List(Param("name", Optional)))
  val ids = List(
    "sec-runtime-semantics-instantiateordinaryfunctionexpression",
    "sec-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:assert (= name absent)
  |  1:access __x0__ = (BindingIdentifier "StringValue")
  |  1:name = __x0__
  |  2:let scope = CONTEXT.LexicalEnvironment
  |  3:app __x1__ = (NewDeclarativeEnvironment scope)
  |  3:let funcEnv = __x1__
  |  4:app __x2__ = (funcEnv.CreateImmutableBinding funcEnv name false)
  |  4:__x2__
  |  5:let sourceText = (get-syntax FunctionExpression)
  |  6:app __x3__ = (OrdinaryFunctionCreate INTRINSIC_Function_prototype sourceText FormalParameters FunctionBody CONST_nonDASHlexicalDASHthis funcEnv)
  |  6:let closure = __x3__
  |  7:app __x4__ = (SetFunctionName closure name)
  |  7:__x4__
  |  8:app __x5__ = (MakeConstructor closure)
  |  8:__x5__
  |  9:app __x6__ = (funcEnv.InitializeBinding funcEnv name closure)
  |  9:__x6__
  |  10:return closure
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: _name_ is not present.""",
    """        1. Set _name_ to StringValue of |BindingIdentifier|.""",
    """        1. Let _scope_ be the running execution context's LexicalEnvironment.""",
    """        1. Let _funcEnv_ be NewDeclarativeEnvironment(_scope_).""",
    """        1. Perform _funcEnv_.CreateImmutableBinding(_name_, *false*).""",
    """        1. Let _sourceText_ be the source text matched by |FunctionExpression|.""",
    """        1. Let _closure_ be OrdinaryFunctionCreate(%Function.prototype%, _sourceText_, |FormalParameters|, |FunctionBody|, ~non-lexical-this~, _funcEnv_).""",
    """        1. Perform SetFunctionName(_closure_, _name_).""",
    """        1. Perform MakeConstructor(_closure_).""",
    """        1. Perform _funcEnv_.InitializeBinding(_name_, _closure_).""",
    """        1. Return _closure_.""",
  )
}
