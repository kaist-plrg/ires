package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.RegExp.prototype.toString` extends Algo {
  val head = BuiltinHead(parseRef("""RegExp.prototype.toString"""), List())
  val ids = List(
    "sec-regexp.prototype.tostring",
    "sec-properties-of-the-regexp-prototype-object",
    "sec-regexp-regular-expression-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:let R = this
  |  1:if (! (= (typeof R) Object)) throw TypeError else 56:{}
  |  2:app __x0__ = (Get R "source")
  |  2:app __x1__ = (ToString [? __x0__])
  |  2:let pattern = [? __x1__]
  |  3:app __x2__ = (Get R "flags")
  |  3:app __x3__ = (ToString [? __x2__])
  |  3:let flags = [? __x3__]
  |  4:let result = (+ (+ (+ "/" pattern) "/") flags)
  |  5:return result
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _R_ be the *this* value.""",
    """          1. If Type(_R_) is not Object, throw a *TypeError* exception.""",
    """          1. Let _pattern_ be ? ToString(? Get(_R_, *"source"*)).""",
    """          1. Let _flags_ be ? ToString(? Get(_R_, *"flags"*)).""",
    """          1. Let _result_ be the string-concatenation of *"/"*, _pattern_, *"/"*, and _flags_.""",
    """          1. Return _result_.""",
  )
}
