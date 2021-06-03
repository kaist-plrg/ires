package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Object.defineProperties` extends Algo {
  val head = BuiltinHead(parseRef("""Object.defineProperties"""), List(Param("O", Normal), Param("Properties", Normal)))
  val ids = List(
    "sec-object.defineproperties",
    "sec-properties-of-the-object-constructor",
    "sec-object-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= (typeof O) Object)) throw TypeError else 71:{}
  |  1:app __x0__ = (ObjectDefineProperties O Properties)
  |  1:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If Type(_O_) is not Object, throw a *TypeError* exception.""",
    """          1. Return ? ObjectDefineProperties(_O_, _Properties_).""",
  )
}
