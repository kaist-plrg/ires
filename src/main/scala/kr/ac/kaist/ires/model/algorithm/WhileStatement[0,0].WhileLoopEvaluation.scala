package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::WhileStatement[0,0].WhileLoopEvaluation` extends Algo {
  val head = SyntaxDirectedHead("WhileStatement", 0, 0, Rhs(List(Terminal("while"), Terminal("("), NonTerminal("Expression", List(""), false), Terminal(")"), NonTerminal("Statement", List(""), false)), None), "WhileLoopEvaluation", List(Param("labelSet", Normal)))
  val ids = List(
    "sec-runtime-semantics-whileloopevaluation",
    "sec-while-statement",
    "sec-iteration-statements",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:let V = undefined
  |  1:while true {
  |    2:access __x0__ = (Expression "Evaluation")
  |    2:let exprRef = __x0__
  |    3:app __x1__ = (GetValue exprRef)
  |    3:let exprValue = [? __x1__]
  |    4:app __x2__ = (ToBoolean exprValue)
  |    4:if (= [! __x2__] false) return V else 10:{}
  |    5:access __x3__ = (Statement "Evaluation")
  |    5:let stmtResult = __x3__
  |    6:app __x4__ = (LoopContinues stmtResult labelSet)
  |    6:if (= __x4__ false) {
  |      app __x5__ = (UpdateEmpty stmtResult V)
  |      return __x5__
  |    } else 10:{}
  |    7:if (! (= stmtResult.Value CONST_empty)) V = stmtResult.Value else 10:{}
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _V_ be *undefined*.""",
    """          1. Repeat,""",
    """            1. Let _exprRef_ be the result of evaluating |Expression|.""",
    """            1. Let _exprValue_ be ? GetValue(_exprRef_).""",
    """            1. If ! ToBoolean(_exprValue_) is *false*, return NormalCompletion(_V_).""",
    """            1. Let _stmtResult_ be the result of evaluating |Statement|.""",
    """            1. If LoopContinues(_stmtResult_, _labelSet_) is *false*, return Completion(UpdateEmpty(_stmtResult_, _V_)).""",
    """            1. If _stmtResult_.[[Value]] is not ~empty~, set _V_ to _stmtResult_.[[Value]].""",
  )
}
