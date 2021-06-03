package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GetIdentifierReference` extends Algo {
  val head = NormalHead("GetIdentifierReference", List(Param("env", Normal), Param("name", Normal), Param("strict", Normal)))
  val ids = List(
    "sec-getidentifierreference",
    "sec-environment-record-operations",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:if (= env null) return (new ReferenceRecord("Base" -> CONST_unresolvable, "ReferencedName" -> name, "Strict" -> strict, "ThisValue" -> CONST_empty)) else 2:{}
  |  2:app __x0__ = (env.HasBinding env name)
  |  2:let exists = [? __x0__]
  |  5:if (= exists true) return (new ReferenceRecord("Base" -> env, "ReferencedName" -> name, "Strict" -> strict, "ThisValue" -> CONST_empty)) else {
  |    6:let outer = env.OuterEnv
  |    7:app __x1__ = (GetIdentifierReference outer name strict)
  |    7:return [? __x1__]
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If _env_ is the value *null*, then""",
    """            1. Return the Reference Record { [[Base]]: ~unresolvable~, [[ReferencedName]]: _name_, [[Strict]]: _strict_, [[ThisValue]]: ~empty~ }.""",
    """          1. Let _exists_ be ? _env_.HasBinding(_name_).""",
    """          1. If _exists_ is *true*, then""",
    """            1. Return the Reference Record { [[Base]]: _env_, [[ReferencedName]]: _name_, [[Strict]]: _strict_, [[ThisValue]]: ~empty~ }.""",
    """          1. Else,""",
    """            1. Let _outer_ be _env_.[[OuterEnv]].""",
    """            1. Return ? GetIdentifierReference(_outer_, _name_, _strict_).""",
  )
}
