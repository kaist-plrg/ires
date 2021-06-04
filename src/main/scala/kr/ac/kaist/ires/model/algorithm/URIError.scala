package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::URIError` extends Algo {
  val head = NormalHead("URIError", List(Param("message", Normal)))
  val ids = List()
  val rawBody = parseInst("""??? "Need manual modeling"""".stripMargin)
  val code = scala.Array[String]()
}