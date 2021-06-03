package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Object.freeze` extends Algo {
  val head = BuiltinHead(parseRef("""Object.freeze"""), List(Param("O", Normal)))
  val ids = List(
    "sec-object.freeze",
    "sec-properties-of-the-object-constructor",
    "sec-object-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= (typeof O) Object)) return O else 71:{}
  |  1:app __x0__ = (SetIntegrityLevel O CONST_frozen)
  |  1:let status = [? __x0__]
  |  2:if (= status false) throw TypeError else 71:{}
  |  3:return O
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If Type(_O_) is not Object, return _O_.""",
    """          1. Let _status_ be ? SetIntegrityLevel(_O_, ~frozen~).""",
    """          1. If _status_ is *false*, throw a *TypeError* exception.""",
    """          1. Return _O_.""",
  )
}
