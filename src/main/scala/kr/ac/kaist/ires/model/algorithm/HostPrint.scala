package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::HostPrint` extends Algo {
  val head = BuiltinHead(parseRef("""HostPrint"""), List(Param("str", Normal)))
  val ids = List()
  val rawBody = parseInst("""{
  |  print [? str]
  |  return undefined
  |}""".stripMargin)
  val code = scala.Array[String]()
}
