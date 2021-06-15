package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ClassExpression[0,1].HasName` extends Algo {
  val head = SyntaxDirectedHead("ClassExpression", 0, 1, Rhs(List(Terminal("class"), NonTerminal("BindingIdentifier", List(""), false), NonTerminal("ClassTail", List(""), false)), None), "HasName", List())
  val ids = List(
    "sec-static-semantics-hasname",
    "sec-syntax-directed-operations-function-name-inference",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""return true""".stripMargin)
  val code = scala.Array[String](
    """        1. Return *true*.""",
  )
}
