package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ForBodyEvaluation` extends Algo {
  val head = NormalHead("ForBodyEvaluation", List(Param("test", Normal), Param("increment", Normal), Param("stmt", Normal), Param("perIterationBindings", Normal), Param("labelSet", Normal)))
  val ids = List(
    "sec-forbodyevaluation",
    "sec-for-statement",
    "sec-iteration-statements",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:let V = undefined
  |  1:app __x0__ = (CreatePerIterationEnvironment perIterationBindings)
  |  1:[? __x0__]
  |  2:while true {
  |    3:if (! (= test absent)) {
  |      4:access __x1__ = (test "Evaluation")
  |      4:let testRef = __x1__
  |      5:app __x2__ = (GetValue testRef)
  |      5:let testValue = [? __x2__]
  |      6:app __x3__ = (ToBoolean testValue)
  |      6:if (= [! __x3__] false) return V else 10:{}
  |    } else 10:{}
  |    7:access __x4__ = (stmt "Evaluation")
  |    7:let result = __x4__
  |    8:app __x5__ = (LoopContinues result labelSet)
  |    8:if (= __x5__ false) {
  |      app __x6__ = (UpdateEmpty result V)
  |      return __x6__
  |    } else 10:{}
  |    9:if (! (= result.Value CONST_empty)) V = result.Value else 10:{}
  |    10:app __x7__ = (CreatePerIterationEnvironment perIterationBindings)
  |    10:[? __x7__]
  |    11:if (! (= increment absent)) {
  |      12:access __x8__ = (increment "Evaluation")
  |      12:let incRef = __x8__
  |      13:app __x9__ = (GetValue incRef)
  |      13:[? __x9__]
  |    } else 10:{}
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _V_ be *undefined*.""",
    """          1. Perform ? CreatePerIterationEnvironment(_perIterationBindings_).""",
    """          1. Repeat,""",
    """            1. If _test_ is not ~[empty]~, then""",
    """              1. Let _testRef_ be the result of evaluating _test_.""",
    """              1. Let _testValue_ be ? GetValue(_testRef_).""",
    """              1. If ! ToBoolean(_testValue_) is *false*, return NormalCompletion(_V_).""",
    """            1. Let _result_ be the result of evaluating _stmt_.""",
    """            1. If LoopContinues(_result_, _labelSet_) is *false*, return Completion(UpdateEmpty(_result_, _V_)).""",
    """            1. If _result_.[[Value]] is not ~empty~, set _V_ to _result_.[[Value]].""",
    """            1. Perform ? CreatePerIterationEnvironment(_perIterationBindings_).""",
    """            1. If _increment_ is not ~[empty]~, then""",
    """              1. Let _incRef_ be the result of evaluating _increment_.""",
    """              1. Perform ? GetValue(_incRef_).""",
  )
}
