package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::PropertyDefinition[1,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("PropertyDefinition", 1, 0, Rhs(List(NonTerminal("CoverInitializedName", List(""), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-object-initializer-static-semantics-early-errors",
    "sec-object-initializer",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""if true throw SyntaxError else 2:{}""".stripMargin)
  val code = scala.Array[String](
    """          <li>""",
    """            Always throw a Syntax Error if code matches this production.""",
    """          </li>""",
  )
}
