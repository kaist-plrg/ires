package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ModuleItemList[1,0].ExportedBindings` extends Algo {
  val head = SyntaxDirectedHead("ModuleItemList", 1, 0, Rhs(List(NonTerminal("ModuleItemList", List(""), false), NonTerminal("ModuleItem", List(""), false)), None), "ExportedBindings", List())
  val ids = List(
    "sec-static-semantics-exportedbindings",
    "sec-exports",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ModuleItemList "ExportedBindings")
  |  0:let names = __x0__
  |  1:access __x1__ = (ModuleItem "ExportedBindings")
  |  1:let __x2__ = __x1__
  |  1:let __x3__ = 0i
  |  1:while (< __x3__ __x2__.length) {
  |    let __x4__ = __x2__[__x3__]
  |    append __x4__ -> names
  |    __x3__ = (+ __x3__ 1i)
  |  }
  |  2:return names
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _names_ be ExportedBindings of |ModuleItemList|.""",
    """          1. Append to _names_ the elements of the ExportedBindings of |ModuleItem|.""",
    """          1. Return _names_.""",
  )
}
