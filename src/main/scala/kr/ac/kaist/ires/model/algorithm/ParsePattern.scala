package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ParsePattern` extends Algo {
  val head = NormalHead("ParsePattern", List(Param("patternText", Normal), Param("u", Normal)))
  val ids = List(
    "sec-parsepattern",
    "sec-abstract-operations-for-the-regexp-constructor",
    "sec-regexp-constructor",
    "sec-regexp-regular-expression-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  2:if (= u true) let parseResult = (parse-syntax patternText "Pattern" (new [true, true])) else {
  |    3:let parseResult = (parse-syntax patternText "Pattern" (new [false, false]))
  |    4:parseResult = (parse-syntax patternText "Pattern" (new [false, true]))
  |  }
  |  6:return parseResult
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. If _u_ is *true*, then""",
    """              1. Let _parseResult_ be ParseText(_patternText_, |Pattern[+U, +N]|).""",
    """            1. Else,""",
    """              1. Let _parseResult_ be ParseText(_patternText_, |Pattern[~U, ~N]|).""",
    """              1. If _parseResult_ is a Parse Node and _parseResult_ contains a |GroupName|, then""",
    """                1. Set _parseResult_ to ParseText(_patternText_, |Pattern[~U, +N]|).""",
    """            1. Return _parseResult_.""",
  )
}
