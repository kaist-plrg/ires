package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Elision[0,0].IteratorDestructuringAssignmentEvaluation` extends Algo {
  val head = SyntaxDirectedHead("Elision", 0, 0, Rhs(List(Terminal(",")), None), "IteratorDestructuringAssignmentEvaluation", List(Param("iteratorRecord", Normal)))
  val ids = List(
    "sec-runtime-semantics-iteratordestructuringassignmentevaluation",
    "sec-destructuring-assignment",
    "sec-assignment-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:if (= iteratorRecord.Done false) {
  |    1:app __x0__ = (IteratorStep iteratorRecord)
  |    1:let next = __x0__
  |    2:app __x1__ = (IsAbruptCompletion next)
  |    2:if __x1__ iteratorRecord.Done = true else 10:{}
  |    3:[? next]
  |    4:if (= next false) iteratorRecord.Done = true else 10:{}
  |  } else 10:{}
  |  5:return CONST_empty
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If _iteratorRecord_.[[Done]] is *false*, then""",
    """            1. Let _next_ be IteratorStep(_iteratorRecord_).""",
    """            1. If _next_ is an abrupt completion, set _iteratorRecord_.[[Done]] to *true*.""",
    """            1. ReturnIfAbrupt(_next_).""",
    """            1. If _next_ is *false*, set _iteratorRecord_.[[Done]] to *true*.""",
    """          1. Return NormalCompletion(~empty~).""",
  )
}
