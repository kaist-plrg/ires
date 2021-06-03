package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::InitializeBoundName` extends Algo {
  val head = NormalHead("InitializeBoundName", List(Param("name", Normal), Param("value", Normal), Param("environment", Normal)))
  val ids = List(
    "sec-initializeboundname",
    "sec-runtime-semantics-bindinginitialization",
    "sec-syntax-directed-operations-miscellaneous",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof name) String)
  |  4:if (! (= environment undefined)) {
  |    2:app __x0__ = (environment.InitializeBinding environment name value)
  |    2:__x0__
  |    3:return undefined
  |  } else {
  |    5:app __x1__ = (ResolveBinding name)
  |    5:let lhs = __x1__
  |    6:app __x2__ = (PutValue lhs value)
  |    6:return [? __x2__]
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: Type(_name_) is String.""",
    """          1. If _environment_ is not *undefined*, then""",
    """            1. Perform _environment_.InitializeBinding(_name_, _value_).""",
    """            1. Return NormalCompletion(*undefined*).""",
    """          1. Else,""",
    """            1. Let _lhs_ be ResolveBinding(_name_).""",
    """            1. Return ? PutValue(_lhs_, _value_).""",
  )
}
