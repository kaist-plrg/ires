package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ObjectEnvironmentRecord.HasThisBinding` extends Algo {
  val head = MethodHead("ObjectEnvironmentRecord", "HasThisBinding", Param("envRec", Normal), List())
  val ids = List(
    "sec-object-environment-records-hasthisbinding",
    "sec-object-environment-records",
    "sec-the-environment-record-type-hierarchy",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""return false""".stripMargin)
  val code = scala.Array[String](
    """            1. Return *false*.""",
  )
}
