package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::NewModuleEnvironment` extends Algo {
  val head = NormalHead("NewModuleEnvironment", List(Param("E", Normal)))
  val ids = List(
    "sec-newmoduleenvironment",
    "sec-environment-record-operations",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:let env = (new ModuleEnvironmentRecord("SubMap" -> (new SubMap())))
  |  1:env.OuterEnv = E
  |  2:return env
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _env_ be a new module Environment Record containing no bindings.""",
    """          1. Set _env_.[[OuterEnv]] to _E_.""",
    """          1. Return _env_.""",
  )
}
