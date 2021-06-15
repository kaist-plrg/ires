package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ObjectBindingPattern[0,0].ContainsExpression` extends Algo {
  val head = SyntaxDirectedHead("ObjectBindingPattern", 0, 0, Rhs(List(Terminal("{"), Terminal("}")), None), "ContainsExpression", List())
  val ids = List(
    "sec-static-semantics-containsexpression",
    "sec-parameter-lists",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""return false""".stripMargin)
  val code = scala.Array[String](
    """        1. Return *false*.""",
  )
}
