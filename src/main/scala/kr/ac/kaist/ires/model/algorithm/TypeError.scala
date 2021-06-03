package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::TypeError` extends Algo {
  val head = NormalHead("TypeError", List(Param("message", Normal)))
  val ids = List()
  val rawBody = parseInst("""??? "Need manual modeling"""".stripMargin)
  val code = scala.Array[String]()
}
