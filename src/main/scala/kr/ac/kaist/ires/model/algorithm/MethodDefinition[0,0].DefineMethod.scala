package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::MethodDefinition[0,0].DefineMethod` extends Algo {
  val head = SyntaxDirectedHead("MethodDefinition", 0, 0, Rhs(List(NonTerminal("PropertyName", List(""), false), Terminal("("), NonTerminal("UniqueFormalParameters", List(""), false), Terminal(")"), Terminal("{"), NonTerminal("FunctionBody", List(""), false), Terminal("}")), None), "DefineMethod", List(Param("object", Normal), Param("functionPrototype", Optional)))
  val ids = List(
    "sec-runtime-semantics-definemethod",
    "sec-method-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (PropertyName "Evaluation")
  |  0:let propKey = __x0__
  |  1:[? propKey]
  |  2:let scope = CONTEXT.LexicalEnvironment
  |  5:if (! (= functionPrototype absent)) let prototype = functionPrototype else let prototype = INTRINSIC_Function_prototype
  |  7:let sourceText = (get-syntax MethodDefinition)
  |  8:app __x1__ = (OrdinaryFunctionCreate prototype sourceText UniqueFormalParameters FunctionBody CONST_nonDASHlexicalDASHthis scope)
  |  8:let closure = __x1__
  |  9:app __x2__ = (MakeMethod closure object)
  |  9:__x2__
  |  10:return (new Record("Key" -> propKey, "Closure" -> closure))
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _propKey_ be the result of evaluating |PropertyName|.""",
    """        1. ReturnIfAbrupt(_propKey_).""",
    """        1. Let _scope_ be the running execution context's LexicalEnvironment.""",
    """        1. If _functionPrototype_ is present, then""",
    """          1. Let _prototype_ be _functionPrototype_.""",
    """        1. Else,""",
    """          1. Let _prototype_ be %Function.prototype%.""",
    """        1. Let _sourceText_ be the source text matched by |MethodDefinition|.""",
    """        1. Let _closure_ be OrdinaryFunctionCreate(_prototype_, _sourceText_, |UniqueFormalParameters|, |FunctionBody|, ~non-lexical-this~, _scope_).""",
    """        1. Perform MakeMethod(_closure_, _object_).""",
    """        1. Return the Record { [[Key]]: _propKey_, [[Closure]]: _closure_ }.""",
  )
}
