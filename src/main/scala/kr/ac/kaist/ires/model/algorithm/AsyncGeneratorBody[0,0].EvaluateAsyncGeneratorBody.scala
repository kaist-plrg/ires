package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncGeneratorBody[0,0].EvaluateAsyncGeneratorBody` extends Algo {
  val head = SyntaxDirectedHead("AsyncGeneratorBody", 0, 0, Rhs(List(NonTerminal("FunctionBody", List(""), false)), None), "EvaluateAsyncGeneratorBody", List(Param("functionObject", Normal), Param("argumentsList", Normal)))
  val ids = List(
    "sec-runtime-semantics-evaluateasyncgeneratorbody",
    "sec-async-generator-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (FunctionDeclarationInstantiation functionObject argumentsList)
  |  0:[? __x0__]
  |  1:app __x1__ = (OrdinaryCreateFromConstructor functionObject "%AsyncGeneratorFunction.prototype.prototype%" (new ["AsyncGeneratorState", "AsyncGeneratorContext", "AsyncGeneratorQueue", "GeneratorBrand"]))
  |  1:let generator = [? __x1__]
  |  2:generator.GeneratorBrand = CONST_empty
  |  3:app __x2__ = (AsyncGeneratorStart generator FunctionBody)
  |  3:[! __x2__]
  |  4:return (new Completion("Type" -> CONST_return, "Value" -> generator, "Target" -> CONST_empty))
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Perform ? FunctionDeclarationInstantiation(_functionObject_, _argumentsList_).""",
    """        1. Let _generator_ be ? OrdinaryCreateFromConstructor(_functionObject_, *"%AsyncGeneratorFunction.prototype.prototype%"*, « [[AsyncGeneratorState]], [[AsyncGeneratorContext]], [[AsyncGeneratorQueue]], [[GeneratorBrand]] »).""",
    """        1. Set _generator_.[[GeneratorBrand]] to ~empty~.""",
    """        1. Perform ! AsyncGeneratorStart(_generator_, |FunctionBody|).""",
    """        1. Return Completion { [[Type]]: ~return~, [[Value]]: _generator_, [[Target]]: ~empty~ }.""",
  )
}
