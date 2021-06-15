package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SingleNameBinding[0,1].ContainsExpression` extends Algo {
  val head = SyntaxDirectedHead("SingleNameBinding", 0, 1, Rhs(List(NonTerminal("BindingIdentifier", List(""), false), NonTerminal("Initializer", List(""), false)), None), "ContainsExpression", List())
  val ids = List(
    "sec-static-semantics-containsexpression",
    "sec-parameter-lists",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""return true""".stripMargin)
  val code = scala.Array[String](
    """        1. Return *true*.""",
  )
}
