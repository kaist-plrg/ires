package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.String.prototype.normalize` extends Algo {
  val head = BuiltinHead(parseRef("""String.prototype.normalize"""), List(Param("form", Optional)))
  val ids = List(
    "sec-string.prototype.normalize",
    "sec-properties-of-the-string-prototype-object",
    "sec-string-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (RequireObjectCoercible this)
  |  0:let O = [? __x0__]
  |  1:app __x1__ = (ToString O)
  |  1:let S = [? __x1__]
  |  3:if (= form undefined) let f = "NFC" else {
  |    app __x2__ = (ToString form)
  |    let f = [? __x2__]
  |  }
  |  4:if (! (|| (|| (|| (= f "NFC") (= f "NFD")) (= f "NFKC")) (= f "NFKD"))) throw RangeError else 4:{}
  |  5:??? "Let id:{ns} be the String value that is the result of normalizing id:{S} into the normalization form named by id:{f} as specified in < a href = \" https : / / unicode . org / reports / tr15 / \" > https : / / unicode . org / reports / tr15 / < / a > ."
  |  6:return ns
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? RequireObjectCoercible(*this* value).""",
    """          1. Let _S_ be ? ToString(_O_).""",
    """          1. If _form_ is *undefined*, let _f_ be *"NFC"*.""",
    """          1. Else, let _f_ be ? ToString(_form_).""",
    """          1. If _f_ is not one of *"NFC"*, *"NFD"*, *"NFKC"*, or *"NFKD"*, throw a *RangeError* exception.""",
    """          1. Let _ns_ be the String value that is the result of normalizing _S_ into the normalization form named by _f_ as specified in <a href="https://unicode.org/reports/tr15/">https://unicode.org/reports/tr15/</a>.""",
    """          1. Return _ns_.""",
  )
}
