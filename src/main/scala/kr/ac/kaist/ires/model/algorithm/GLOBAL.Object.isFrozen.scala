package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Object.isFrozen` extends Algo {
  val head = BuiltinHead(parseRef("""Object.isFrozen"""), List(Param("O", Normal)))
  val ids = List(
    "sec-object.isfrozen",
    "sec-properties-of-the-object-constructor",
    "sec-object-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= (typeof O) Object)) return true else 2:{}
  |  1:app __x0__ = (TestIntegrityLevel O CONST_frozen)
  |  1:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If Type(_O_) is not Object, return *true*.""",
    """          1. Return ? TestIntegrityLevel(_O_, ~frozen~).""",
  )
}
