package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BindingIdentifier[1,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("BindingIdentifier", 1, 0, Rhs(List(Terminal("yield")), None), "EarlyErrors", List())
  val ids = List(
    "sec-identifiers-static-semantics-early-errors",
    "sec-identifiers",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""??? "It is a Syntax Error if this production has a sub:{[ Yield ]} parameter ."""".stripMargin)
  val code = scala.Array[String](
    """        <li>""",
    """          It is a Syntax Error if this production has a <sub>[Yield]</sub> parameter.""",
    """        </li>""",
  )
}
