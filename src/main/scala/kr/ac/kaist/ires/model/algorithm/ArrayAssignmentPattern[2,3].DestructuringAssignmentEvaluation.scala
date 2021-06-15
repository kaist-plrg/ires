package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArrayAssignmentPattern[2,3].DestructuringAssignmentEvaluation` extends Algo {
  val head = SyntaxDirectedHead("ArrayAssignmentPattern", 2, 3, Rhs(List(Terminal("["), NonTerminal("AssignmentElementList", List(""), false), Terminal(","), NonTerminal("Elision", List(""), true), NonTerminal("AssignmentRestElement", List(""), true), Terminal("]")), None), "DestructuringAssignmentEvaluation", List(Param("value", Normal)))
  val ids = List(
    "sec-runtime-semantics-destructuringassignmentevaluation",
    "sec-destructuring-assignment",
    "sec-assignment-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (GetIterator value)
  |  0:let iteratorRecord = [? __x0__]
  |  1:access __x1__ = (AssignmentElementList "IteratorDestructuringAssignmentEvaluation" iteratorRecord)
  |  1:let status = __x1__
  |  2:app __x2__ = (IsAbruptCompletion status)
  |  2:if __x2__ {
  |    3:if (= iteratorRecord.Done false) {
  |      app __x3__ = (IteratorClose iteratorRecord status)
  |      return [? __x3__]
  |    } else 10:{}
  |    4:return status
  |  } else 10:{}
  |  5:if (! (= Elision absent)) {
  |    6:access __x4__ = (Elision "IteratorDestructuringAssignmentEvaluation" iteratorRecord)
  |    6:status = __x4__
  |    7:app __x5__ = (IsAbruptCompletion status)
  |    7:if __x5__ {
  |      8:assert (= iteratorRecord.Done true)
  |      9:return status
  |    } else 10:{}
  |  } else 10:{}
  |  10:if (! (= AssignmentRestElement absent)) {
  |    11:access __x6__ = (AssignmentRestElement "IteratorDestructuringAssignmentEvaluation" iteratorRecord)
  |    11:status = __x6__
  |  } else 10:{}
  |  12:if (= iteratorRecord.Done false) {
  |    app __x7__ = (IteratorClose iteratorRecord status)
  |    return [? __x7__]
  |  } else 10:{}
  |  13:return status
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _iteratorRecord_ be ? GetIterator(_value_).""",
    """          1. Let _status_ be IteratorDestructuringAssignmentEvaluation of |AssignmentElementList| with argument _iteratorRecord_.""",
    """          1. If _status_ is an abrupt completion, then""",
    """            1. If _iteratorRecord_.[[Done]] is *false*, return ? IteratorClose(_iteratorRecord_, _status_).""",
    """            1. Return Completion(_status_).""",
    """          1. If |Elision| is present, then""",
    """            1. Set _status_ to the result of performing IteratorDestructuringAssignmentEvaluation of |Elision| with _iteratorRecord_ as the argument.""",
    """            1. If _status_ is an abrupt completion, then""",
    """              1. Assert: _iteratorRecord_.[[Done]] is *true*.""",
    """              1. Return Completion(_status_).""",
    """          1. If |AssignmentRestElement| is present, then""",
    """            1. Set _status_ to the result of performing IteratorDestructuringAssignmentEvaluation of |AssignmentRestElement| with _iteratorRecord_ as the argument.""",
    """          1. If _iteratorRecord_.[[Done]] is *false*, return ? IteratorClose(_iteratorRecord_, _status_).""",
    """          1. Return Completion(_status_).""",
  )
}
