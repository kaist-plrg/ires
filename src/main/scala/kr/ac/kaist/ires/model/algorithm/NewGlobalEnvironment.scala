package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::NewGlobalEnvironment` extends Algo {
  val head = NormalHead("NewGlobalEnvironment", List(Param("G", Normal), Param("thisValue", Normal)))
  val ids = List(
    "sec-newglobalenvironment",
    "sec-environment-record-operations",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:let objRec = (new ObjectEnvironmentRecord("BindingObject" -> G))
  |  1:let dclRec = (new DeclarativeEnvironmentRecord())
  |  2:let env = (new GlobalEnvironmentRecord())
  |  3:env.ObjectRecord = objRec
  |  4:env.GlobalThisValue = thisValue
  |  5:env.DeclarativeRecord = dclRec
  |  6:env.VarNames = (new [])
  |  7:env.OuterEnv = null
  |  8:return env
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _objRec_ be a new object Environment Record containing _G_ as the binding object.""",
    """          1. Let _dclRec_ be a new declarative Environment Record containing no bindings.""",
    """          1. Let _env_ be a new global Environment Record.""",
    """          1. Set _env_.[[ObjectRecord]] to _objRec_.""",
    """          1. Set _env_.[[GlobalThisValue]] to _thisValue_.""",
    """          1. Set _env_.[[DeclarativeRecord]] to _dclRec_.""",
    """          1. Set _env_.[[VarNames]] to a new empty List.""",
    """          1. Set _env_.[[OuterEnv]] to *null*.""",
    """          1. Return _env_.""",
  )
}
