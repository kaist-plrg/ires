package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncGeneratorDeclaration[1,0].InstantiateAsyncGeneratorFunctionObject` extends Algo {
  val head = SyntaxDirectedHead("AsyncGeneratorDeclaration", 1, 0, Rhs(List(Terminal("async"), Terminal("function"), Terminal("*"), Terminal("("), NonTerminal("FormalParameters", List(""), false), Terminal(")"), Terminal("{"), NonTerminal("AsyncGeneratorBody", List(""), false), Terminal("}")), None), "InstantiateAsyncGeneratorFunctionObject", List(Param("scope", Normal)))
  val ids = List(
    "sec-runtime-semantics-instantiateasyncgeneratorfunctionobject",
    "sec-async-generator-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:let sourceText = (get-syntax AsyncGeneratorDeclaration)
  |  1:app __x0__ = (OrdinaryFunctionCreate INTRINSIC_AsyncGeneratorFunction_prototype sourceText FormalParameters AsyncGeneratorBody CONST_nonDASHlexicalDASHthis scope)
  |  1:let F = __x0__
  |  2:app __x1__ = (SetFunctionName F "default")
  |  2:__x1__
  |  3:app __x2__ = (OrdinaryObjectCreate INTRINSIC_AsyncGeneratorFunction_prototype_prototype)
  |  3:let prototype = [! __x2__]
  |  4:app __x3__ = (DefinePropertyOrThrow F "prototype" (new PropertyDescriptor("Value" -> prototype, "Writable" -> true, "Enumerable" -> false, "Configurable" -> false)))
  |  4:__x3__
  |  5:return F
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _sourceText_ be the source text matched by |AsyncGeneratorDeclaration|.""",
    """        1. Let _F_ be OrdinaryFunctionCreate(%AsyncGeneratorFunction.prototype%, _sourceText_, |FormalParameters|, |AsyncGeneratorBody|, ~non-lexical-this~, _scope_).""",
    """        1. Perform SetFunctionName(_F_, *"default"*).""",
    """        1. Let _prototype_ be ! OrdinaryObjectCreate(%AsyncGeneratorFunction.prototype.prototype%).""",
    """        1. Perform DefinePropertyOrThrow(_F_, *"prototype"*, PropertyDescriptor { [[Value]]: _prototype_, [[Writable]]: *true*, [[Enumerable]]: *false*, [[Configurable]]: *false* }).""",
    """        1. Return _F_.""",
  )
}
