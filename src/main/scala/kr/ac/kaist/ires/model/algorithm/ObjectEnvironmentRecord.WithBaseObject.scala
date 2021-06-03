package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ObjectEnvironmentRecord.WithBaseObject` extends Algo {
  val head = MethodHead("ObjectEnvironmentRecord", "WithBaseObject", Param("envRec", Normal), List())
  val ids = List(
    "sec-object-environment-records-withbaseobject",
    "sec-object-environment-records",
    "sec-the-environment-record-type-hierarchy",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""if (= envRec.withEnvironment true) return envRec.BindingObject else return undefined""".stripMargin)
  val code = scala.Array[String](
    """            1. If the _withEnvironment_ flag of _envRec_ is *true*, return the binding object for _envRec_.""",
    """            1. Otherwise, return *undefined*.""",
  )
}
