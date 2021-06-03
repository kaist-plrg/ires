package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FunctionStatementList[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("FunctionStatementList", 0, 0, Rhs(List(EmptyToken), None), "Evaluation", List())
  val ids = List(
    "sec-function-definitions-runtime-semantics-evaluation",
    "sec-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""return undefined""".stripMargin)
  val code = scala.Array[String](
    """        1. Return NormalCompletion(*undefined*).""",
  )
}
