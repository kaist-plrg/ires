package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GetGeneratorKind` extends Algo {
  val head = NormalHead("GetGeneratorKind", List())
  val ids = List(
    "sec-getgeneratorkind",
    "sec-generator-abstract-operations",
    "sec-generator-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:let genContext = CONTEXT
  |  1:if (= genContext.Generator absent) return CONST_nonDASHgenerator else 16:{}
  |  2:let generator = genContext.Generator
  |  4:if (! (= generator.AsyncGeneratorState absent)) return CONST_async else return CONST_sync
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _genContext_ be the running execution context.""",
    """          1. If _genContext_ does not have a Generator component, return ~non-generator~.""",
    """          1. Let _generator_ be the Generator component of _genContext_.""",
    """          1. If _generator_ has an [[AsyncGeneratorState]] internal slot, return ~async~.""",
    """          1. Else, return ~sync~.""",
  )
}
