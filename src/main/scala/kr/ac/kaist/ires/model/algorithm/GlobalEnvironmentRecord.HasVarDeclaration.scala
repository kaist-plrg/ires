package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GlobalEnvironmentRecord.HasVarDeclaration` extends Algo {
  val head = MethodHead("GlobalEnvironmentRecord", "HasVarDeclaration", Param("envRec", Normal), List(Param("N", Normal)))
  val ids = List(
    "sec-hasvardeclaration",
    "sec-global-environment-records",
    "sec-the-environment-record-type-hierarchy",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:let varDeclaredNames = envRec.VarNames
  |  1:if (contains varDeclaredNames N) return true else 3:{}
  |  2:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _varDeclaredNames_ be _envRec_.[[VarNames]].""",
    """            1. If _varDeclaredNames_ contains _N_, return *true*.""",
    """            1. Return *false*.""",
  )
}
