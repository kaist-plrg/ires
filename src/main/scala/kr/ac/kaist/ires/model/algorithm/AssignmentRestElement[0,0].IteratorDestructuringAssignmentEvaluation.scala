package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AssignmentRestElement[0,0].IteratorDestructuringAssignmentEvaluation` extends Algo {
  val head = SyntaxDirectedHead("AssignmentRestElement", 0, 0, Rhs(List(Terminal("..."), NonTerminal("DestructuringAssignmentTarget", List(""), false)), None), "IteratorDestructuringAssignmentEvaluation", List(Param("iteratorRecord", Normal)))
  val ids = List(
    "sec-runtime-semantics-iteratordestructuringassignmentevaluation",
    "sec-destructuring-assignment",
    "sec-assignment-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:if (! (|| (is-instance-of DestructuringAssignmentTarget ObjectLiteral) (is-instance-of DestructuringAssignmentTarget ArrayLiteral))) {
  |    1:access __x0__ = (DestructuringAssignmentTarget "Evaluation")
  |    1:let lref = __x0__
  |    2:[? lref]
  |  } else 10:{}
  |  3:app __x1__ = (ArrayCreate 0i)
  |  3:let A = [! __x1__]
  |  4:let n = 0i
  |  5:while (= iteratorRecord.Done false) {
  |    6:app __x2__ = (IteratorStep iteratorRecord)
  |    6:let next = __x2__
  |    7:app __x3__ = (IsAbruptCompletion next)
  |    7:if __x3__ iteratorRecord.Done = true else 10:{}
  |    8:[? next]
  |    10:if (= next false) iteratorRecord.Done = true else {
  |      11:app __x4__ = (IteratorValue next)
  |      11:let nextValue = __x4__
  |      12:app __x5__ = (IsAbruptCompletion nextValue)
  |      12:if __x5__ iteratorRecord.Done = true else 10:{}
  |      13:[? nextValue]
  |      14:app __x6__ = (ToString n)
  |      14:app __x7__ = (CreateDataPropertyOrThrow A [! __x6__] nextValue)
  |      14:[! __x7__]
  |      15:n = (+ n 1i)
  |    }
  |  }
  |  16:if (! (|| (is-instance-of DestructuringAssignmentTarget ObjectLiteral) (is-instance-of DestructuringAssignmentTarget ArrayLiteral))) {
  |    17:app __x8__ = (PutValue lref A)
  |    17:return [? __x8__]
  |  } else 10:{}
  |  18:let nestedAssignmentPattern = (parse-syntax DestructuringAssignmentTarget "AssignmentPattern" (new []))
  |  19:access __x9__ = (nestedAssignmentPattern "DestructuringAssignmentEvaluation" A)
  |  19:return __x9__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If |DestructuringAssignmentTarget| is neither an |ObjectLiteral| nor an |ArrayLiteral|, then""",
    """            1. Let _lref_ be the result of evaluating |DestructuringAssignmentTarget|.""",
    """            1. ReturnIfAbrupt(_lref_).""",
    """          1. Let _A_ be ! ArrayCreate(0).""",
    """          1. Let _n_ be 0.""",
    """          1. Repeat, while _iteratorRecord_.[[Done]] is *false*,""",
    """            1. Let _next_ be IteratorStep(_iteratorRecord_).""",
    """            1. If _next_ is an abrupt completion, set _iteratorRecord_.[[Done]] to *true*.""",
    """            1. ReturnIfAbrupt(_next_).""",
    """            1. If _next_ is *false*, set _iteratorRecord_.[[Done]] to *true*.""",
    """            1. Else,""",
    """              1. Let _nextValue_ be IteratorValue(_next_).""",
    """              1. If _nextValue_ is an abrupt completion, set _iteratorRecord_.[[Done]] to *true*.""",
    """              1. ReturnIfAbrupt(_nextValue_).""",
    """              1. Perform ! CreateDataPropertyOrThrow(_A_, ! ToString(𝔽(_n_)), _nextValue_).""",
    """              1. Set _n_ to _n_ + 1.""",
    """          1. If |DestructuringAssignmentTarget| is neither an |ObjectLiteral| nor an |ArrayLiteral|, then""",
    """            1. Return ? PutValue(_lref_, _A_).""",
    """          1. Let _nestedAssignmentPattern_ be the |AssignmentPattern| that is covered by |DestructuringAssignmentTarget|.""",
    """          1. Return the result of performing DestructuringAssignmentEvaluation of _nestedAssignmentPattern_ with _A_ as the argument.""",
  )
}
