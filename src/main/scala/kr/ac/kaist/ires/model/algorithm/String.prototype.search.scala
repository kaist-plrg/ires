package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::String.prototype.search` extends Algo {
  val head = BuiltinHead(parseRef("""String.prototype.search"""), List(Param("regexp", Normal)))
  val ids = List(
    "sec-string.prototype.search",
    "sec-properties-of-the-string-prototype-object",
    "sec-string-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (RequireObjectCoercible this)
  |  0:let O = [? __x0__]
  |  1:if (! (|| (= regexp undefined) (= regexp null))) {
  |    2:app __x1__ = (GetMethod regexp SYMBOL_search)
  |    2:let searcher = [? __x1__]
  |    3:if (! (= searcher undefined)) {
  |      4:app __x2__ = (Call searcher regexp (new [O]))
  |      4:return [? __x2__]
  |    } else 5:{}
  |  } else 5:{}
  |  5:app __x3__ = (ToString O)
  |  5:let string = [? __x3__]
  |  6:app __x4__ = (RegExpCreate regexp undefined)
  |  6:let rx = [? __x4__]
  |  7:app __x5__ = (Invoke rx SYMBOL_search (new [string]))
  |  7:return [? __x5__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? RequireObjectCoercible(*this* value).""",
    """          1. If _regexp_ is neither *undefined* nor *null*, then""",
    """            1. Let _searcher_ be ? GetMethod(_regexp_, @@search).""",
    """            1. If _searcher_ is not *undefined*, then""",
    """              1. Return ? Call(_searcher_, _regexp_, « _O_ »).""",
    """          1. Let _string_ be ? ToString(_O_).""",
    """          1. Let _rx_ be ? RegExpCreate(_regexp_, *undefined*).""",
    """          1. Return ? Invoke(_rx_, @@search, « _string_ »).""",
  )
}
