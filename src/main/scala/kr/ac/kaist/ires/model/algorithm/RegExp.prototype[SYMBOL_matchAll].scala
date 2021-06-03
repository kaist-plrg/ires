package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::RegExp.prototype[SYMBOL_matchAll]` extends Algo {
  val head = BuiltinHead(parseRef("""RegExp.prototype[SYMBOL_matchAll]"""), List(Param("string", Normal)))
  val ids = List(
    "sec-regexp-prototype-matchall",
    "sec-properties-of-the-regexp-prototype-object",
    "sec-regexp-regular-expression-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:let R = this
  |  1:if (! (= (typeof R) Object)) throw TypeError else 52:{}
  |  2:app __x0__ = (ToString string)
  |  2:let S = [? __x0__]
  |  3:app __x1__ = (SpeciesConstructor R INTRINSIC_RegExp)
  |  3:let C = [? __x1__]
  |  4:app __x2__ = (Get R "flags")
  |  4:app __x3__ = (ToString [? __x2__])
  |  4:let flags = [? __x3__]
  |  5:app __x4__ = (Construct C (new [R, flags]))
  |  5:let matcher = [? __x4__]
  |  6:app __x5__ = (Get R "lastIndex")
  |  6:app __x6__ = (ToLength [? __x5__])
  |  6:let lastIndex = [? __x6__]
  |  7:app __x7__ = (Set matcher "lastIndex" lastIndex true)
  |  7:[? __x7__]
  |  9:if (contains flags "g") let global = true else let global = false
  |  11:if (contains flags "u") let fullUnicode = true else let fullUnicode = false
  |  12:app __x8__ = (CreateRegExpStringIterator matcher S global fullUnicode)
  |  12:return [! __x8__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _R_ be the *this* value.""",
    """          1. If Type(_R_) is not Object, throw a *TypeError* exception.""",
    """          1. Let _S_ be ? ToString(_string_).""",
    """          1. Let _C_ be ? SpeciesConstructor(_R_, %RegExp%).""",
    """          1. Let _flags_ be ? ToString(? Get(_R_, *"flags"*)).""",
    """          1. Let _matcher_ be ? Construct(_C_, « _R_, _flags_ »).""",
    """          1. Let _lastIndex_ be ? ToLength(? Get(_R_, *"lastIndex"*)).""",
    """          1. Perform ? Set(_matcher_, *"lastIndex"*, _lastIndex_, *true*).""",
    """          1. If _flags_ contains *"g"*, let _global_ be *true*.""",
    """          1. Else, let _global_ be *false*.""",
    """          1. If _flags_ contains *"u"*, let _fullUnicode_ be *true*.""",
    """          1. Else, let _fullUnicode_ be *false*.""",
    """          1. Return ! CreateRegExpStringIterator(_matcher_, _S_, _global_, _fullUnicode_).""",
  )
}
