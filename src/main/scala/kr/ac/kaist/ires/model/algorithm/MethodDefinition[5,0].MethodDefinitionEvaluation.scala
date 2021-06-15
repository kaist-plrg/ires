package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::MethodDefinition[5,0].MethodDefinitionEvaluation` extends Algo {
  val head = SyntaxDirectedHead("MethodDefinition", 5, 0, Rhs(List(Terminal("set"), NonTerminal("PropertyName", List(""), false), Terminal("("), NonTerminal("PropertySetParameterList", List(""), false), Terminal(")"), Terminal("{"), NonTerminal("FunctionBody", List(""), false), Terminal("}")), None), "MethodDefinitionEvaluation", List(Param("object", Normal), Param("enumerable", Normal)))
  val ids = List(
    "sec-runtime-semantics-methoddefinitionevaluation",
    "sec-method-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (PropertyName "Evaluation")
  |  0:let propKey = __x0__
  |  1:[? propKey]
  |  2:let scope = CONTEXT.LexicalEnvironment
  |  3:let sourceText = (get-syntax MethodDefinition)
  |  4:app __x1__ = (OrdinaryFunctionCreate INTRINSIC_Function_prototype sourceText PropertySetParameterList FunctionBody CONST_nonDASHlexicalDASHthis scope)
  |  4:let closure = __x1__
  |  5:app __x2__ = (MakeMethod closure object)
  |  5:__x2__
  |  6:app __x3__ = (SetFunctionName closure propKey "set")
  |  6:__x3__
  |  7:let desc = (new PropertyDescriptor("Set" -> closure, "Enumerable" -> enumerable, "Configurable" -> true))
  |  8:app __x4__ = (DefinePropertyOrThrow object propKey desc)
  |  8:return [? __x4__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _propKey_ be the result of evaluating |PropertyName|.""",
    """        1. ReturnIfAbrupt(_propKey_).""",
    """        1. Let _scope_ be the running execution context's LexicalEnvironment.""",
    """        1. Let _sourceText_ be the source text matched by |MethodDefinition|.""",
    """        1. Let _closure_ be OrdinaryFunctionCreate(%Function.prototype%, _sourceText_, |PropertySetParameterList|, |FunctionBody|, ~non-lexical-this~, _scope_).""",
    """        1. Perform MakeMethod(_closure_, _object_).""",
    """        1. Perform SetFunctionName(_closure_, _propKey_, *"set"*).""",
    """        1. Let _desc_ be the PropertyDescriptor { [[Set]]: _closure_, [[Enumerable]]: _enumerable_, [[Configurable]]: *true* }.""",
    """        1. Return ? DefinePropertyOrThrow(_object_, _propKey_, _desc_).""",
  )
}
