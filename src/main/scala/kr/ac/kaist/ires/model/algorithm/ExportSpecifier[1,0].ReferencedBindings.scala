package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ExportSpecifier[1,0].ReferencedBindings` extends Algo {
  val head = SyntaxDirectedHead("ExportSpecifier", 1, 0, Rhs(List(NonTerminal("IdentifierName", List(""), false), Terminal("as"), NonTerminal("IdentifierName", List(""), false)), None), "ReferencedBindings", List())
  val ids = List(
    "sec-static-semantics-referencedbindings",
    "sec-exports",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""return (new [IdentifierName0])""".stripMargin)
  val code = scala.Array[String](
    """          1. Return a List whose sole element is the first |IdentifierName|.""",
  )
}
