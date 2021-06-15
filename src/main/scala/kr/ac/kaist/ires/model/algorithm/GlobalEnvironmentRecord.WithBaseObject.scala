package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GlobalEnvironmentRecord.WithBaseObject` extends Algo {
  val head = MethodHead("GlobalEnvironmentRecord", "WithBaseObject", Param("envRec", Normal), List())
  val ids = List(
    "sec-global-environment-records-withbaseobject",
    "sec-global-environment-records",
    "sec-the-environment-record-type-hierarchy",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""return undefined""".stripMargin)
  val code = scala.Array[String](
    """            1. Return *undefined*.""",
  )
}
