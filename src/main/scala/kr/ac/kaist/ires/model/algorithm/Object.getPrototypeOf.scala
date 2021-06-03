package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Object.getPrototypeOf` extends Algo {
  val head = BuiltinHead(parseRef("""Object.getPrototypeOf"""), List(Param("O", Normal)))
  val ids = List(
    "sec-object.getprototypeof",
    "sec-properties-of-the-object-constructor",
    "sec-object-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToObject O)
  |  0:let obj = [? __x0__]
  |  1:app __x1__ = (obj.GetPrototypeOf obj)
  |  1:return [? __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _obj_ be ? ToObject(_O_).""",
    """          1. Return ? _obj_.[[GetPrototypeOf]]().""",
  )
}
