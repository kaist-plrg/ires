package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::TemplateMiddleList[1,0].SubstitutionEvaluation` extends Algo {
  val head = SyntaxDirectedHead("TemplateMiddleList", 1, 0, Rhs(List(NonTerminal("TemplateMiddleList", List(""), false), NonTerminal("TemplateMiddle", List(""), false), NonTerminal("Expression", List(""), false)), None), "SubstitutionEvaluation", List())
  val ids = List(
    "sec-runtime-semantics-substitutionevaluation",
    "sec-template-literals",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (TemplateMiddleList "SubstitutionEvaluation")
  |  0:let preceding = [? __x0__]
  |  1:access __x1__ = (Expression "Evaluation")
  |  1:let nextRef = __x1__
  |  2:app __x2__ = (GetValue nextRef)
  |  2:let next = [? __x2__]
  |  3:append next -> preceding
  |  4:return preceding
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _preceding_ be ? SubstitutionEvaluation of |TemplateMiddleList|.""",
    """          1. Let _nextRef_ be the result of evaluating |Expression|.""",
    """          1. Let _next_ be ? GetValue(_nextRef_).""",
    """          1. Append _next_ as the last element of the List _preceding_.""",
    """          1. Return _preceding_.""",
  )
}
