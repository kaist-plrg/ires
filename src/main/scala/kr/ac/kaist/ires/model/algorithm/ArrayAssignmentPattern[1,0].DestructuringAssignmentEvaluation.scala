package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArrayAssignmentPattern[1,0].DestructuringAssignmentEvaluation` extends Algo {
  val head = SyntaxDirectedHead("ArrayAssignmentPattern", 1, 0, Rhs(List(Terminal("["), NonTerminal("AssignmentElementList", List(""), false), Terminal("]")), None), "DestructuringAssignmentEvaluation", List(Param("value", Normal)))
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
  |  1:let result = __x1__
  |  2:if (= iteratorRecord.Done false) {
  |    app __x2__ = (IteratorClose iteratorRecord result)
  |    return [? __x2__]
  |  } else 10:{}
  |  3:return result
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _iteratorRecord_ be ? GetIterator(_value_).""",
    """          1. Let _result_ be IteratorDestructuringAssignmentEvaluation of |AssignmentElementList| with argument _iteratorRecord_.""",
    """          1. If _iteratorRecord_.[[Done]] is *false*, return ? IteratorClose(_iteratorRecord_, _result_).""",
    """          1. Return _result_.""",
  )
}
