package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GeneratorDeclaration[0,0].InstantiateGeneratorFunctionObject` extends Algo {
  val head = SyntaxDirectedHead("GeneratorDeclaration", 0, 0, Rhs(List(Terminal("function"), Terminal("*"), NonTerminal("BindingIdentifier", List(""), false), Terminal("("), NonTerminal("FormalParameters", List(""), false), Terminal(")"), Terminal("{"), NonTerminal("GeneratorBody", List(""), false), Terminal("}")), None), "InstantiateGeneratorFunctionObject", List(Param("scope", Normal)))
  val ids = List(
    "sec-runtime-semantics-instantiategeneratorfunctionobject",
    "sec-generator-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (BindingIdentifier "StringValue")
  |  0:let name = __x0__
  |  1:let sourceText = (get-syntax GeneratorDeclaration)
  |  2:app __x1__ = (OrdinaryFunctionCreate INTRINSIC_GeneratorFunction_prototype sourceText FormalParameters GeneratorBody CONST_nonDASHlexicalDASHthis scope)
  |  2:let F = __x1__
  |  3:app __x2__ = (SetFunctionName F name)
  |  3:__x2__
  |  4:app __x3__ = (OrdinaryObjectCreate INTRINSIC_GeneratorFunction_prototype_prototype)
  |  4:let prototype = [! __x3__]
  |  5:app __x4__ = (DefinePropertyOrThrow F "prototype" (new PropertyDescriptor("Value" -> prototype, "Writable" -> true, "Enumerable" -> false, "Configurable" -> false)))
  |  5:__x4__
  |  6:return F
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _name_ be StringValue of |BindingIdentifier|.""",
    """        1. Let _sourceText_ be the source text matched by |GeneratorDeclaration|.""",
    """        1. Let _F_ be OrdinaryFunctionCreate(%GeneratorFunction.prototype%, _sourceText_, |FormalParameters|, |GeneratorBody|, ~non-lexical-this~, _scope_).""",
    """        1. Perform SetFunctionName(_F_, _name_).""",
    """        1. Let _prototype_ be ! OrdinaryObjectCreate(%GeneratorFunction.prototype.prototype%).""",
    """        1. Perform DefinePropertyOrThrow(_F_, *"prototype"*, PropertyDescriptor { [[Value]]: _prototype_, [[Writable]]: *true*, [[Enumerable]]: *false*, [[Configurable]]: *false* }).""",
    """        1. Return _F_.""",
  )
}
