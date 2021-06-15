package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Literal[3,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("Literal", 3, 0, Rhs(List(NonTerminal("StringLiteral", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-literals-runtime-semantics-evaluation",
    "sec-primary-expression-literals",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (StringLiteral "SV")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return the SV of |StringLiteral| as defined in <emu-xref href="#sec-static-semantics-sv"></emu-xref>.""",
  )
}
