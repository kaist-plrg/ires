package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BigInt::add` extends Algo {
  val head = NormalHead("BigInt::add", List(Param("x", Normal), Param("y", Normal)))
  val ids = List()
  val rawBody = parseInst("""{
  |  return (+ x y)
  |}""".stripMargin)
  val code = scala.Array[String]()
}
