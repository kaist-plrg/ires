package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SerializeJSONObject` extends Algo {
  val head = NormalHead("SerializeJSONObject", List(Param("state", Normal), Param("value", Normal)))
  val ids = List(
    "sec-serializejsonobject",
    "sec-json.stringify",
    "sec-json-object",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:if (contains state.Stack value) throw TypeError else 7:{}
  |  1:append value -> state.Stack
  |  2:let stepback = state.Indent
  |  3:state.Indent = (+ state.Indent state.Gap)
  |  6:if (! (= state.PropertyList undefined)) let K = state.PropertyList else {
  |    7:app __x0__ = (EnumerableOwnPropertyNames value CONST_key)
  |    7:let K = [? __x0__]
  |  }
  |  8:let partial = (new [])
  |  9:let __x1__ = K
  |  9:let __x2__ = 0i
  |  9:while (< __x2__ __x1__.length) {
  |    let P = __x1__[__x2__]
  |    10:app __x3__ = (SerializeJSONProperty state P value)
  |    10:let strP = [? __x3__]
  |    11:if (! (= strP undefined)) {
  |      12:app __x4__ = (QuoteJSONString P)
  |      12:let member = __x4__
  |      13:member = (+ member ":")
  |      14:if (! (= state.Gap "")) member = (+ member " ") else 7:{}
  |      16:member = (+ member strP)
  |      17:append member -> partial
  |    } else 7:{}
  |    __x2__ = (+ __x2__ 1i)
  |  }
  |  20:if (= partial.length 0i) let final = "{}" else if (= state.Gap "") {
  |    22:??? "Let id:{properties} be the String value formed by concatenating all the element Strings of id:{partial} with each adjacent pair of Strings separated with the code unit 0x002C ( COMMA ) . A comma is not inserted either before the first String or after the last String ."
  |    23:let final = (+ (+ "{" properties) "}")
  |  } else {
  |    25:let separator = (+ (+ "," "\n") state.Indent)
  |    26:??? "Let id:{properties} be the String value formed by concatenating all the element Strings of id:{partial} with each adjacent pair of Strings separated with id:{separator} . The id:{separator} String is not inserted either before the first String or after the last String ."
  |    27:let final = (+ (+ (+ (+ (+ (+ "{" "\n") state.Indent) properties) "\n") stepback) "}")
  |  }
  |  28:??? "Remove the last element of id:{state} . [ [ Stack ] ] ."
  |  29:state.Indent = stepback
  |  30:return final
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If _state_.[[Stack]] contains _value_, throw a *TypeError* exception because the structure is cyclical.""",
    """          1. Append _value_ to _state_.[[Stack]].""",
    """          1. Let _stepback_ be _state_.[[Indent]].""",
    """          1. Set _state_.[[Indent]] to the string-concatenation of _state_.[[Indent]] and _state_.[[Gap]].""",
    """          1. If _state_.[[PropertyList]] is not *undefined*, then""",
    """            1. Let _K_ be _state_.[[PropertyList]].""",
    """          1. Else,""",
    """            1. Let _K_ be ? EnumerableOwnPropertyNames(_value_, ~key~).""",
    """          1. Let _partial_ be a new empty List.""",
    """          1. For each element _P_ of _K_, do""",
    """            1. Let _strP_ be ? SerializeJSONProperty(_state_, _P_, _value_).""",
    """            1. If _strP_ is not *undefined*, then""",
    """              1. Let _member_ be QuoteJSONString(_P_).""",
    """              1. Set _member_ to the string-concatenation of _member_ and *":"*.""",
    """              1. If _state_.[[Gap]] is not the empty String, then""",
    """                1. Set _member_ to the string-concatenation of _member_ and the code unit 0x0020 (SPACE).""",
    """              1. Set _member_ to the string-concatenation of _member_ and _strP_.""",
    """              1. Append _member_ to _partial_.""",
    """          1. If _partial_ is empty, then""",
    """            1. Let _final_ be *"{}"*.""",
    """          1. Else,""",
    """            1. If _state_.[[Gap]] is the empty String, then""",
    """              1. Let _properties_ be the String value formed by concatenating all the element Strings of _partial_ with each adjacent pair of Strings separated with the code unit 0x002C (COMMA). A comma is not inserted either before the first String or after the last String.""",
    """              1. Let _final_ be the string-concatenation of *"{"*, _properties_, and *"}"*.""",
    """            1. Else,""",
    """              1. Let _separator_ be the string-concatenation of the code unit 0x002C (COMMA), the code unit 0x000A (LINE FEED), and _state_.[[Indent]].""",
    """              1. Let _properties_ be the String value formed by concatenating all the element Strings of _partial_ with each adjacent pair of Strings separated with _separator_. The _separator_ String is not inserted either before the first String or after the last String.""",
    """              1. Let _final_ be the string-concatenation of *"{"*, the code unit 0x000A (LINE FEED), _state_.[[Indent]], _properties_, the code unit 0x000A (LINE FEED), _stepback_, and *"}"*.""",
    """          1. Remove the last element of _state_.[[Stack]].""",
    """          1. Set _state_.[[Indent]] to _stepback_.""",
    """          1. Return _final_.""",
  )
}
