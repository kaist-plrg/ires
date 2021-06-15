package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GetArgument` extends Algo {
  val head = NormalHead("GetArgument", List(Param("argumentsList", Normal), Param("idx", Normal)))
  val ids = List()
  val rawBody = parseInst("""{
  |  let arg = argumentsList[idx]
  |  if (= arg absent) return undefined else return arg
  |}""".stripMargin)
  val code = scala.Array[String]()
}
