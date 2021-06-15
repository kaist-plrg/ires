package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncArrowFunction[0,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("AsyncArrowFunction", 0, 0, Rhs(List(Terminal("async"), NonTerminal("AsyncArrowBindingIdentifier", List(""), false), Terminal("=>"), NonTerminal("AsyncConciseBody", List(""), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-async-arrow-function-definitions-static-semantics-early-errors",
    "sec-async-arrow-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (AsyncArrowBindingIdentifier "BoundNames")
  |  0:access __x1__ = (AsyncConciseBody "LexicallyDeclaredNames")
  |  0:let __x2__ = __x0__
  |  0:let __x3__ = __x1__
  |  0:let __x4__ = 0i
  |  0:let __x5__ = 0i
  |  0:let __x6__ = false
  |  0:while (< __x4__ __x2__.length) {
  |    __x5__ = 0i
  |    while (< __x5__ __x3__.length) if (= __x2__[__x4__] __x3__[__x5__]) __x6__ = true else 2:{}
  |  }
  |  0:if __x6__ throw SyntaxError else 2:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """        <li>It is a Syntax Error if any element of the BoundNames of |AsyncArrowBindingIdentifier| also occurs in the LexicallyDeclaredNames of |AsyncConciseBody|.</li>""",
  )
}
