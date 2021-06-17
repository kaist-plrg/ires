package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::NewObjectEnvironment` extends Algo {
  val head = NormalHead("NewObjectEnvironment", List(Param("O", Normal), Param("E", Normal)))
  val ids = List(
    "sec-newobjectenvironment",
    "sec-environment-record-operations",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:let env = (new ObjectEnvironmentRecord("BindingObject" -> O))
  |  1:env.OuterEnv = E
  |  2:return env
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _env_ be a new object Environment Record containing _O_ as the binding object.""",
    """          1. Set _env_.[[OuterEnv]] to _E_.""",
    """          1. Return _env_.""",
  )
}
