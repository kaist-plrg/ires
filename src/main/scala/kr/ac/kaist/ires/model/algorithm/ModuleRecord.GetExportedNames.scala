package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ModuleRecord.GetExportedNames` extends Algo {
  val head = NormalHead("ModuleRecord.GetExportedNames", List(Param("this", Normal), Param("exportStarSet", Optional)))
  val ids = List()
  val rawBody = parseInst("""return StrList""".stripMargin)
  val code = scala.Array[String]()
}
