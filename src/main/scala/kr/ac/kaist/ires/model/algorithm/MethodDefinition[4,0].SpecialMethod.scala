package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::MethodDefinition[4,0].SpecialMethod` extends Algo {
  val head = SyntaxDirectedHead("MethodDefinition", 4, 0, Rhs(List(Terminal("get"), NonTerminal("PropertyName", List(""), false), Terminal("("), Terminal(")"), Terminal("{"), NonTerminal("FunctionBody", List(""), false), Terminal("}")), None), "SpecialMethod", List())
  val ids = List(
    "sec-static-semantics-specialmethod",
    "sec-method-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""return true""".stripMargin)
  val code = scala.Array[String](
    """        1. Return *true*.""",
  )
}
