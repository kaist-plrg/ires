package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::getRegExp.prototype.source` extends Algo {
  val head = BuiltinHead(parseRef("""getRegExp.prototype.source"""), List())
  val ids = List(
    "sec-get-regexp.prototype.source",
    "sec-properties-of-the-regexp-prototype-object",
    "sec-regexp-regular-expression-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:let R = this
  |  1:if (! (= (typeof R) Object)) throw TypeError else 56:{}
  |  2:if (= R.OriginalSource absent) {
  |    4:app __x0__ = (SameValue R INTRINSIC_RegExp_prototype)
  |    4:if (= __x0__ true) return "(?:)" else throw TypeError
  |  } else 56:{}
  |  5:assert (! (= R.OriginalFlags absent))
  |  6:let src = R.OriginalSource
  |  7:let flags = R.OriginalFlags
  |  8:app __x1__ = (EscapeRegExpPattern src flags)
  |  8:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _R_ be the *this* value.""",
    """          1. If Type(_R_) is not Object, throw a *TypeError* exception.""",
    """          1. If _R_ does not have an [[OriginalSource]] internal slot, then""",
    """            1. If SameValue(_R_, %RegExp.prototype%) is *true*, return *"(?:)"*.""",
    """            1. Otherwise, throw a *TypeError* exception.""",
    """          1. Assert: _R_ has an [[OriginalFlags]] internal slot.""",
    """          1. Let _src_ be _R_.[[OriginalSource]].""",
    """          1. Let _flags_ be _R_.[[OriginalFlags]].""",
    """          1. Return EscapeRegExpPattern(_src_, _flags_).""",
  )
}
