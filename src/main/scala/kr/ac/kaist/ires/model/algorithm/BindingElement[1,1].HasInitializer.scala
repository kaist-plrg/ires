package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BindingElement[1,1].HasInitializer` extends Algo {
  val head = SyntaxDirectedHead("BindingElement", 1, 1, Rhs(List(NonTerminal("BindingPattern", List(""), false), NonTerminal("Initializer", List(""), false)), None), "HasInitializer", List())
  val ids = List(
    "sec-static-semantics-hasinitializer",
    "sec-parameter-lists",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""return true""".stripMargin)
  val code = scala.Array[String](
    """        1. Return *true*.""",
  )
}
