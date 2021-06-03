package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Literal[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("Literal", 0, 0, Rhs(List(NonTerminal("NullLiteral", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-literals-runtime-semantics-evaluation",
    "sec-primary-expression-literals",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""return null""".stripMargin)
  val code = scala.Array[String](
    """          1. Return *null*.""",
  )
}
