package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Literal[2,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("Literal", 2, 0, Rhs(List(NonTerminal("NumericLiteral", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-literals-runtime-semantics-evaluation",
    "sec-primary-expression-literals",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (NumericLiteral "NumericValue")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return the NumericValue of |NumericLiteral| as defined in <emu-xref href="#sec-literals-numeric-literals"></emu-xref>.""",
  )
}
