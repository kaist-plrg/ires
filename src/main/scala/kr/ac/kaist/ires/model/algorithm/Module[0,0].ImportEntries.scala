package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Module[0,0].ImportEntries` extends Algo {
  val head = SyntaxDirectedHead("Module", 0, 0, Rhs(List(EmptyToken), None), "ImportEntries", List())
  val ids = List(
    "sec-static-semantics-importentries",
    "sec-imports",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""return (new [])""".stripMargin)
  val code = scala.Array[String](
    """          1. Return a new empty List.""",
  )
}
