package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SingleNameBinding[0,0].HasInitializer` extends Algo {
  val head = SyntaxDirectedHead("SingleNameBinding", 0, 0, Rhs(List(NonTerminal("BindingIdentifier", List(""), false)), None), "HasInitializer", List())
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
