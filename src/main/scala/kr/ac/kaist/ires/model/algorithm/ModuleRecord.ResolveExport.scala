package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ModuleRecord.ResolveExport` extends Algo {
  val head = NormalHead("ModuleRecord.ResolveExport", List(Param("this", Normal), Param("exportName", Normal), Param("resolveSet", Optional)))
  val ids = List()
  val rawBody = parseInst("""return (new ResolvedBindingRecord())""".stripMargin)
  val code = scala.Array[String]()
}
