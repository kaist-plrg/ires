package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ThrowCompletion` extends Algo {
  val head = NormalHead("ThrowCompletion", List(Param("argument", Normal)))
  val ids = List()
  val rawBody = parseInst("""{
  |  return (new Completion("Type" -> CONST_throw, "Value" -> argument, "Target" -> CONST_empty))
  |}""".stripMargin)
  val code = scala.Array[String]()
}
