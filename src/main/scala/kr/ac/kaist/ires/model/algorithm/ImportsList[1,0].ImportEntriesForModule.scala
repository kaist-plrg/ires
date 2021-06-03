package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ImportsList[1,0].ImportEntriesForModule` extends Algo {
  val head = SyntaxDirectedHead("ImportsList", 1, 0, Rhs(List(NonTerminal("ImportsList", List(""), false), Terminal(","), NonTerminal("ImportSpecifier", List(""), false)), None), "ImportEntriesForModule", List(Param("module", Normal)))
  val ids = List(
    "sec-static-semantics-importentriesformodule",
    "sec-imports",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ImportsList "ImportEntriesForModule" module)
  |  0:let specs = __x0__
  |  1:access __x1__ = (ImportSpecifier "ImportEntriesForModule" module)
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
    """          1. Let _specs_ be the ImportEntriesForModule of |ImportsList| with argument _module_.""",
    """          1. Append to _specs_ the elements of the ImportEntriesForModule of |ImportSpecifier| with argument _module_.""",
    """          1. Return _specs_.""",
  )
}
