package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Script[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("Script", 0, 0, Rhs(List(EmptyToken), None), "Evaluation", List())
  val ids = List(
    "sec-script-semantics-runtime-semantics-evaluation",
    "sec-scripts",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""return undefined""".stripMargin)
  val code = scala.Array[String](
    """        1. Return NormalCompletion(*undefined*).""",
  )
}
