package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::PropertyName[1,0].IsComputedPropertyKey` extends Algo {
  val head = SyntaxDirectedHead("PropertyName", 1, 0, Rhs(List(NonTerminal("ComputedPropertyName", List(""), false)), None), "IsComputedPropertyKey", List())
  val ids = List(
    "sec-static-semantics-iscomputedpropertykey",
    "sec-object-initializer",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""return true""".stripMargin)
  val code = scala.Array[String](
    """          1. Return *true*.""",
  )
}
