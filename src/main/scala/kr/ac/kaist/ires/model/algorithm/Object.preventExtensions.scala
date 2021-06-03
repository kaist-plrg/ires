package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Object.preventExtensions` extends Algo {
  val head = BuiltinHead(parseRef("""Object.preventExtensions"""), List(Param("O", Normal)))
  val ids = List(
    "sec-object.preventextensions",
    "sec-properties-of-the-object-constructor",
    "sec-object-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= (typeof O) Object)) return O else 2:{}
  |  1:app __x0__ = (O.PreventExtensions O)
  |  1:let status = [? __x0__]
  |  2:if (= status false) throw TypeError else 2:{}
  |  3:return O
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If Type(_O_) is not Object, return _O_.""",
    """          1. Let _status_ be ? _O_.[[PreventExtensions]]().""",
    """          1. If _status_ is *false*, throw a *TypeError* exception.""",
    """          1. Return _O_.""",
  )
}
