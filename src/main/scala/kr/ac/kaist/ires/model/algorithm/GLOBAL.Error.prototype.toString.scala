package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Error.prototype.toString` extends Algo {
  val head = BuiltinHead(parseRef("""Error.prototype.toString"""), List())
  val ids = List(
    "sec-error.prototype.tostring",
    "sec-properties-of-the-error-prototype-object",
    "sec-error-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:let O = this
  |  1:if (! (= (typeof O) Object)) throw TypeError else 3:{}
  |  2:app __x0__ = (Get O "name")
  |  2:let name = [? __x0__]
  |  3:if (= name undefined) name = "Error" else {
  |    app __x1__ = (ToString name)
  |    name = [? __x1__]
  |  }
  |  4:app __x2__ = (Get O "message")
  |  4:let msg = [? __x2__]
  |  5:if (= msg undefined) msg = "" else {
  |    app __x3__ = (ToString msg)
  |    msg = [? __x3__]
  |  }
  |  6:if (= name "") return msg else 3:{}
  |  7:if (= msg "") return name else 3:{}
  |  8:return (+ (+ (+ name ":") " ") msg)
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be the *this* value.""",
    """          1. If Type(_O_) is not Object, throw a *TypeError* exception.""",
    """          1. Let _name_ be ? Get(_O_, *"name"*).""",
    """          1. If _name_ is *undefined*, set _name_ to *"Error"*; otherwise set _name_ to ? ToString(_name_).""",
    """          1. Let _msg_ be ? Get(_O_, *"message"*).""",
    """          1. If _msg_ is *undefined*, set _msg_ to the empty String; otherwise set _msg_ to ? ToString(_msg_).""",
    """          1. If _name_ is the empty String, return _msg_.""",
    """          1. If _msg_ is the empty String, return _name_.""",
    """          1. Return the string-concatenation of _name_, the code unit 0x003A (COLON), the code unit 0x0020 (SPACE), and _msg_.""",
  )
}
