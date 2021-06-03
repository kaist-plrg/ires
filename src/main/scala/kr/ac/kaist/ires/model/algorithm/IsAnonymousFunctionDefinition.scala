package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IsAnonymousFunctionDefinition` extends Algo {
  val head = NormalHead("IsAnonymousFunctionDefinition", List(Param("expr", Normal)))
  val ids = List(
    "sec-isanonymousfunctiondefinition",
    "sec-syntax-directed-operations-function-name-inference",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (expr "IsFunctionDefinition")
  |  0:if (= __x0__ false) return false else 1:{}
  |  1:access __x1__ = (expr "HasName")
  |  1:let hasName = __x1__
  |  2:if (= hasName true) return false else 1:{}
  |  3:return true
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If IsFunctionDefinition of _expr_ is *false*, return *false*.""",
    """        1. Let _hasName_ be HasName of _expr_.""",
    """        1. If _hasName_ is *true*, return *false*.""",
    """        1. Return *true*.""",
  )
}
