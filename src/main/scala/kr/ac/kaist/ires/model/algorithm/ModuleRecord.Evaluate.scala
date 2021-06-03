package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ModuleRecord.Evaluate` extends Algo {
  val head = NormalHead("ModuleRecord.Evaluate", List(Param("this", Normal)))
  val ids = List()
  val rawBody = parseInst("""return undefined""".stripMargin)
  val code = scala.Array[String]()
}
