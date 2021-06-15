package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Yield` extends Algo {
  val head = NormalHead("Yield", List(Param("value", Normal)))
  val ids = List(
    "sec-yield",
    "sec-generator-abstract-operations",
    "sec-generator-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (GetGeneratorKind)
  |  0:let generatorKind = [! __x0__]
  |  2:if (= generatorKind CONST_async) {
  |    app __x1__ = (AsyncGeneratorYield value)
  |    return [? __x1__]
  |  } else {
  |    app __x2__ = (CreateIterResultObject value false)
  |    app __x3__ = (GeneratorYield [! __x2__])
  |    return [? __x3__]
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _generatorKind_ be ! GetGeneratorKind().""",
    """          1. If _generatorKind_ is ~async~, return ? AsyncGeneratorYield(_value_).""",
    """          1. Otherwise, return ? GeneratorYield(! CreateIterResultObject(_value_, *false*)).""",
  )
}
