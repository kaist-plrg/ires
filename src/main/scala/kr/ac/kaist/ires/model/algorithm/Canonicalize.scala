package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Canonicalize` extends Algo {
  val head = NormalHead("Canonicalize", List(Param("ch", Normal)))
  val ids = List(
    "sec-runtime-semantics-canonicalize-ch",
    "sec-atom",
    "sec-pattern-semantics",
    "sec-regexp-regular-expression-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:if (&& (= Unicode true) (= IgnoreCase true)) {
  |    1:??? "If the file CaseFolding . txt of the Unicode Character Database provides a simple or common case folding mapping for id:{ch} , return the result of applying that mapping to id:{ch} ."
  |    2:return ch
  |  } else 4:{}
  |  3:if (= IgnoreCase false) return ch else 4:{}
  |  5:??? "Let id:{cp} be the code point whose numeric value is that of id:{ch} ."
  |  6:??? "Let id:{u} be the result of toUppercase ( « id:{cp} » ) , according to the Unicode Default Case Conversion algorithm ."
  |  7:app __x0__ = (CodePointsToString u)
  |  7:let uStr = [! __x0__]
  |  8:??? "If id:{uStr} does not consist of a single code unit , return id:{ch} ."
  |  9:??? "Let id:{cu} be id:{uStr} ' s single code unit element ."
  |  10:??? "If the numeric value of id:{ch} ≥ 128 and the numeric value of id:{cu} < 128 , return id:{ch} ."
  |  11:return cu
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. If _Unicode_ is *true* and _IgnoreCase_ is *true*, then""",
    """              1. If the file CaseFolding.txt of the Unicode Character Database provides a simple or common case folding mapping for _ch_, return the result of applying that mapping to _ch_.""",
    """              1. Return _ch_.""",
    """            1. If _IgnoreCase_ is *false*, return _ch_.""",
    """            1. Assert: _ch_ is a UTF-16 code unit.""",
    """            1. Let _cp_ be the code point whose numeric value is that of _ch_.""",
    """            1. Let _u_ be the result of toUppercase(« _cp_ »), according to the Unicode Default Case Conversion algorithm.""",
    """            1. Let _uStr_ be ! CodePointsToString(_u_).""",
    """            1. If _uStr_ does not consist of a single code unit, return _ch_.""",
    """            1. Let _cu_ be _uStr_'s single code unit element.""",
    """            1. If the numeric value of _ch_ ≥ 128 and the numeric value of _cu_ < 128, return _ch_.""",
    """            1. Return _cu_.""",
  )
}
