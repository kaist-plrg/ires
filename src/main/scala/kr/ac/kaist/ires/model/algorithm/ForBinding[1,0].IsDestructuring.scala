package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ForBinding[1,0].IsDestructuring` extends Algo {
  val head = SyntaxDirectedHead("ForBinding", 1, 0, Rhs(List(NonTerminal("BindingPattern", List(""), false)), None), "IsDestructuring", List())
  val ids = List(
    "sec-static-semantics-isdestructuring",
    "sec-for-in-and-for-of-statements",
    "sec-iteration-statements",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""return true""".stripMargin)
  val code = scala.Array[String](
    """          1. Return *true*.""",
  )
}
