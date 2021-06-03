package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GeneratorMethod[0,0].MethodDefinitionEvaluation` extends Algo {
  val head = SyntaxDirectedHead("GeneratorMethod", 0, 0, Rhs(List(Terminal("*"), NonTerminal("PropertyName", List(""), false), Terminal("("), NonTerminal("UniqueFormalParameters", List(""), false), Terminal(")"), Terminal("{"), NonTerminal("GeneratorBody", List(""), false), Terminal("}")), None), "MethodDefinitionEvaluation", List(Param("object", Normal), Param("enumerable", Normal)))
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
  |  3:let sourceText = (get-syntax GeneratorMethod)
  |  4:app __x1__ = (OrdinaryFunctionCreate INTRINSIC_GeneratorFunction_prototype sourceText UniqueFormalParameters GeneratorBody CONST_nonDASHlexicalDASHthis scope)
  |  4:let closure = __x1__
  |  5:app __x2__ = (MakeMethod closure object)
  |  5:__x2__
  |  6:app __x3__ = (SetFunctionName closure propKey)
  |  6:__x3__
  |  7:app __x4__ = (OrdinaryObjectCreate INTRINSIC_GeneratorFunction_prototype_prototype)
  |  7:let prototype = [! __x4__]
  |  8:app __x5__ = (DefinePropertyOrThrow closure "prototype" (new PropertyDescriptor("Value" -> prototype, "Writable" -> true, "Enumerable" -> false, "Configurable" -> false)))
  |  8:__x5__
  |  9:let desc = (new PropertyDescriptor("Value" -> closure, "Writable" -> true, "Enumerable" -> enumerable, "Configurable" -> true))
  |  10:app __x6__ = (DefinePropertyOrThrow object propKey desc)
  |  10:return [? __x6__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _propKey_ be the result of evaluating |PropertyName|.""",
    """        1. ReturnIfAbrupt(_propKey_).""",
    """        1. Let _scope_ be the running execution context's LexicalEnvironment.""",
    """        1. Let _sourceText_ be the source text matched by |GeneratorMethod|.""",
    """        1. Let _closure_ be OrdinaryFunctionCreate(%GeneratorFunction.prototype%, _sourceText_, |UniqueFormalParameters|, |GeneratorBody|, ~non-lexical-this~, _scope_).""",
    """        1. Perform MakeMethod(_closure_, _object_).""",
    """        1. Perform SetFunctionName(_closure_, _propKey_).""",
    """        1. Let _prototype_ be ! OrdinaryObjectCreate(%GeneratorFunction.prototype.prototype%).""",
    """        1. Perform DefinePropertyOrThrow(_closure_, *"prototype"*, PropertyDescriptor { [[Value]]: _prototype_, [[Writable]]: *true*, [[Enumerable]]: *false*, [[Configurable]]: *false* }).""",
    """        1. Let _desc_ be the PropertyDescriptor { [[Value]]: _closure_, [[Writable]]: *true*, [[Enumerable]]: _enumerable_, [[Configurable]]: *true* }.""",
    """        1. Return ? DefinePropertyOrThrow(_object_, _propKey_, _desc_).""",
  )
}
