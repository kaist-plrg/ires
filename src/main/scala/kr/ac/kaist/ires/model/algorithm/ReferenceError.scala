package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ReferenceError` extends Algo {
  val head = NormalHead("ReferenceError", List(Param("message", Normal)))
  val ids = List()
  val rawBody = parseInst("""??? "Need manual modeling"""".stripMargin)
  val code = scala.Array[String]()
}
