package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::VariableDeclaration[1,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("VariableDeclaration", 1, 0, Rhs(List(NonTerminal("BindingPattern", List(""), false), NonTerminal("Initializer", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-variable-statement-runtime-semantics-evaluation",
    "sec-variable-statement",
    "sec-declarations-and-the-variable-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (Initializer "Evaluation")
  |  0:let rhs = __x0__
  |  1:app __x1__ = (GetValue rhs)
  |  1:let rval = [? __x1__]
  |  2:access __x2__ = (BindingPattern "BindingInitialization" rval undefined)
  |  2:return __x2__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _rhs_ be the result of evaluating |Initializer|.""",
    """          1. Let _rval_ be ? GetValue(_rhs_).""",
    """          1. Return the result of performing BindingInitialization for |BindingPattern| passing _rval_ and *undefined* as arguments.""",
  )
}
