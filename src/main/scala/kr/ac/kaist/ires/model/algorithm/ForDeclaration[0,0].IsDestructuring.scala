package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ForDeclaration[0,0].IsDestructuring` extends Algo {
  val head = SyntaxDirectedHead("ForDeclaration", 0, 0, Rhs(List(NonTerminal("LetOrConst", List(""), false), NonTerminal("ForBinding", List(""), false)), None), "IsDestructuring", List())
  val ids = List(
    "sec-static-semantics-isdestructuring",
    "sec-for-in-and-for-of-statements",
    "sec-iteration-statements",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ForBinding "IsDestructuring")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return IsDestructuring of |ForBinding|.""",
  )
}
