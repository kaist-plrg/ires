package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ImportClause[3,0].ImportEntriesForModule` extends Algo {
  val head = SyntaxDirectedHead("ImportClause", 3, 0, Rhs(List(NonTerminal("ImportedDefaultBinding", List(""), false), Terminal(","), NonTerminal("NameSpaceImport", List(""), false)), None), "ImportEntriesForModule", List(Param("module", Normal)))
  val ids = List(
    "sec-static-semantics-importentriesformodule",
    "sec-imports",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ImportedDefaultBinding "ImportEntriesForModule" module)
  |  0:let entries = __x0__
  |  1:access __x1__ = (NameSpaceImport "ImportEntriesForModule" module)
  |  1:let __x2__ = __x1__
  |  1:let __x3__ = 0i
  |  1:while (< __x3__ __x2__.length) {
  |    let __x4__ = __x2__[__x3__]
  |    append __x4__ -> entries
  |    __x3__ = (+ __x3__ 1i)
  |  }
  |  2:return entries
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _entries_ be ImportEntriesForModule of |ImportedDefaultBinding| with argument _module_.""",
    """          1. Append to _entries_ the elements of the ImportEntriesForModule of |NameSpaceImport| with argument _module_.""",
    """          1. Return _entries_.""",
  )
}
