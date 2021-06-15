package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FunctionBody[0,0].FunctionBodyContainsUseStrict` extends Algo {
  val head = SyntaxDirectedHead("FunctionBody", 0, 0, Rhs(List(NonTerminal("FunctionStatementList", List(""), false)), None), "FunctionBodyContainsUseStrict", List())
  val ids = List(
    "sec-static-semantics-functionbodycontainsusestrict",
    "sec-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""??? "If the Directive Prologue of nt:{FunctionBody} contains a Use Strict Directive , return value:{true} ; otherwise , return value:{false} ."""".stripMargin)
  val code = scala.Array[String](
    """        1. If the Directive Prologue of |FunctionBody| contains a Use Strict Directive, return *true*; otherwise, return *false*.""",
  )
}
