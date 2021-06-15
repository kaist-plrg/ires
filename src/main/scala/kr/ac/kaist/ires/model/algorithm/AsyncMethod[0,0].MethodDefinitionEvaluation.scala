package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncMethod[0,0].MethodDefinitionEvaluation` extends Algo {
  val head = SyntaxDirectedHead("AsyncMethod", 0, 0, Rhs(List(Terminal("async"), NonTerminal("PropertyName", List(""), false), Terminal("("), NonTerminal("UniqueFormalParameters", List(""), false), Terminal(")"), Terminal("{"), NonTerminal("AsyncFunctionBody", List(""), false), Terminal("}")), None), "MethodDefinitionEvaluation", List(Param("object", Normal), Param("enumerable", Normal)))
  val ids = List(
    "sec-runtime-semantics-methoddefinitionevaluation",
    "sec-method-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (PropertyName "Evaluation")
  |  0:let propKey = __x0__
  |  1:[? propKey]
  |  2:access __x1__ = (CONTEXT "LexicalEnvironment")
  |  2:let scope = __x1__
  |  3:let sourceText = (get-syntax AsyncMethod)
  |  4:app __x2__ = (OrdinaryFunctionCreate INTRINSIC_AsyncFunction_prototype sourceText UniqueFormalParameters AsyncFunctionBody CONST_nonDASHlexicalDASHthis scope)
  |  4:let closure = [! __x2__]
  |  5:app __x3__ = (MakeMethod closure object)
  |  5:[! __x3__]
  |  6:app __x4__ = (SetFunctionName closure propKey)
  |  6:[! __x4__]
  |  7:let desc = (new PropertyDescriptor("Value" -> closure, "Writable" -> true, "Enumerable" -> enumerable, "Configurable" -> true))
  |  8:app __x5__ = (DefinePropertyOrThrow object propKey desc)
  |  8:return [? __x5__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _propKey_ be the result of evaluating |PropertyName|.""",
    """        1. ReturnIfAbrupt(_propKey_).""",
    """        1. Let _scope_ be the LexicalEnvironment of the running execution context.""",
    """        1. Let _sourceText_ be the source text matched by |AsyncMethod|.""",
    """        1. Let _closure_ be ! OrdinaryFunctionCreate(%AsyncFunction.prototype%, _sourceText_, |UniqueFormalParameters|, |AsyncFunctionBody|, ~non-lexical-this~, _scope_).""",
    """        1. Perform ! MakeMethod(_closure_, _object_).""",
    """        1. Perform ! SetFunctionName(_closure_, _propKey_).""",
    """        1. Let _desc_ be the PropertyDescriptor { [[Value]]: _closure_, [[Writable]]: *true*, [[Enumerable]]: _enumerable_, [[Configurable]]: *true* }.""",
    """        1. Return ? DefinePropertyOrThrow(_object_, _propKey_, _desc_).""",
  )
}
