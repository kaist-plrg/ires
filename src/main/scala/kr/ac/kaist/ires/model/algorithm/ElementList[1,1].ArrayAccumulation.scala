package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ElementList[1,1].ArrayAccumulation` extends Algo {
  val head = SyntaxDirectedHead("ElementList", 1, 1, Rhs(List(NonTerminal("Elision", List(""), true), NonTerminal("SpreadElement", List(""), false)), None), "ArrayAccumulation", List(Param("array", Normal), Param("nextIndex", Normal)))
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
  |  3:access __x1__ = (SpreadElement "ArrayAccumulation" array nextIndex)
  |  3:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If |Elision| is present, then""",
    """            1. Set _nextIndex_ to the result of performing ArrayAccumulation for |Elision| with arguments _array_ and _nextIndex_.""",
    """            1. ReturnIfAbrupt(_nextIndex_).""",
    """          1. Return the result of performing ArrayAccumulation for |SpreadElement| with arguments _array_ and _nextIndex_.""",
  )
}
