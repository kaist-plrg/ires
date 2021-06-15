package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ClassElementList[1,0].ComputedPropertyContains` extends Algo {
  val head = SyntaxDirectedHead("ClassElementList", 1, 0, Rhs(List(NonTerminal("ClassElementList", List(""), false), NonTerminal("ClassElement", List(""), false)), None), "ComputedPropertyContains", List(Param("symbol", Normal)))
  val ids = List(
    "sec-static-semantics-computedpropertycontains",
    "sec-syntax-directed-operations-contains",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ClassElementList "ComputedPropertyContains" symbol)
  |  0:let inList = __x0__
  |  1:if (= inList true) return true else 1:{}
  |  2:access __x1__ = (ClassElement "ComputedPropertyContains" symbol)
  |  2:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _inList_ be ComputedPropertyContains of |ClassElementList| with argument _symbol_.""",
    """        1. If _inList_ is *true*, return *true*.""",
    """        1. Return the result of ComputedPropertyContains for |ClassElement| with argument _symbol_.""",
  )
}
