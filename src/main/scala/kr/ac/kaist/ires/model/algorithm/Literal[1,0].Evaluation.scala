package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Literal[1,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("Literal", 1, 0, Rhs(List(NonTerminal("BooleanLiteral", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-literals-runtime-semantics-evaluation",
    "sec-primary-expression-literals",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:if (= (get-syntax BooleanLiteral) "false") return false else 0:{}
  |  1:if (= (get-syntax BooleanLiteral) "true") return true else 0:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If |BooleanLiteral| is the token `false`, return *false*.""",
    """          1. If |BooleanLiteral| is the token `true`, return *true*.""",
  )
}
