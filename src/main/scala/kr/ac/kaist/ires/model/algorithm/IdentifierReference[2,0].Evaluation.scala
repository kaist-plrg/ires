package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IdentifierReference[2,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("IdentifierReference", 2, 0, Rhs(List(Terminal("await")), None), "Evaluation", List())
  val ids = List(
    "sec-identifiers-runtime-semantics-evaluation",
    "sec-identifiers",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ResolveBinding "await")
  |  0:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return ? ResolveBinding(*"await"*).""",
  )
}
