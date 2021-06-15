package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::HostFinalizeImportMeta` extends Algo {
  val head = NormalHead("HostFinalizeImportMeta", List(Param("importMeta", Normal), Param("moduleRecord", Normal)))
  val ids = List()
  val rawBody = parseInst("""return CONST_empty""".stripMargin)
  val code = scala.Array[String]()
}
