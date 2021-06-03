package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::String.prototype.matchAll` extends Algo {
  val head = BuiltinHead(parseRef("""String.prototype.matchAll"""), List(Param("regexp", Normal)))
  val ids = List(
    "sec-string.prototype.matchall",
    "sec-properties-of-the-string-prototype-object",
    "sec-string-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (RequireObjectCoercible this)
  |  0:let O = [? __x0__]
  |  1:if (! (|| (= regexp undefined) (= regexp null))) {
  |    2:app __x1__ = (IsRegExp regexp)
  |    2:let isRegExp = [? __x1__]
  |    3:if (= isRegExp true) {
  |      4:app __x2__ = (Get regexp "flags")
  |      4:let flags = [? __x2__]
  |      5:app __x3__ = (RequireObjectCoercible flags)
  |      5:[? __x3__]
  |      6:app __x4__ = (ToString flags)
  |      6:if (! (contains [? __x4__] "g")) throw TypeError else 4:{}
  |    } else 4:{}
  |    7:app __x5__ = (GetMethod regexp SYMBOL_matchAll)
  |    7:let matcher = [? __x5__]
  |    8:if (! (= matcher undefined)) {
  |      9:app __x6__ = (Call matcher regexp (new [O]))
  |      9:return [? __x6__]
  |    } else 4:{}
  |  } else 4:{}
  |  10:app __x7__ = (ToString O)
  |  10:let S = [? __x7__]
  |  11:app __x8__ = (RegExpCreate regexp "g")
  |  11:let rx = [? __x8__]
  |  12:app __x9__ = (Invoke rx SYMBOL_matchAll (new [S]))
  |  12:return [? __x9__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? RequireObjectCoercible(*this* value).""",
    """          1. If _regexp_ is neither *undefined* nor *null*, then""",
    """            1. Let _isRegExp_ be ? IsRegExp(_regexp_).""",
    """            1. If _isRegExp_ is *true*, then""",
    """              1. Let _flags_ be ? Get(_regexp_, *"flags"*).""",
    """              1. Perform ? RequireObjectCoercible(_flags_).""",
    """              1. If ? ToString(_flags_) does not contain *"g"*, throw a *TypeError* exception.""",
    """            1. Let _matcher_ be ? GetMethod(_regexp_, @@matchAll).""",
    """            1. If _matcher_ is not *undefined*, then""",
    """              1. Return ? Call(_matcher_, _regexp_, « _O_ »).""",
    """          1. Let _S_ be ? ToString(_O_).""",
    """          1. Let _rx_ be ? RegExpCreate(_regexp_, *"g"*).""",
    """          1. Return ? Invoke(_rx_, @@matchAll, « _S_ »).""",
  )
}
