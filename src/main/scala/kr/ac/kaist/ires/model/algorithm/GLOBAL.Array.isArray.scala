package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Array.isArray` extends Algo {
  val head = BuiltinHead(parseRef("""Array.isArray"""), List(Param("arg", Normal)))
  val ids = List(
    "sec-array.isarray",
    "sec-properties-of-the-array-constructor",
    "sec-array-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsArray arg)
  |  0:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return ? IsArray(_arg_).""",
  )
}
