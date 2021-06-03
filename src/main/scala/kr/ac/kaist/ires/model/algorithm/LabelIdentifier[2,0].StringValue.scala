package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::LabelIdentifier[2,0].StringValue` extends Algo {
  val head = SyntaxDirectedHead("LabelIdentifier", 2, 0, Rhs(List(Terminal("await")), None), "StringValue", List())
  val ids = List(
    "sec-static-semantics-stringvalue",
    "sec-identifiers",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""return "await"""".stripMargin)
  val code = scala.Array[String](
    """        1. Return *"await"*.""",
  )
}
