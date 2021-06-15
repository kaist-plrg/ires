package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ImportedLocalNames` extends Algo {
  val head = NormalHead("ImportedLocalNames", List(Param("importEntries", Normal)))
  val ids = List(
    "sec-importedlocalnames",
    "sec-module-semantics",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:let localNames = (new [])
  |  1:let __x0__ = importEntries
  |  1:let __x1__ = 0i
  |  1:while (< __x1__ __x0__.length) {
  |    let i = __x0__[__x1__]
  |    2:append i.LocalName -> localNames
  |    __x1__ = (+ __x1__ 1i)
  |  }
  |  3:return localNames
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _localNames_ be a new empty List.""",
    """          1. For each ImportEntry Record _i_ of _importEntries_, do""",
    """            1. Append _i_.[[LocalName]] to _localNames_.""",
    """          1. Return _localNames_.""",
  )
}
