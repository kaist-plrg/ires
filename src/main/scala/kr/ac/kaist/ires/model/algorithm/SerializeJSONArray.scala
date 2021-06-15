package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SerializeJSONArray` extends Algo {
  val head = NormalHead("SerializeJSONArray", List(Param("state", Normal), Param("value", Normal)))
  val ids = List(
    "sec-serializejsonarray",
    "sec-json.stringify",
    "sec-json-object",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:if (contains state.Stack value) throw TypeError else 7:{}
  |  1:append value -> state.Stack
  |  2:let stepback = state.Indent
  |  3:state.Indent = (+ state.Indent state.Gap)
  |  4:let partial = (new [])
  |  5:app __x0__ = (LengthOfArrayLike value)
  |  5:let len = [? __x0__]
  |  6:let index = 0i
  |  7:while (< index len) {
  |    8:app __x1__ = (ToString index)
  |    8:app __x2__ = (SerializeJSONProperty state [! __x1__] value)
  |    8:let strP = [? __x2__]
  |    11:if (= strP undefined) append "null" -> partial else append strP -> partial
  |    13:index = (+ index 1i)
  |  }
  |  16:if (= partial.length 0i) let final = "[]" else if (= state.Gap "") {
  |    18:??? "Let id:{properties} be the String value formed by concatenating all the element Strings of id:{partial} with each adjacent pair of Strings separated with the code unit 0x002C ( COMMA ) . A comma is not inserted either before the first String or after the last String ."
  |    19:let final = (+ (+ "[" properties) "]")
  |  } else {
  |    21:let separator = (+ (+ "," "\n") state.Indent)
  |    22:??? "Let id:{properties} be the String value formed by concatenating all the element Strings of id:{partial} with each adjacent pair of Strings separated with id:{separator} . The id:{separator} String is not inserted either before the first String or after the last String ."
  |    23:let final = (+ (+ (+ (+ (+ (+ "[" "\n") state.Indent) properties) "\n") stepback) "]")
  |  }
  |  24:??? "Remove the last element of id:{state} . [ [ Stack ] ] ."
  |  25:state.Indent = stepback
  |  26:return final
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If _state_.[[Stack]] contains _value_, throw a *TypeError* exception because the structure is cyclical.""",
    """          1. Append _value_ to _state_.[[Stack]].""",
    """          1. Let _stepback_ be _state_.[[Indent]].""",
    """          1. Set _state_.[[Indent]] to the string-concatenation of _state_.[[Indent]] and _state_.[[Gap]].""",
    """          1. Let _partial_ be a new empty List.""",
    """          1. Let _len_ be ? LengthOfArrayLike(_value_).""",
    """          1. Let _index_ be 0.""",
    """          1. Repeat, while _index_ < _len_,""",
    """            1. Let _strP_ be ? SerializeJSONProperty(_state_, ! ToString(𝔽(_index_)), _value_).""",
    """            1. If _strP_ is *undefined*, then""",
    """              1. Append *"null"* to _partial_.""",
    """            1. Else,""",
    """              1. Append _strP_ to _partial_.""",
    """            1. Set _index_ to _index_ + 1.""",
    """          1. If _partial_ is empty, then""",
    """            1. Let _final_ be *"[]"*.""",
    """          1. Else,""",
    """            1. If _state_.[[Gap]] is the empty String, then""",
    """              1. Let _properties_ be the String value formed by concatenating all the element Strings of _partial_ with each adjacent pair of Strings separated with the code unit 0x002C (COMMA). A comma is not inserted either before the first String or after the last String.""",
    """              1. Let _final_ be the string-concatenation of *"["*, _properties_, and *"]"*.""",
    """            1. Else,""",
    """              1. Let _separator_ be the string-concatenation of the code unit 0x002C (COMMA), the code unit 0x000A (LINE FEED), and _state_.[[Indent]].""",
    """              1. Let _properties_ be the String value formed by concatenating all the element Strings of _partial_ with each adjacent pair of Strings separated with _separator_. The _separator_ String is not inserted either before the first String or after the last String.""",
    """              1. Let _final_ be the string-concatenation of *"["*, the code unit 0x000A (LINE FEED), _state_.[[Indent]], _properties_, the code unit 0x000A (LINE FEED), _stepback_, and *"]"*.""",
    """          1. Remove the last element of _state_.[[Stack]].""",
    """          1. Set _state_.[[Indent]] to _stepback_.""",
    """          1. Return _final_.""",
  )
}
