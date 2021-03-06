package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FunctionDeclaration[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("FunctionDeclaration", 0, 0, Rhs(List(Terminal("function"), NonTerminal("BindingIdentifier", List(""), false), Terminal("("), NonTerminal("FormalParameters", List(""), false), Terminal(")"), Terminal("{"), NonTerminal("FunctionBody", List(""), false), Terminal("}")), None), "Evaluation", List())
  val ids = List(
    "sec-function-definitions-runtime-semantics-evaluation",
    "sec-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""return CONST_empty""".stripMargin)
  val code = scala.Array[String](
    """        1. Return NormalCompletion(~empty~).""",
  )
}
