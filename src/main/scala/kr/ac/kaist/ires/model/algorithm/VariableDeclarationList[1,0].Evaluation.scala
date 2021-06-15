package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::VariableDeclarationList[1,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("VariableDeclarationList", 1, 0, Rhs(List(NonTerminal("VariableDeclarationList", List(""), false), Terminal(","), NonTerminal("VariableDeclaration", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-variable-statement-runtime-semantics-evaluation",
    "sec-variable-statement",
    "sec-declarations-and-the-variable-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (VariableDeclarationList "Evaluation")
  |  0:let next = __x0__
  |  1:[? next]
  |  2:access __x1__ = (VariableDeclaration "Evaluation")
  |  2:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _next_ be the result of evaluating |VariableDeclarationList|.""",
    """          1. ReturnIfAbrupt(_next_).""",
    """          1. Return the result of evaluating |VariableDeclaration|.""",
  )
}
