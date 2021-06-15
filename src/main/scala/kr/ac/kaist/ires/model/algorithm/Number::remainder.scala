package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Number::remainder` extends Algo {
  val head = NormalHead("Number::remainder", List(Param("x", Normal), Param("y", Normal)))
  val ids = List()
  val rawBody = parseInst("""{
  |  return (% x y)
  |}""".stripMargin)
  val code = scala.Array[String]()
}
