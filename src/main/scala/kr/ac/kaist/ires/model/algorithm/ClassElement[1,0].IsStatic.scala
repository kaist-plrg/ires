package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ClassElement[1,0].IsStatic` extends Algo {
  val head = SyntaxDirectedHead("ClassElement", 1, 0, Rhs(List(Terminal("static"), NonTerminal("MethodDefinition", List(""), false)), None), "IsStatic", List())
  val ids = List(
    "sec-static-semantics-isstatic",
    "sec-class-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""return true""".stripMargin)
  val code = scala.Array[String](
    """        1. Return *true*.""",
  )
}
