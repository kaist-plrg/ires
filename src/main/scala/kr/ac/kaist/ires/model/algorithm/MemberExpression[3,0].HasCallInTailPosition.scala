package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::MemberExpression[3,0].HasCallInTailPosition` extends Algo {
  val head = SyntaxDirectedHead("MemberExpression", 3, 0, Rhs(List(NonTerminal("MemberExpression", List(""), false), NonTerminal("TemplateLiteral", List(""), false)), None), "HasCallInTailPosition", List(Param("call", Normal)))
  val ids = List(
    "sec-expression-rules",
    "sec-static-semantics-hascallintailposition",
    "sec-tail-position-calls",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:if (= this call) return true else 0:{}
  |  1:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If this |MemberExpression| is _call_, return *true*.""",
    """          1. Return *false*.""",
  )
}
