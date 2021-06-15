package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Decode` extends Algo {
  val head = NormalHead("Decode", List(Param("string", Normal), Param("reservedSet", Normal)))
  val ids = List(
    "sec-decode",
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
  |    8:if (! (= C "%")) let S = C else {
  |      9:let start = k
  |      10:if (! (< (+ k 2i) strLen)) throw URIError else 71:{}
  |      11:??? "If the code units at index ( id:{k} + 1 ) and ( id:{k} + 2 ) within id:{string} do not represent hexadecimal digits , throw a value:{URIError} exception ."
  |      12:??? "Let id:{B} be the 8 - bit value represented by the two hexadecimal digits at index ( id:{k} + 1 ) and ( id:{k} + 2 ) ."
  |      13:k = (+ k 2i)
  |      14:??? "Let id:{n} be the number of leading 1 bits in id:{B} ."
  |      21:if (== n 0i) {
  |        16:let C = B
  |        19:if (! (contains reservedSet C)) let S = C else {
  |          20:let __x0__ = ""
  |          20:let __x1__ = start
  |          20:while (< __x1__ (+ (+ k 1i) 1i)) {
  |            access __x2__ = (string __x1__)
  |            __x0__ = (+ __x0__ __x2__)
  |            __x1__ = (+ __x1__ 1i)
  |          }
  |          20:let S = __x0__
  |        }
  |      } else {
  |        22:if (|| (== n 1i) (< 4i n)) throw URIError else 71:{}
  |        23:if (! (< (+ k (* 3i (- n 1i))) strLen)) throw URIError else 71:{}
  |        24:let Octets = (new [B])
  |        25:let j = 1i
  |        26:while (< j n) {
  |          27:k = (+ k 1i)
  |          28:if (! (= string[k] "%")) throw URIError else 71:{}
  |          29:??? "If the code units at index ( id:{k} + 1 ) and ( id:{k} + 2 ) within id:{string} do not represent hexadecimal digits , throw a value:{URIError} exception ."
  |          30:??? "Let id:{B} be the 8 - bit value represented by the two hexadecimal digits at index ( id:{k} + 1 ) and ( id:{k} + 2 ) ."
  |          31:k = (+ k 2i)
  |          32:append B -> Octets
  |          33:j = (+ j 1i)
  |        }
  |        34:assert (= Octets.length n)
  |        35:??? "If id:{Octets} does not contain a valid UTF - 8 encoding of a Unicode code point , throw a value:{URIError} exception ."
  |        36:??? "Let id:{V} be the code point obtained by applying the UTF - 8 transformation to id:{Octets} , that is , from a List of octets into a 21 - bit value ."
  |        37:app __x3__ = (UTF16EncodeCodePoint V)
  |        37:let S = __x3__
  |      }
  |    }
  |    38:R = (+ R S)
  |    39:k = (+ k 1i)
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _strLen_ be the length of _string_.""",
    """            1. Let _R_ be the empty String.""",
    """            1. Let _k_ be 0.""",
    """            1. Repeat,""",
    """              1. If _k_ = _strLen_, return _R_.""",
    """              1. Let _C_ be the code unit at index _k_ within _string_.""",
    """              1. If _C_ is not the code unit 0x0025 (PERCENT SIGN), then""",
    """                1. Let _S_ be the String value containing only the code unit _C_.""",
    """              1. Else,""",
    """                1. Let _start_ be _k_.""",
    """                1. If _k_ + 2 ≥ _strLen_, throw a *URIError* exception.""",
    """                1. If the code units at index (_k_ + 1) and (_k_ + 2) within _string_ do not represent hexadecimal digits, throw a *URIError* exception.""",
    """                1. Let _B_ be the 8-bit value represented by the two hexadecimal digits at index (_k_ + 1) and (_k_ + 2).""",
    """                1. Set _k_ to _k_ + 2.""",
    """                1. Let _n_ be the number of leading 1 bits in _B_.""",
    """                1. If _n_ = 0, then""",
    """                  1. Let _C_ be the code unit whose value is _B_.""",
    """                  1. If _C_ is not in _reservedSet_, then""",
    """                    1. Let _S_ be the String value containing only the code unit _C_.""",
    """                  1. Else,""",
    """                    1. Let _S_ be the substring of _string_ from _start_ to _k_ + 1.""",
    """                1. Else,""",
    """                  1. If _n_ = 1 or _n_ > 4, throw a *URIError* exception.""",
    """                  1. If _k_ + (3 × (_n_ - 1)) ≥ _strLen_, throw a *URIError* exception.""",
    """                  1. Let _Octets_ be a List whose sole element is _B_.""",
    """                  1. Let _j_ be 1.""",
    """                  1. Repeat, while _j_ < _n_,""",
    """                    1. Set _k_ to _k_ + 1.""",
    """                    1. If the code unit at index _k_ within _string_ is not the code unit 0x0025 (PERCENT SIGN), throw a *URIError* exception.""",
    """                    1. If the code units at index (_k_ + 1) and (_k_ + 2) within _string_ do not represent hexadecimal digits, throw a *URIError* exception.""",
    """                    1. Let _B_ be the 8-bit value represented by the two hexadecimal digits at index (_k_ + 1) and (_k_ + 2).""",
    """                    1. Set _k_ to _k_ + 2.""",
    """                    1. Append _B_ to _Octets_.""",
    """                    1. Set _j_ to _j_ + 1.""",
    """                  1. Assert: The length of _Octets_ is _n_.""",
    """                  1. If _Octets_ does not contain a valid UTF-8 encoding of a Unicode code point, throw a *URIError* exception.""",
    """                  1. Let _V_ be the code point obtained by applying the UTF-8 transformation to _Octets_, that is, from a List of octets into a 21-bit value.""",
    """                  1. Let _S_ be UTF16EncodeCodePoint(_V_).""",
    """              1. Set _R_ to the string-concatenation of _R_ and _S_.""",
    """              1. Set _k_ to _k_ + 1.""",
  )
}
