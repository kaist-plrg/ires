package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::DoWhileStatement[0,0].DoWhileLoopEvaluation` extends Algo {
  val head = SyntaxDirectedHead("DoWhileStatement", 0, 0, Rhs(List(Terminal("do"), NonTerminal("Statement", List(""), false), Terminal("while"), Terminal("("), NonTerminal("Expression", List(""), false), Terminal(")"), Terminal(";")), None), "DoWhileLoopEvaluation", List(Param("labelSet", Normal)))
  val ids = List(
    "sec-runtime-semantics-dowhileloopevaluation",
    "sec-do-while-statement",
    "sec-iteration-statements",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:let V = undefined
  |  1:while true {
  |    2:access __x0__ = (Statement "Evaluation")
  |    2:let stmtResult = __x0__
  |    3:app __x1__ = (LoopContinues stmtResult labelSet)
  |    3:if (= __x1__ false) {
  |      app __x2__ = (UpdateEmpty stmtResult V)
  |      return __x2__
  |    } else 10:{}
  |    4:if (! (= stmtResult.Value CONST_empty)) V = stmtResult.Value else 10:{}
  |    5:access __x3__ = (Expression "Evaluation")
  |    5:let exprRef = __x3__
  |    6:app __x4__ = (GetValue exprRef)
  |    6:let exprValue = [? __x4__]
  |    7:app __x5__ = (ToBoolean exprValue)
  |    7:if (= [! __x5__] false) return V else 10:{}
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _V_ be *undefined*.""",
    """          1. Repeat,""",
    """            1. Let _stmtResult_ be the result of evaluating |Statement|.""",
    """            1. If LoopContinues(_stmtResult_, _labelSet_) is *false*, return Completion(UpdateEmpty(_stmtResult_, _V_)).""",
    """            1. If _stmtResult_.[[Value]] is not ~empty~, set _V_ to _stmtResult_.[[Value]].""",
    """            1. Let _exprRef_ be the result of evaluating |Expression|.""",
    """            1. Let _exprValue_ be ? GetValue(_exprRef_).""",
    """            1. If ! ToBoolean(_exprValue_) is *false*, return NormalCompletion(_V_).""",
  )
}
