package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ClassTail[0,3].Contains` extends Algo {
  val head = SyntaxDirectedHead("ClassTail", 0, 3, Rhs(List(NonTerminal("ClassHeritage", List(""), true), Terminal("{"), NonTerminal("ClassBody", List(""), false), Terminal("}")), None), "Contains", List(Param("symbol", Normal)))
  val ids = List(
    "sec-static-semantics-contains",
    "sec-syntax-directed-operations-contains",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:if (is-instance-of symbol ClassBody) return true else 1:{}
  |  1:if (is-instance-of symbol ClassHeritage) if (! (= ClassHeritage absent)) return true else return false else 1:{}
  |  3:access __x0__ = (ClassHeritage "Contains" symbol)
  |  3:let inHeritage = __x0__
  |  4:if (= inHeritage true) return true else 1:{}
  |  5:access __x1__ = (ClassBody "ComputedPropertyContains" symbol)
  |  5:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If _symbol_ is |ClassBody|, return *true*.""",
    """        1. If _symbol_ is |ClassHeritage|, then""",
    """          1. If |ClassHeritage| is present, return *true*; otherwise return *false*.""",
    """        1. Let _inHeritage_ be |ClassHeritage| Contains _symbol_.""",
    """        1. If _inHeritage_ is *true*, return *true*.""",
    """        1. Return the result of ComputedPropertyContains for |ClassBody| with argument _symbol_.""",
  )
}
