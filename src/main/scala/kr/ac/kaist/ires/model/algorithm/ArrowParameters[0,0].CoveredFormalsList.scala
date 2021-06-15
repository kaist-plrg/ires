package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArrowParameters[0,0].CoveredFormalsList` extends Algo {
  val head = SyntaxDirectedHead("ArrowParameters", 0, 0, Rhs(List(NonTerminal("BindingIdentifier", List(""), false)), None), "CoveredFormalsList", List())
  val ids = List(
    "sec-static-semantics-coveredformalslist",
    "sec-arrow-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""return this""".stripMargin)
  val code = scala.Array[String](
    """        1. Return this |ArrowParameters|.""",
  )
}
