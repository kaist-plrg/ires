package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::PropertyName[0,0].IsComputedPropertyKey` extends Algo {
  val head = SyntaxDirectedHead("PropertyName", 0, 0, Rhs(List(NonTerminal("LiteralPropertyName", List(""), false)), None), "IsComputedPropertyKey", List())
  val ids = List(
    "sec-static-semantics-iscomputedpropertykey",
    "sec-object-initializer",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""return false""".stripMargin)
  val code = scala.Array[String](
    """          1. Return *false*.""",
  )
}
