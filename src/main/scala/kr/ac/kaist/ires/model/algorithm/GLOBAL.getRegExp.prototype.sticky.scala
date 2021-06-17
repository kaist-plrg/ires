package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.getRegExp.prototype.sticky` extends Algo {
  val head = BuiltinHead(parseRef("""getRegExp.prototype.sticky"""), List())
  val ids = List(
    "sec-get-regexp.prototype.sticky",
    "sec-properties-of-the-regexp-prototype-object",
    "sec-regexp-regular-expression-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:let R = this
  |  1:if (! (= (typeof R) Object)) throw TypeError else 56:{}
  |  2:if (= R.OriginalFlags absent) {
  |    4:app __x0__ = (SameValue R INTRINSIC_RegExp_prototype)
  |    4:if (= __x0__ true) return undefined else throw TypeError
  |  } else 56:{}
  |  5:let flags = R.OriginalFlags
  |  6:if (contains flags "y") return true else 56:{}
  |  7:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _R_ be the *this* value.""",
    """          1. If Type(_R_) is not Object, throw a *TypeError* exception.""",
    """          1. If _R_ does not have an [[OriginalFlags]] internal slot, then""",
    """            1. If SameValue(_R_, %RegExp.prototype%) is *true*, return *undefined*.""",
    """            1. Otherwise, throw a *TypeError* exception.""",
    """          1. Let _flags_ be _R_.[[OriginalFlags]].""",
    """          1. If _flags_ contains the code unit 0x0079 (LATIN SMALL LETTER Y), return *true*.""",
    """          1. Return *false*.""",
  )
}
