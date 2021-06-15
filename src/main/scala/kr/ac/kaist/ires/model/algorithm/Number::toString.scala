package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Number::toString` extends Algo {
  val head = NormalHead("Number::toString", List(Param("x", Normal)))
  val ids = List()
  val rawBody = parseInst("""{
  |  return (convert x num2str )
  |}""".stripMargin)
  val code = scala.Array[String]()
}
