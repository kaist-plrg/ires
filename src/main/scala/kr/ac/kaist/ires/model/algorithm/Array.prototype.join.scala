package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Array.prototype.join` extends Algo {
  val head = BuiltinHead(parseRef("""Array.prototype.join"""), List(Param("separator", Normal)))
  val ids = List(
    "sec-array.prototype.join",
    "sec-properties-of-the-array-prototype-object",
    "sec-array-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToObject this)
  |  0:let O = [? __x0__]
  |  1:app __x1__ = (LengthOfArrayLike O)
  |  1:let len = [? __x1__]
  |  2:??? "If id:{separator} is value:{undefined} , let id:{sep} be the single - element String value:{\",\"} ."
  |  3:app sep = (ToString separator)
  |  3:[? sep]
  |  4:let R = ""
  |  5:let k = 0i
  |  6:while (< k len) {
  |    7:if (< 0i k) R = (+ R sep) else 4:{}
  |    8:app __x2__ = (ToString k)
  |    8:app __x3__ = (Get O [! __x2__])
  |    8:let element = [? __x3__]
  |    9:if (|| (= element undefined) (= element null)) let next = "" else {
  |      app __x4__ = (ToString element)
  |      let next = [? __x4__]
  |    }
  |    10:R = (+ R next)
  |    11:k = (+ k 1i)
  |  }
  |  12:return R
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? ToObject(*this* value).""",
    """          1. Let _len_ be ? LengthOfArrayLike(_O_).""",
    """          1. If _separator_ is *undefined*, let _sep_ be the single-element String *","*.""",
    """          1. Else, let _sep_ be ? ToString(_separator_).""",
    """          1. Let _R_ be the empty String.""",
    """          1. Let _k_ be 0.""",
    """          1. Repeat, while _k_ < _len_,""",
    """            1. If _k_ > 0, set _R_ to the string-concatenation of _R_ and _sep_.""",
    """            1. Let _element_ be ? Get(_O_, ! ToString(𝔽(_k_))).""",
    """            1. If _element_ is *undefined* or *null*, let _next_ be the empty String; otherwise, let _next_ be ? ToString(_element_).""",
    """            1. Set _R_ to the string-concatenation of _R_ and _next_.""",
    """            1. Set _k_ to _k_ + 1.""",
    """          1. Return _R_.""",
  )
}
