package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ModuleItemList[1,0].ModuleRequests` extends Algo {
  val head = SyntaxDirectedHead("ModuleItemList", 1, 0, Rhs(List(NonTerminal("ModuleItemList", List(""), false), NonTerminal("ModuleItem", List(""), false)), None), "ModuleRequests", List())
  val ids = List(
    "sec-static-semantics-modulerequests",
    "sec-module-semantics",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ModuleItemList "ModuleRequests")
  |  0:let moduleNames = __x0__
  |  1:access __x1__ = (ModuleItem "ModuleRequests")
  |  1:let additionalNames = __x1__
  |  2:let __i__ = 0i
  |  2:while (< __i__ additionalNames.length) if (contains moduleNames additionalNames[__i__]) {} else append additionalNames[__i__] -> moduleNames
  |  3:return moduleNames
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _moduleNames_ be ModuleRequests of |ModuleItemList|.""",
    """          1. Let _additionalNames_ be ModuleRequests of |ModuleItem|.""",
    """          1. Append to _moduleNames_ each element of _additionalNames_ that is not already an element of _moduleNames_.""",
    """          1. Return _moduleNames_.""",
  )
}
