package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncFunctionExpression[0,1].IsFunctionDefinition` extends Algo {
  val head = SyntaxDirectedHead("AsyncFunctionExpression", 0, 1, Rhs(List(Terminal("async"), Terminal("function"), NonTerminal("BindingIdentifier", List(""), true), Terminal("("), NonTerminal("FormalParameters", List(""), false), Terminal(")"), Terminal("{"), NonTerminal("AsyncFunctionBody", List(""), false), Terminal("}")), None), "IsFunctionDefinition", List())
  val ids = List(
    "sec-static-semantics-isfunctiondefinition",
    "sec-syntax-directed-operations-function-name-inference",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""return true""".stripMargin)
  val code = scala.Array[String](
    """        1. Return *true*.""",
  )
}
