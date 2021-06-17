package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.String.prototype.match` extends Algo {
  val head = BuiltinHead(parseRef("""String.prototype.match"""), List(Param("regexp", Normal)))
  val ids = List(
    "sec-string.prototype.match",
    "sec-properties-of-the-string-prototype-object",
    "sec-string-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (RequireObjectCoercible this)
  |  0:let O = [? __x0__]
  |  1:if (! (|| (= regexp undefined) (= regexp null))) {
  |    2:app __x1__ = (GetMethod regexp SYMBOL_match)
  |    2:let matcher = [? __x1__]
  |    3:if (! (= matcher undefined)) {
  |      4:app __x2__ = (Call matcher regexp (new [O]))
  |      4:return [? __x2__]
  |    } else 4:{}
  |  } else 4:{}
  |  5:app __x3__ = (ToString O)
  |  5:let S = [? __x3__]
  |  6:app __x4__ = (RegExpCreate regexp undefined)
  |  6:let rx = [? __x4__]
  |  7:app __x5__ = (Invoke rx SYMBOL_match (new [S]))
  |  7:return [? __x5__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? RequireObjectCoercible(*this* value).""",
    """          1. If _regexp_ is neither *undefined* nor *null*, then""",
    """            1. Let _matcher_ be ? GetMethod(_regexp_, @@match).""",
    """            1. If _matcher_ is not *undefined*, then""",
    """              1. Return ? Call(_matcher_, _regexp_, « _O_ »).""",
    """          1. Let _S_ be ? ToString(_O_).""",
    """          1. Let _rx_ be ? RegExpCreate(_regexp_, *undefined*).""",
    """          1. Return ? Invoke(_rx_, @@match, « _S_ »).""",
  )
}
