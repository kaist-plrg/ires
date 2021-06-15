package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GlobalEnvironmentRecord.HasSuperBinding` extends Algo {
  val head = MethodHead("GlobalEnvironmentRecord", "HasSuperBinding", Param("envRec", Normal), List())
  val ids = List(
    "sec-global-environment-records-hassuperbinding",
    "sec-global-environment-records",
    "sec-the-environment-record-type-hierarchy",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""return false""".stripMargin)
  val code = scala.Array[String](
    """            1. Return *false*.""",
  )
}
