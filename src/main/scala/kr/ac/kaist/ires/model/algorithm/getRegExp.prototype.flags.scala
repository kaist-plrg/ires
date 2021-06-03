package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::getRegExp.prototype.flags` extends Algo {
  val head = BuiltinHead(parseRef("""getRegExp.prototype.flags"""), List())
  val ids = List(
    "sec-get-regexp.prototype.flags",
    "sec-properties-of-the-regexp-prototype-object",
    "sec-regexp-regular-expression-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:let R = this
  |  1:if (! (= (typeof R) Object)) throw TypeError else 52:{}
  |  2:let result = ""
  |  3:app __x0__ = (Get R "global")
  |  3:app __x1__ = (ToBoolean [? __x0__])
  |  3:let global = [! __x1__]
  |  4:if (= global true) let result = (+ result "g") else 52:{}
  |  5:app __x2__ = (Get R "ignoreCase")
  |  5:app __x3__ = (ToBoolean [? __x2__])
  |  5:let ignoreCase = [! __x3__]
  |  6:if (= ignoreCase true) let result = (+ result "i") else 52:{}
  |  7:app __x4__ = (Get R "multiline")
  |  7:app __x5__ = (ToBoolean [? __x4__])
  |  7:let multiline = [! __x5__]
  |  8:if (= multiline true) let result = (+ result "m") else 52:{}
  |  9:app __x6__ = (Get R "dotAll")
  |  9:app __x7__ = (ToBoolean [? __x6__])
  |  9:let dotAll = [! __x7__]
  |  10:if (= dotAll true) let result = (+ result "s") else 52:{}
  |  11:app __x8__ = (Get R "unicode")
  |  11:app __x9__ = (ToBoolean [? __x8__])
  |  11:let unicode = [! __x9__]
  |  12:if (= unicode true) let result = (+ result "u") else 52:{}
  |  13:app __x10__ = (Get R "sticky")
  |  13:app __x11__ = (ToBoolean [? __x10__])
  |  13:let sticky = [! __x11__]
  |  14:if (= sticky true) let result = (+ result "y") else 52:{}
  |  15:return result
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _R_ be the *this* value.""",
    """          1. If Type(_R_) is not Object, throw a *TypeError* exception.""",
    """          1. Let _result_ be the empty String.""",
    """          1. Let _global_ be ! ToBoolean(? Get(_R_, *"global"*)).""",
    """          1. If _global_ is *true*, append the code unit 0x0067 (LATIN SMALL LETTER G) as the last code unit of _result_.""",
    """          1. Let _ignoreCase_ be ! ToBoolean(? Get(_R_, *"ignoreCase"*)).""",
    """          1. If _ignoreCase_ is *true*, append the code unit 0x0069 (LATIN SMALL LETTER I) as the last code unit of _result_.""",
    """          1. Let _multiline_ be ! ToBoolean(? Get(_R_, *"multiline"*)).""",
    """          1. If _multiline_ is *true*, append the code unit 0x006D (LATIN SMALL LETTER M) as the last code unit of _result_.""",
    """          1. Let _dotAll_ be ! ToBoolean(? Get(_R_, *"dotAll"*)).""",
    """          1. If _dotAll_ is *true*, append the code unit 0x0073 (LATIN SMALL LETTER S) as the last code unit of _result_.""",
    """          1. Let _unicode_ be ! ToBoolean(? Get(_R_, *"unicode"*)).""",
    """          1. If _unicode_ is *true*, append the code unit 0x0075 (LATIN SMALL LETTER U) as the last code unit of _result_.""",
    """          1. Let _sticky_ be ! ToBoolean(? Get(_R_, *"sticky"*)).""",
    """          1. If _sticky_ is *true*, append the code unit 0x0079 (LATIN SMALL LETTER Y) as the last code unit of _result_.""",
    """          1. Return _result_.""",
  )
}
