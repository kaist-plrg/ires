package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ReturnStatement[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("ReturnStatement", 0, 0, Rhs(List(Terminal("return"), Terminal(";")), None), "Evaluation", List())
  val ids = List(
    "sec-return-statement-runtime-semantics-evaluation",
    "sec-return-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""return (new Completion("Type" -> CONST_return, "Value" -> undefined, "Target" -> CONST_empty))""".stripMargin)
  val code = scala.Array[String](
    """        1. Return Completion { [[Type]]: ~return~, [[Value]]: *undefined*, [[Target]]: ~empty~ }.""",
  )
}
