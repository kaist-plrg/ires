package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::PrimaryExpression[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("PrimaryExpression", 0, 0, Rhs(List(Terminal("this")), None), "Evaluation", List())
  val ids = List(
    "sec-this-keyword-runtime-semantics-evaluation",
    "sec-this-keyword",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ResolveThisBinding)
  |  0:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return ? ResolveThisBinding().""",
  )
}
