package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GetGlobalObject` extends Algo {
  val head = NormalHead("GetGlobalObject", List())
  val ids = List(
    "sec-getglobalobject",
    "sec-execution-contexts",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:let currentRealm = REALM
  |  1:return currentRealm.GlobalObject
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _currentRealm_ be the current Realm Record.""",
    """        1. Return _currentRealm_.[[GlobalObject]].""",
  )
}
