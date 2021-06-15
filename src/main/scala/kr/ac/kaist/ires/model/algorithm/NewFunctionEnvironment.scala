package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::NewFunctionEnvironment` extends Algo {
  val head = NormalHead("NewFunctionEnvironment", List(Param("F", Normal), Param("newTarget", Normal)))
  val ids = List(
    "sec-newfunctionenvironment",
    "sec-environment-record-operations",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  1:assert (|| (= (typeof newTarget) Undefined) (= (typeof newTarget) Object))
  |  2:let env = (new FunctionEnvironmentRecord("SubMap" -> (new SubMap())))
  |  3:env.FunctionObject = F
  |  5:if (= F.ThisMode CONST_lexical) env.ThisBindingStatus = CONST_lexical else env.ThisBindingStatus = CONST_uninitialized
  |  6:env.NewTarget = newTarget
  |  7:env.OuterEnv = F.Environment
  |  8:return env
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: _F_ is an ECMAScript function.""",
    """          1. Assert: Type(_newTarget_) is Undefined or Object.""",
    """          1. Let _env_ be a new function Environment Record containing no bindings.""",
    """          1. Set _env_.[[FunctionObject]] to _F_.""",
    """          1. If _F_.[[ThisMode]] is ~lexical~, set _env_.[[ThisBindingStatus]] to ~lexical~.""",
    """          1. Else, set _env_.[[ThisBindingStatus]] to ~uninitialized~.""",
    """          1. Set _env_.[[NewTarget]] to _newTarget_.""",
    """          1. Set _env_.[[OuterEnv]] to _F_.[[Environment]].""",
    """          1. Return _env_.""",
  )
}
