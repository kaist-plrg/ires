package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ClassDeclaration[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("ClassDeclaration", 0, 0, Rhs(List(Terminal("class"), NonTerminal("BindingIdentifier", List(""), false), NonTerminal("ClassTail", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-class-definitions-runtime-semantics-evaluation",
    "sec-class-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (this "BindingClassDeclarationEvaluation")
  |  0:[? __x0__]
  |  1:return CONST_empty
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Perform ? BindingClassDeclarationEvaluation of this |ClassDeclaration|.""",
    """        1. Return NormalCompletion(~empty~).""",
  )
}
