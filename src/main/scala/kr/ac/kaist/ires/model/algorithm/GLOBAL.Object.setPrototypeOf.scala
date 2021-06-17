package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Object.setPrototypeOf` extends Algo {
  val head = BuiltinHead(parseRef("""Object.setPrototypeOf"""), List(Param("O", Normal), Param("proto", Normal)))
  val ids = List(
    "sec-object.setprototypeof",
    "sec-properties-of-the-object-constructor",
    "sec-object-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (RequireObjectCoercible O)
  |  0:O = [? __x0__]
  |  1:if (! (|| (= (typeof proto) Object) (= (typeof proto) Null))) throw TypeError else 2:{}
  |  2:if (! (= (typeof O) Object)) return O else 2:{}
  |  3:app __x1__ = (O.SetPrototypeOf O proto)
  |  3:let status = [? __x1__]
  |  4:if (= status false) throw TypeError else 2:{}
  |  5:return O
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Set _O_ to ? RequireObjectCoercible(_O_).""",
    """          1. If Type(_proto_) is neither Object nor Null, throw a *TypeError* exception.""",
    """          1. If Type(_O_) is not Object, return _O_.""",
    """          1. Let _status_ be ? _O_.[[SetPrototypeOf]](_proto_).""",
    """          1. If _status_ is *false*, throw a *TypeError* exception.""",
    """          1. Return _O_.""",
  )
}
