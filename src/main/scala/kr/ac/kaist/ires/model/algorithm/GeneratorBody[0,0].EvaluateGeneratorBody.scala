package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GeneratorBody[0,0].EvaluateGeneratorBody` extends Algo {
  val head = SyntaxDirectedHead("GeneratorBody", 0, 0, Rhs(List(NonTerminal("FunctionBody", List(""), false)), None), "EvaluateGeneratorBody", List(Param("functionObject", Normal), Param("argumentsList", Normal)))
  val ids = List(
    "sec-runtime-semantics-evaluategeneratorbody",
    "sec-generator-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (FunctionDeclarationInstantiation functionObject argumentsList)
  |  0:[? __x0__]
  |  1:app __x1__ = (OrdinaryCreateFromConstructor functionObject "%GeneratorFunction.prototype.prototype%" (new ["GeneratorState", "GeneratorContext", "GeneratorBrand"]))
  |  1:let G = [? __x1__]
  |  2:G.GeneratorBrand = CONST_empty
  |  3:app __x2__ = (GeneratorStart G FunctionBody)
  |  3:__x2__
  |  4:return (new Completion("Type" -> CONST_return, "Value" -> G, "Target" -> CONST_empty))
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Perform ? FunctionDeclarationInstantiation(_functionObject_, _argumentsList_).""",
    """        1. Let _G_ be ? OrdinaryCreateFromConstructor(_functionObject_, *"%GeneratorFunction.prototype.prototype%"*, « [[GeneratorState]], [[GeneratorContext]], [[GeneratorBrand]] »).""",
    """        1. Set _G_.[[GeneratorBrand]] to ~empty~.""",
    """        1. Perform GeneratorStart(_G_, |FunctionBody|).""",
    """        1. Return Completion { [[Type]]: ~return~, [[Value]]: _G_, [[Target]]: ~empty~ }.""",
  )
}
