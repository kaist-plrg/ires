package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GetSubstitution` extends Algo {
  val head = NormalHead("GetSubstitution", List(Param("matched", Normal), Param("str", Normal), Param("position", Normal), Param("captures", Normal), Param("namedCaptures", Normal), Param("replacement", Normal)))
  val ids = List(
    "sec-getsubstitution",
    "sec-string.prototype.replace",
    "sec-properties-of-the-string-prototype-object",
    "sec-string-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof matched) String)
  |  1:let matchLength = matched.length
  |  2:assert (= (typeof str) String)
  |  3:let stringLength = str.length
  |  4:assert (! (< stringLength position))
  |  6:assert (= (typeof replacement) String)
  |  7:let tailPos = (+ position matchLength)
  |  8:let m = captures.length
  |  9:??? "Let id:{result} be the String value derived from id:{replacement} by copying code unit elements from id:{replacement} to id:{result} while performing replacements as specified in link:{table-replacement-text-symbol-substitutions} . These code:{$} replacements are done left - to - right , and , once such a replacement is performed , the new replacement text is not subject to further replacements ."
  |  10:return result
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: Type(_matched_) is String.""",
    """            1. Let _matchLength_ be the number of code units in _matched_.""",
    """            1. Assert: Type(_str_) is String.""",
    """            1. Let _stringLength_ be the number of code units in _str_.""",
    """            1. Assert: _position_ ≤ _stringLength_.""",
    """            1. Assert: _captures_ is a possibly empty List of Strings.""",
    """            1. Assert: Type(_replacement_) is String.""",
    """            1. Let _tailPos_ be _position_ + _matchLength_.""",
    """            1. Let _m_ be the number of elements in _captures_.""",
    """            1. Let _result_ be the String value derived from _replacement_ by copying code unit elements from _replacement_ to _result_ while performing replacements as specified in <emu-xref href="#table-replacement-text-symbol-substitutions"></emu-xref>. These `$` replacements are done left-to-right, and, once such a replacement is performed, the new replacement text is not subject to further replacements.""",
    """            1. Return _result_.""",
  )
}
