package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::LabelIdentifier[0,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("LabelIdentifier", 0, 0, Rhs(List(NonTerminal("Identifier", List(""), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-identifiers-static-semantics-early-errors",
    "sec-identifiers",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:??? "It is a Syntax Error if this production has a sub:{[ Yield ]} parameter and StringValue of nt:{Identifier} is value:{\"yield\"} ."
  |  1:??? "It is a Syntax Error if this production has an sub:{[ Await ]} parameter and StringValue of nt:{Identifier} is value:{\"await\"} ."
  |}""".stripMargin)
  val code = scala.Array[String](
    """        <li>""",
    """          It is a Syntax Error if this production has a <sub>[Yield]</sub> parameter and StringValue of |Identifier| is *"yield"*.""",
    """        </li>""",
    """        <li>""",
    """          It is a Syntax Error if this production has an <sub>[Await]</sub> parameter and StringValue of |Identifier| is *"await"*.""",
    """        </li>""",
  )
}
