package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ExportsList[1,0].ExportedNames` extends Algo {
  val head = SyntaxDirectedHead("ExportsList", 1, 0, Rhs(List(NonTerminal("ExportsList", List(""), false), Terminal(","), NonTerminal("ExportSpecifier", List(""), false)), None), "ExportedNames", List())
  val ids = List(
    "sec-static-semantics-exportednames",
    "sec-exports",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ExportsList "ExportedNames")
  |  0:let names = __x0__
  |  1:access __x1__ = (ExportSpecifier "ExportedNames")
  |  1:let __x2__ = __x1__
  |  1:let __x3__ = 0i
  |  1:while (< __x3__ __x2__.length) {
  |    let __x4__ = __x2__[__x3__]
  |    append __x4__ -> names
  |    __x3__ = (+ __x3__ 1i)
  |  }
  |  2:return names
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _names_ be the ExportedNames of |ExportsList|.""",
    """          1. Append to _names_ the elements of the ExportedNames of |ExportSpecifier|.""",
    """          1. Return _names_.""",
  )
}
