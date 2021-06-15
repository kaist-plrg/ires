package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::HoistableDeclaration[1,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("HoistableDeclaration", 1, 0, Rhs(List(NonTerminal("GeneratorDeclaration", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-statement-semantics-runtime-semantics-evaluation",
    "sec-statement-semantics",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""return CONST_empty""".stripMargin)
  val code = scala.Array[String](
    """        1. Return NormalCompletion(~empty~).""",
  )
}
