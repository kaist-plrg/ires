package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ResolveBinding` extends Algo {
  val head = NormalHead("ResolveBinding", List(Param("name", Normal), Param("env", Optional)))
  val ids = List(
    "sec-resolvebinding",
    "sec-execution-contexts",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:if (|| (= env absent) (= env undefined)) env = CONTEXT.LexicalEnvironment else 0:{}
  |  2:assert (is-instance-of env EnvironmentRecord)
  |  3:if true let strict = true else let strict = false
  |  4:app __x0__ = (GetIdentifierReference env name strict)
  |  4:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If _env_ is not present or if _env_ is *undefined*, then""",
    """          1. Set _env_ to the running execution context's LexicalEnvironment.""",
    """        1. Assert: _env_ is an Environment Record.""",
    """        1. If the code matching the syntactic production that is being evaluated is contained in strict mode code, let _strict_ be *true*; else let _strict_ be *false*.""",
    """        1. Return ? GetIdentifierReference(_env_, _name_, _strict_).""",
  )
}
