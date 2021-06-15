package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ModuleEnvironmentRecord.HasThisBinding` extends Algo {
  val head = MethodHead("ModuleEnvironmentRecord", "HasThisBinding", Param("envRec", Normal), List())
  val ids = List(
    "sec-module-environment-records-hasthisbinding",
    "sec-module-environment-records",
    "sec-the-environment-record-type-hierarchy",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""return true""".stripMargin)
  val code = scala.Array[String](
    """            1. Return *true*.""",
  )
}
