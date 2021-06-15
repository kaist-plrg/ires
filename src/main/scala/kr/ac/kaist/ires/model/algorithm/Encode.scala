package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Encode` extends Algo {
  val head = NormalHead("Encode", List(Param("string", Normal), Param("unescapedSet", Normal)))
  val ids = List(
    "sec-encode",
    "sec-uri-syntax-and-semantics",
    "sec-uri-handling-functions",
    "sec-function-properties-of-the-global-object",
    "sec-global-object",
  )
  val rawBody = parseInst("""{
  |  0:let strLen = string.length
  |  1:let R = ""
  |  2:let k = 0i
  |  3:while true {
  |    4:if (== k strLen) return R else 71:{}
  |    5:let C = string[k]
  |    9:if (contains unescapedSet C) {
  |      7:k = (+ k 1i)
  |      8:R = (+ R C)
  |    } else {
  |      10:app __x0__ = (CodePointAt string k)
  |      10:let cp = [! __x0__]
  |      11:if (= cp.IsUnpairedSurrogate true) throw URIError else 71:{}
  |      12:k = (+ k cp.CodeUnitCount)
  |      13:??? "Let id:{Octets} be the List of octets resulting by applying the UTF - 8 transformation to id:{cp} . [ [ CodePoint ] ] ."
  |      14:let __x1__ = Octets
  |      14:let __x2__ = 0i
  |      14:while (< __x2__ __x1__.length) {
  |        let octet = __x1__[__x2__]
  |        15:R = (+ (+ R "%") ??? "StringOp")
  |        __x2__ = (+ __x2__ 1i)
  |      }
  |    }
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _strLen_ be the number of code units in _string_.""",
    """            1. Let _R_ be the empty String.""",
    """            1. Let _k_ be 0.""",
    """            1. Repeat,""",
    """              1. If _k_ = _strLen_, return _R_.""",
    """              1. Let _C_ be the code unit at index _k_ within _string_.""",
    """              1. If _C_ is in _unescapedSet_, then""",
    """                1. Set _k_ to _k_ + 1.""",
    """                1. Set _R_ to the string-concatenation of _R_ and _C_.""",
    """              1. Else,""",
    """                1. Let _cp_ be ! CodePointAt(_string_, _k_).""",
    """                1. If _cp_.[[IsUnpairedSurrogate]] is *true*, throw a *URIError* exception.""",
    """                1. Set _k_ to _k_ + _cp_.[[CodeUnitCount]].""",
    """                1. Let _Octets_ be the List of octets resulting by applying the UTF-8 transformation to _cp_.[[CodePoint]].""",
    """                1. For each element _octet_ of _Octets_, do""",
    """                  1. Set _R_ to the string-concatenation of:""",
    """                    * _R_""",
    """                    * *"%"*""",
    """                    * the String representation of _octet_, formatted as a two-digit uppercase hexadecimal number, padded to the left with a zero if necessary""",
  )
}
