package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IdentifierReference[0,0].AssignmentTargetType` extends Algo {
  val head = SyntaxDirectedHead("IdentifierReference", 0, 0, Rhs(List(NonTerminal("Identifier", List(""), false)), None), "AssignmentTargetType", List())
  val ids = List(
    "sec-static-semantics-assignmenttargettype",
    "sec-syntax-directed-operations-miscellaneous",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:let __x0__ = true
  |  0:__x0__ = true
  |  0:if __x0__ {
  |    access __x1__ = (Identifier "StringValue")
  |    __x0__ = (|| (= __x1__ "eval") (= __x1__ "arguments"))
  |  } else 1:{}
  |  0:if __x0__ return CONST_invalid else 1:{}
  |  1:return CONST_simple
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If this |IdentifierReference| is contained in strict mode code and StringValue of |Identifier| is *"eval"* or *"arguments"*, return ~invalid~.""",
    """        1. Return ~simple~.""",
  )
}
