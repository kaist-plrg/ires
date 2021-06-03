package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ElementList[0,1].ArrayAccumulation` extends Algo {
  val head = SyntaxDirectedHead("ElementList", 0, 1, Rhs(List(NonTerminal("Elision", List(""), true), NonTerminal("AssignmentExpression", List(""), false)), None), "ArrayAccumulation", List(Param("array", Normal), Param("nextIndex", Normal)))
  val ids = List(
    "sec-runtime-semantics-arrayaccumulation",
    "sec-array-initializer",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= Elision absent)) {
  |    1:access __x0__ = (Elision "ArrayAccumulation" array nextIndex)
  |    1:nextIndex = __x0__
  |    2:[? nextIndex]
  |  } else 2:{}
  |  3:access __x1__ = (AssignmentExpression "Evaluation")
  |  3:let initResult = __x1__
  |  4:app __x2__ = (GetValue initResult)
  |  4:let initValue = [? __x2__]
  |  5:app __x3__ = (ToString nextIndex)
  |  5:app __x4__ = (CreateDataPropertyOrThrow array [! __x3__] initValue)
  |  5:let created = [! __x4__]
  |  6:return (+ nextIndex 1i)
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If |Elision| is present, then""",
    """            1. Set _nextIndex_ to the result of performing ArrayAccumulation for |Elision| with arguments _array_ and _nextIndex_.""",
    """            1. ReturnIfAbrupt(_nextIndex_).""",
    """          1. Let _initResult_ be the result of evaluating |AssignmentExpression|.""",
    """          1. Let _initValue_ be ? GetValue(_initResult_).""",
    """          1. Let _created_ be ! CreateDataPropertyOrThrow(_array_, ! ToString(𝔽(_nextIndex_)), _initValue_).""",
    """          1. Return _nextIndex_ + 1.""",
  )
}
