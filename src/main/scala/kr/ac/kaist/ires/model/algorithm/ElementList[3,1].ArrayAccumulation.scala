package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ElementList[3,1].ArrayAccumulation` extends Algo {
  val head = SyntaxDirectedHead("ElementList", 3, 1, Rhs(List(NonTerminal("ElementList", List(""), false), Terminal(","), NonTerminal("Elision", List(""), true), NonTerminal("SpreadElement", List(""), false)), None), "ArrayAccumulation", List(Param("array", Normal), Param("nextIndex", Normal)))
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
  |  5:access __x2__ = (SpreadElement "ArrayAccumulation" array nextIndex)
  |  5:return __x2__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Set _nextIndex_ to the result of performing ArrayAccumulation for |ElementList| with arguments _array_ and _nextIndex_.""",
    """          1. ReturnIfAbrupt(_nextIndex_).""",
    """          1. If |Elision| is present, then""",
    """            1. Set _nextIndex_ to the result of performing ArrayAccumulation for |Elision| with arguments _array_ and _nextIndex_.""",
    """            1. ReturnIfAbrupt(_nextIndex_).""",
    """          1. Return the result of performing ArrayAccumulation for |SpreadElement| with arguments _array_ and _nextIndex_.""",
  )
}
