package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Elision[1,0].IteratorDestructuringAssignmentEvaluation` extends Algo {
  val head = SyntaxDirectedHead("Elision", 1, 0, Rhs(List(NonTerminal("Elision", List(""), false), Terminal(",")), None), "IteratorDestructuringAssignmentEvaluation", List(Param("iteratorRecord", Normal)))
  val ids = List(
    "sec-runtime-semantics-iteratordestructuringassignmentevaluation",
    "sec-destructuring-assignment",
    "sec-assignment-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (Elision "IteratorDestructuringAssignmentEvaluation" iteratorRecord)
  |  0:[? __x0__]
  |  1:if (= iteratorRecord.Done false) {
  |    2:app __x1__ = (IteratorStep iteratorRecord)
  |    2:let next = __x1__
  |    3:app __x2__ = (IsAbruptCompletion next)
  |    3:if __x2__ iteratorRecord.Done = true else 10:{}
  |    4:[? next]
  |    5:if (= next false) iteratorRecord.Done = true else 10:{}
  |  } else 10:{}
  |  6:return CONST_empty
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Perform ? IteratorDestructuringAssignmentEvaluation of |Elision| with _iteratorRecord_ as the argument.""",
    """          1. If _iteratorRecord_.[[Done]] is *false*, then""",
    """            1. Let _next_ be IteratorStep(_iteratorRecord_).""",
    """            1. If _next_ is an abrupt completion, set _iteratorRecord_.[[Done]] to *true*.""",
    """            1. ReturnIfAbrupt(_next_).""",
    """            1. If _next_ is *false*, set _iteratorRecord_.[[Done]] to *true*.""",
    """          1. Return NormalCompletion(~empty~).""",
  )
}
