package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ExportsList[1,0].ExportEntriesForModule` extends Algo {
  val head = SyntaxDirectedHead("ExportsList", 1, 0, Rhs(List(NonTerminal("ExportsList", List(""), false), Terminal(","), NonTerminal("ExportSpecifier", List(""), false)), None), "ExportEntriesForModule", List(Param("module", Normal)))
  val ids = List(
    "sec-static-semantics-exportentriesformodule",
    "sec-exports",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ExportsList "ExportEntriesForModule" module)
  |  0:let specs = __x0__
  |  1:access __x1__ = (ExportSpecifier "ExportEntriesForModule" module)
  |  1:let __x2__ = __x1__
  |  1:let __x3__ = 0i
  |  1:while (< __x3__ __x2__.length) {
  |    let __x4__ = __x2__[__x3__]
  |    append __x4__ -> specs
  |    __x3__ = (+ __x3__ 1i)
  |  }
  |  2:return specs
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _specs_ be the ExportEntriesForModule of |ExportsList| with argument _module_.""",
    """          1. Append to _specs_ the elements of the ExportEntriesForModule of |ExportSpecifier| with argument _module_.""",
    """          1. Return _specs_.""",
  )
}
