package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ClassElement[2,0].ClassElementKind` extends Algo {
  val head = SyntaxDirectedHead("ClassElement", 2, 0, Rhs(List(Terminal(";")), None), "ClassElementKind", List())
  val ids = List(
    "sec-static-semantics-classelementkind",
    "sec-class-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""return CONST_empty""".stripMargin)
  val code = scala.Array[String](
    """        1. Return ~empty~.""",
  )
}
