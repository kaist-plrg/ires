package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::HoistableDeclaration[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("HoistableDeclaration", 0, 0, Rhs(List(NonTerminal("FunctionDeclaration", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-statement-semantics-runtime-semantics-evaluation",
    "sec-statement-semantics",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (FunctionDeclaration "Evaluation")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return the result of evaluating |FunctionDeclaration|.""",
  )
}
