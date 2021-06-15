package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GeneratorExpression[0,0].InstantiateGeneratorFunctionExpression` extends Algo {
  val head = SyntaxDirectedHead("GeneratorExpression", 0, 0, Rhs(List(Terminal("function"), Terminal("*"), Terminal("("), NonTerminal("FormalParameters", List(""), false), Terminal(")"), Terminal("{"), NonTerminal("GeneratorBody", List(""), false), Terminal("}")), None), "InstantiateGeneratorFunctionExpression", List(Param("name", Optional)))
  val ids = List(
    "sec-runtime-semantics-instantiategeneratorfunctionexpression",
    "sec-generator-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:if (= name absent) name = "" else 0:{}
  |  1:access __x0__ = (CONTEXT "LexicalEnvironment")
  |  1:let scope = __x0__
  |  2:let sourceText = (get-syntax GeneratorExpression)
  |  3:app __x1__ = (OrdinaryFunctionCreate INTRINSIC_GeneratorFunction_prototype sourceText FormalParameters GeneratorBody CONST_nonDASHlexicalDASHthis scope)
  |  3:let closure = __x1__
  |  4:app __x2__ = (SetFunctionName closure name)
  |  4:__x2__
  |  5:app __x3__ = (OrdinaryObjectCreate INTRINSIC_GeneratorFunction_prototype_prototype)
  |  5:let prototype = [! __x3__]
  |  6:app __x4__ = (DefinePropertyOrThrow closure "prototype" (new PropertyDescriptor("Value" -> prototype, "Writable" -> true, "Enumerable" -> false, "Configurable" -> false)))
  |  6:__x4__
  |  7:return closure
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If _name_ is not present, set _name_ to *""*.""",
    """        1. Let _scope_ be the LexicalEnvironment of the running execution context.""",
    """        1. Let _sourceText_ be the source text matched by |GeneratorExpression|.""",
    """        1. Let _closure_ be OrdinaryFunctionCreate(%GeneratorFunction.prototype%, _sourceText_, |FormalParameters|, |GeneratorBody|, ~non-lexical-this~, _scope_).""",
    """        1. Perform SetFunctionName(_closure_, _name_).""",
    """        1. Let _prototype_ be ! OrdinaryObjectCreate(%GeneratorFunction.prototype.prototype%).""",
    """        1. Perform DefinePropertyOrThrow(_closure_, *"prototype"*, PropertyDescriptor { [[Value]]: _prototype_, [[Writable]]: *true*, [[Enumerable]]: *false*, [[Configurable]]: *false* }).""",
    """        1. Return _closure_.""",
  )
}
