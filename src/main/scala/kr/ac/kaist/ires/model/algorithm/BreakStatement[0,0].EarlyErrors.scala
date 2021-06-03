package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BreakStatement[0,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("BreakStatement", 0, 0, Rhs(List(Terminal("break"), Terminal(";")), None), "EarlyErrors", List())
  val ids = List(
    "sec-break-statement-static-semantics-early-errors",
    "sec-break-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""??? "It is a Syntax Error if this nt:{BreakStatement} is not nested , directly or indirectly ( but not crossing function boundaries ) , within an nt:{IterationStatement} or a nt:{SwitchStatement} ."""".stripMargin)
  val code = scala.Array[String](
    """        <li>""",
    """          It is a Syntax Error if this |BreakStatement| is not nested, directly or indirectly (but not crossing function boundaries), within an |IterationStatement| or a |SwitchStatement|.""",
    """        </li>""",
  )
}
