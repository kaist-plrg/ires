package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::MethodDefinition[0,0].MethodDefinitionEvaluation` extends Algo {
  val head = SyntaxDirectedHead("MethodDefinition", 0, 0, Rhs(List(NonTerminal("PropertyName", List(""), false), Terminal("("), NonTerminal("UniqueFormalParameters", List(""), false), Terminal(")"), Terminal("{"), NonTerminal("FunctionBody", List(""), false), Terminal("}")), None), "MethodDefinitionEvaluation", List(Param("object", Normal), Param("enumerable", Normal)))
  val ids = List(
    "sec-runtime-semantics-methoddefinitionevaluation",
    "sec-method-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (MethodDefinition "DefineMethod" object)
  |  0:let methodDef = [? __x0__]
  |  1:app __x1__ = (SetFunctionName methodDef.Closure methodDef.Key)
  |  1:__x1__
  |  2:let desc = (new PropertyDescriptor("Value" -> methodDef.Closure, "Writable" -> true, "Enumerable" -> enumerable, "Configurable" -> true))
  |  3:app __x2__ = (DefinePropertyOrThrow object methodDef.Key desc)
  |  3:return [? __x2__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _methodDef_ be ? DefineMethod of |MethodDefinition| with argument _object_.""",
    """        1. Perform SetFunctionName(_methodDef_.[[Closure]], _methodDef_.[[Key]]).""",
    """        1. Let _desc_ be the PropertyDescriptor { [[Value]]: _methodDef_.[[Closure]], [[Writable]]: *true*, [[Enumerable]]: _enumerable_, [[Configurable]]: *true* }.""",
    """        1. Return ? DefinePropertyOrThrow(_object_, _methodDef_.[[Key]], _desc_).""",
  )
}
