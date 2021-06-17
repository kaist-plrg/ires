package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::NewDeclarativeEnvironment` extends Algo {
  val head = NormalHead("NewDeclarativeEnvironment", List(Param("E", Normal)))
  val ids = List(
    "sec-newdeclarativeenvironment",
    "sec-environment-record-operations",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:let env = (new DeclarativeEnvironmentRecord())
  |  1:env.OuterEnv = E
  |  2:return env
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _env_ be a new declarative Environment Record containing no bindings.""",
    """          1. Set _env_.[[OuterEnv]] to _E_.""",
    """          1. Return _env_.""",
  )
}
