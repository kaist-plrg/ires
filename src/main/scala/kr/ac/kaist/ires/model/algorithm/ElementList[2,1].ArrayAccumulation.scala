package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ElementList[2,1].ArrayAccumulation` extends Algo {
  val head = SyntaxDirectedHead("ElementList", 2, 1, Rhs(List(NonTerminal("ElementList", List(""), false), Terminal(","), NonTerminal("Elision", List(""), true), NonTerminal("AssignmentExpression", List(""), false)), None), "ArrayAccumulation", List(Param("array", Normal), Param("nextIndex", Normal)))
  val ids = List(
    "sec-runtime-semantics-arrayaccumulation",
    "sec-array-initializer",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ElementList "ArrayAccumulation" array nextIndex)
  |  0:nextIndex = __x0__
  |  1:[? nextIndex]
  |  2:if (! (= Elision absent)) {
  |    3:access __x1__ = (Elision "ArrayAccumulation" array nextIndex)
  |    3:nextIndex = __x1__
  |    4:[? nextIndex]
  |  } else 2:{}
  |  5:access __x2__ = (AssignmentExpression "Evaluation")
  |  5:let initResult = __x2__
  |  6:app __x3__ = (GetValue initResult)
  |  6:let initValue = [? __x3__]
  |  7:app __x4__ = (ToString nextIndex)
  |  7:app __x5__ = (CreateDataPropertyOrThrow array [! __x4__] initValue)
  |  7:let created = [! __x5__]
  |  8:return (+ nextIndex 1i)
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Set _nextIndex_ to the result of performing ArrayAccumulation for |ElementList| with arguments _array_ and _nextIndex_.""",
    """          1. ReturnIfAbrupt(_nextIndex_).""",
    """          1. If |Elision| is present, then""",
    """            1. Set _nextIndex_ to the result of performing ArrayAccumulation for |Elision| with arguments _array_ and _nextIndex_.""",
    """            1. ReturnIfAbrupt(_nextIndex_).""",
    """          1. Let _initResult_ be the result of evaluating |AssignmentExpression|.""",
    """          1. Let _initValue_ be ? GetValue(_initResult_).""",
    """          1. Let _created_ be ! CreateDataPropertyOrThrow(_array_, ! ToString(𝔽(_nextIndex_)), _initValue_).""",
    """          1. Return _nextIndex_ + 1.""",
  )
}
