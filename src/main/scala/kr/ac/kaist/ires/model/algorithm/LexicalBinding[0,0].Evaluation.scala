package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::LexicalBinding[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("LexicalBinding", 0, 0, Rhs(List(NonTerminal("BindingIdentifier", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-let-and-const-declarations-runtime-semantics-evaluation",
    "sec-let-and-const-declarations",
    "sec-declarations-and-the-variable-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (BindingIdentifier "StringValue")
  |  0:app __x1__ = (ResolveBinding __x0__)
  |  0:let lhs = __x1__
  |  1:app __x2__ = (InitializeReferencedBinding lhs undefined)
  |  1:return __x2__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _lhs_ be ResolveBinding(StringValue of |BindingIdentifier|).""",
    """          1. Return InitializeReferencedBinding(_lhs_, *undefined*).""",
  )
}
