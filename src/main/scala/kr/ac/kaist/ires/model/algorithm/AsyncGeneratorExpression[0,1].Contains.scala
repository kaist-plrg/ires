package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncGeneratorExpression[0,1].Contains` extends Algo {
  val head = SyntaxDirectedHead("AsyncGeneratorExpression", 0, 1, Rhs(List(Terminal("async"), Terminal("function"), Terminal("*"), NonTerminal("BindingIdentifier", List(""), true), Terminal("("), NonTerminal("FormalParameters", List(""), false), Terminal(")"), Terminal("{"), NonTerminal("AsyncGeneratorBody", List(""), false), Terminal("}")), None), "Contains", List(Param("symbol", Normal)))
  val ids = List(
    "sec-static-semantics-contains",
    "sec-syntax-directed-operations-contains",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""return false""".stripMargin)
  val code = scala.Array[String](
    """        1. Return *false*.""",
  )
}
