package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArrayAssignmentPattern[0,3].DestructuringAssignmentEvaluation` extends Algo {
  val head = SyntaxDirectedHead("ArrayAssignmentPattern", 0, 3, Rhs(List(Terminal("["), NonTerminal("Elision", List(""), true), NonTerminal("AssignmentRestElement", List(""), false), Terminal("]")), None), "DestructuringAssignmentEvaluation", List(Param("value", Normal)))
  val ids = List(
    "sec-runtime-semantics-destructuringassignmentevaluation",
    "sec-destructuring-assignment",
    "sec-assignment-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (GetIterator value)
  |  0:let iteratorRecord = [? __x0__]
  |  1:if (! (= Elision absent)) {
  |    2:access __x1__ = (Elision "IteratorDestructuringAssignmentEvaluation" iteratorRecord)
  |    2:let status = __x1__
  |    3:app __x2__ = (IsAbruptCompletion status)
  |    3:if __x2__ {
  |      4:assert (= iteratorRecord.Done true)
  |      5:return status
  |    } else 10:{}
  |  } else 10:{}
  |  6:access __x3__ = (AssignmentRestElement "IteratorDestructuringAssignmentEvaluation" iteratorRecord)
  |  6:let result = __x3__
  |  7:if (= iteratorRecord.Done false) {
  |    app __x4__ = (IteratorClose iteratorRecord result)
  |    return [? __x4__]
  |  } else 10:{}
  |  8:return result
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _iteratorRecord_ be ? GetIterator(_value_).""",
    """          1. If |Elision| is present, then""",
    """            1. Let _status_ be IteratorDestructuringAssignmentEvaluation of |Elision| with argument _iteratorRecord_.""",
    """            1. If _status_ is an abrupt completion, then""",
    """              1. Assert: _iteratorRecord_.[[Done]] is *true*.""",
    """              1. Return Completion(_status_).""",
    """          1. Let _result_ be IteratorDestructuringAssignmentEvaluation of |AssignmentRestElement| with argument _iteratorRecord_.""",
    """          1. If _iteratorRecord_.[[Done]] is *false*, return ? IteratorClose(_iteratorRecord_, _result_).""",
    """          1. Return _result_.""",
  )
}
