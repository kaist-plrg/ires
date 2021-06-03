package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::HostGetImportMetaProperties` extends Algo {
  val head = NormalHead("HostGetImportMetaProperties", List(Param("moduleRecord", Normal)))
  val ids = List()
  val rawBody = parseInst("""return (new [])""".stripMargin)
  val code = scala.Array[String]()
}
