package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SetRealmGlobalObject` extends Algo {
  val head = NormalHead("SetRealmGlobalObject", List(Param("realmRec", Normal), Param("globalObj", Normal), Param("thisValue", Normal)))
  val ids = List(
    "sec-setrealmglobalobject",
    "sec-code-realms",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:if (= globalObj undefined) {
  |    1:let intrinsics = realmRec.Intrinsics
  |    2:app __x0__ = (OrdinaryObjectCreate intrinsics.INTRINSIC_Object_prototype)
  |    2:globalObj = [! __x0__]
  |  } else 0:{}
  |  3:assert (= (typeof globalObj) Object)
  |  4:if (= thisValue undefined) thisValue = globalObj else 0:{}
  |  5:realmRec.GlobalObject = globalObj
  |  6:app __x1__ = (NewGlobalEnvironment globalObj thisValue)
  |  6:let newGlobalEnv = __x1__
  |  7:realmRec.GlobalEnv = newGlobalEnv
  |  8:return realmRec
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If _globalObj_ is *undefined*, then""",
    """          1. Let _intrinsics_ be _realmRec_.[[Intrinsics]].""",
    """          1. Set _globalObj_ to ! OrdinaryObjectCreate(_intrinsics_.[[%Object.prototype%]]).""",
    """        1. Assert: Type(_globalObj_) is Object.""",
    """        1. If _thisValue_ is *undefined*, set _thisValue_ to _globalObj_.""",
    """        1. Set _realmRec_.[[GlobalObject]] to _globalObj_.""",
    """        1. Let _newGlobalEnv_ be NewGlobalEnvironment(_globalObj_, _thisValue_).""",
    """        1. Set _realmRec_.[[GlobalEnv]] to _newGlobalEnv_.""",
    """        1. Return _realmRec_.""",
  )
}
