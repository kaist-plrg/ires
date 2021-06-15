package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IdentifierReference[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("IdentifierReference", 0, 0, Rhs(List(NonTerminal("Identifier", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-identifiers-runtime-semantics-evaluation",
    "sec-identifiers",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (Identifier "StringValue")
  |  0:app __x1__ = (ResolveBinding __x0__)
  |  0:return [? __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return ? ResolveBinding(StringValue of |Identifier|).""",
  )
}
