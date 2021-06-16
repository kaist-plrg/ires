package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IsAbruptCompletion` extends Algo {
  val head = NormalHead("IsAbruptCompletion", List(Param("value", Normal)))
  val ids = List()
  val rawBody = parseInst("""{
  |  return (&& (is-completion value) (! (= value.Type CONST_normal)))
  |}""".stripMargin)
  val code = scala.Array[String]()
}
