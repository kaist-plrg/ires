package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::PropertyDefinition[3,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("PropertyDefinition", 3, 0, Rhs(List(NonTerminal("MethodDefinition", List(""), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-object-initializer-static-semantics-early-errors",
    "sec-object-initializer",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (MethodDefinition "HasDirectSuper")
  |  0:if (= __x0__ true) throw SyntaxError else 2:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """          <li>""",
    """            It is a Syntax Error if HasDirectSuper of |MethodDefinition| is *true*.""",
    """          </li>""",
  )
}
