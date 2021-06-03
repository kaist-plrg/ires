package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::WithStatement[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("WithStatement", 0, 0, Rhs(List(Terminal("with"), Terminal("("), NonTerminal("Expression", List(""), false), Terminal(")"), NonTerminal("Statement", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-with-statement-runtime-semantics-evaluation",
    "sec-with-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (Expression "Evaluation")
  |  0:let val = __x0__
  |  1:app __x1__ = (GetValue val)
  |  1:app __x2__ = (ToObject [? __x1__])
  |  1:let obj = [? __x2__]
  |  2:let oldEnv = CONTEXT.LexicalEnvironment
  |  3:app __x3__ = (NewObjectEnvironment obj oldEnv)
  |  3:let newEnv = __x3__
  |  4:newEnv.withEnvironment = true
  |  5:CONTEXT.LexicalEnvironment = newEnv
  |  6:access __x4__ = (Statement "Evaluation")
  |  6:let C = __x4__
  |  7:CONTEXT.LexicalEnvironment = oldEnv
  |  8:app __x5__ = (UpdateEmpty C undefined)
  |  8:return __x5__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _val_ be the result of evaluating |Expression|.""",
    """        1. Let _obj_ be ? ToObject(? GetValue(_val_)).""",
    """        1. Let _oldEnv_ be the running execution context's LexicalEnvironment.""",
    """        1. Let _newEnv_ be NewObjectEnvironment(_obj_, _oldEnv_).""",
    """        1. Set the _withEnvironment_ flag of _newEnv_ to *true*.""",
    """        1. Set the running execution context's LexicalEnvironment to _newEnv_.""",
    """        1. Let _C_ be the result of evaluating |Statement|.""",
    """        1. Set the running execution context's LexicalEnvironment to _oldEnv_.""",
    """        1. Return Completion(UpdateEmpty(_C_, *undefined*)).""",
  )
}
