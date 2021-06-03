package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Script[0,1].IsStrict` extends Algo {
  val head = SyntaxDirectedHead("Script", 0, 1, Rhs(List(NonTerminal("ScriptBody", List(""), true)), None), "IsStrict", List())
  val ids = List(
    "sec-static-semantics-isstrict",
    "sec-scripts",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""??? "If nt:{ScriptBody} is present and the Directive Prologue of nt:{ScriptBody} contains a Use Strict Directive , return value:{true} ; otherwise , return value:{false} ."""".stripMargin)
  val code = scala.Array[String](
    """        1. If |ScriptBody| is present and the Directive Prologue of |ScriptBody| contains a Use Strict Directive, return *true*; otherwise, return *false*.""",
  )
}
