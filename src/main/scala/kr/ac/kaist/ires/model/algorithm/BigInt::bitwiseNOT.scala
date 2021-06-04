package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BigInt::bitwiseNOT` extends Algo {
  val head = NormalHead("BigInt::bitwiseNOT", List(Param("x", Normal)))
  val ids = List()
  val rawBody = parseInst("""{
  |  return (~ x)
  |}""".stripMargin)
  val code = scala.Array[String]()
}