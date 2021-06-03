package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ClassBody[0,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("ClassBody", 0, 0, Rhs(List(NonTerminal("ClassElementList", List(""), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-class-definitions-static-semantics-early-errors",
    "sec-class-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ClassElementList "PrototypePropertyNameList")
  |  0:let __x1__ = __x0__
  |  0:let __x2__ = "constructor"
  |  0:let __x3__ = 0i
  |  0:let __x4__ = 0i
  |  0:let __x5__ = false
  |  0:while (< __x4__ __x1__.length) if (= __x1__[__x4__] __x2__) __x3__ = (+ __x3__ 1i) else 2:{}
  |  0:if (< 1i __x3__) __x5__ = true else 2:{}
  |  0:if __x5__ throw SyntaxError else 2:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """        <li>""",
    """          It is a Syntax Error if PrototypePropertyNameList of |ClassElementList| contains more than one occurrence of *"constructor"*.""",
    """        </li>""",
  )
}
