package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::LabelIdentifier[1,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("LabelIdentifier", 1, 0, Rhs(List(Terminal("yield")), None), "EarlyErrors", List())
  val ids = List(
    "sec-identifiers-static-semantics-early-errors",
    "sec-identifiers",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""if true throw SyntaxError else 2:{}""".stripMargin)
  val code = scala.Array[String](
    """        <li>""",
    """          It is a Syntax Error if the code matched by this production is contained in strict mode code.""",
    """        </li>""",
  )
}
