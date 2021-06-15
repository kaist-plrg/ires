package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ClassElement[1,0].ClassElementKind` extends Algo {
  val head = SyntaxDirectedHead("ClassElement", 1, 0, Rhs(List(Terminal("static"), NonTerminal("MethodDefinition", List(""), false)), None), "ClassElementKind", List())
  val ids = List(
    "sec-static-semantics-classelementkind",
    "sec-class-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""return CONST_NonConstructorMethod""".stripMargin)
  val code = scala.Array[String](
    """        1. Return ~NonConstructorMethod~.""",
  )
}
