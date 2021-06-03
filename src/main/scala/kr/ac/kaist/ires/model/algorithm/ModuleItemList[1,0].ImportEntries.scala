package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ModuleItemList[1,0].ImportEntries` extends Algo {
  val head = SyntaxDirectedHead("ModuleItemList", 1, 0, Rhs(List(NonTerminal("ModuleItemList", List(""), false), NonTerminal("ModuleItem", List(""), false)), None), "ImportEntries", List())
  val ids = List(
    "sec-static-semantics-importentries",
    "sec-imports",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ModuleItemList "ImportEntries")
  |  0:let entries = __x0__
  |  1:access __x1__ = (ModuleItem "ImportEntries")
  |  1:let __x2__ = __x1__
  |  1:let __x3__ = 0i
  |  1:while (< __x3__ __x2__.length) {
  |    let __x4__ = __x2__[__x3__]
  |    append __x4__ -> entries
  |    __x3__ = (+ __x3__ 1i)
  |  }
  |  2:return entries
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _entries_ be ImportEntries of |ModuleItemList|.""",
    """          1. Append to _entries_ the elements of the ImportEntries of |ModuleItem|.""",
    """          1. Return _entries_.""",
  )
}
