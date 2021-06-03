package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::LexicalBinding[1,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("LexicalBinding", 1, 0, Rhs(List(NonTerminal("BindingPattern", List(""), false), NonTerminal("Initializer", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-let-and-const-declarations-runtime-semantics-evaluation",
    "sec-let-and-const-declarations",
    "sec-declarations-and-the-variable-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (Initializer "Evaluation")
  |  0:let rhs = __x0__
  |  1:app __x1__ = (GetValue rhs)
  |  1:let value = [? __x1__]
  |  2:let env = CONTEXT.LexicalEnvironment
  |  3:access __x2__ = (BindingPattern "BindingInitialization" value env)
  |  3:return __x2__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _rhs_ be the result of evaluating |Initializer|.""",
    """          1. Let _value_ be ? GetValue(_rhs_).""",
    """          1. Let _env_ be the running execution context's LexicalEnvironment.""",
    """          1. Return the result of performing BindingInitialization for |BindingPattern| using _value_ and _env_ as the arguments.""",
  )
}
