package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BindingElement[1,0].HasInitializer` extends Algo {
  val head = SyntaxDirectedHead("BindingElement", 1, 0, Rhs(List(NonTerminal("BindingPattern", List(""), false)), None), "HasInitializer", List())
  val ids = List(
    "sec-static-semantics-hasinitializer",
    "sec-parameter-lists",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""return false""".stripMargin)
  val code = scala.Array[String](
    """        1. Return *false*.""",
  )
}
