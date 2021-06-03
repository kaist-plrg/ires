package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GetThisEnvironment` extends Algo {
  val head = NormalHead("GetThisEnvironment", List())
  val ids = List(
    "sec-getthisenvironment",
    "sec-execution-contexts",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:let env = CONTEXT.LexicalEnvironment
  |  1:while true {
  |    2:app __x0__ = (env.HasThisBinding env)
  |    2:let exists = __x0__
  |    3:if (= exists true) return env else 2:{}
  |    4:let outer = env.OuterEnv
  |    5:assert (! (= outer null))
  |    6:env = outer
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _env_ be the running execution context's LexicalEnvironment.""",
    """        1. [id="step-getthisenvironment-loop"] Repeat,""",
    """          1. Let _exists_ be _env_.HasThisBinding().""",
    """          1. If _exists_ is *true*, return _env_.""",
    """          1. Let _outer_ be _env_.[[OuterEnv]].""",
    """          1. Assert: _outer_ is not *null*.""",
    """          1. Set _env_ to _outer_.""",
  )
}
