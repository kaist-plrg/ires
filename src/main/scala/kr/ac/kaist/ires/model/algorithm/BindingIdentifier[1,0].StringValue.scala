package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BindingIdentifier[1,0].StringValue` extends Algo {
  val head = SyntaxDirectedHead("BindingIdentifier", 1, 0, Rhs(List(Terminal("yield")), None), "StringValue", List())
  val ids = List(
    "sec-static-semantics-stringvalue",
    "sec-identifiers",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""return "yield"""".stripMargin)
  val code = scala.Array[String](
    """        1. Return *"yield"*.""",
  )
}
