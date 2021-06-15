package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArrayAssignmentPattern[0,0].DestructuringAssignmentEvaluation` extends Algo {
  val head = SyntaxDirectedHead("ArrayAssignmentPattern", 0, 0, Rhs(List(Terminal("["), Terminal("]")), None), "DestructuringAssignmentEvaluation", List(Param("value", Normal)))
  val ids = List(
    "sec-runtime-semantics-destructuringassignmentevaluation",
    "sec-destructuring-assignment",
    "sec-assignment-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (GetIterator value)
  |  0:let iteratorRecord = [? __x0__]
  |  1:app __x1__ = (NormalCompletion CONST_empty)
  |  1:app __x2__ = (IteratorClose iteratorRecord __x1__)
  |  1:return [? __x2__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _iteratorRecord_ be ? GetIterator(_value_).""",
    """          1. Return ? IteratorClose(_iteratorRecord_, NormalCompletion(~empty~)).""",
  )
}
