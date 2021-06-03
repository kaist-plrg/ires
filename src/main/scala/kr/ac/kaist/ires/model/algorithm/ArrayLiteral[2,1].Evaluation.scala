package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArrayLiteral[2,1].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("ArrayLiteral", 2, 1, Rhs(List(Terminal("["), NonTerminal("ElementList", List(""), false), Terminal(","), NonTerminal("Elision", List(""), true), Terminal("]")), None), "Evaluation", List())
  val ids = List(
    "sec-array-initializer-runtime-semantics-evaluation",
    "sec-array-initializer",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ArrayCreate 0i)
  |  0:let array = [! __x0__]
  |  1:access __x1__ = (ElementList "ArrayAccumulation" array 0i)
  |  1:let nextIndex = __x1__
  |  2:[? nextIndex]
  |  3:if (! (= Elision absent)) {
  |    4:access __x2__ = (Elision "ArrayAccumulation" array nextIndex)
  |    4:let len = __x2__
  |    5:[? len]
  |  } else 2:{}
  |  6:return array
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _array_ be ! ArrayCreate(0).""",
    """          1. Let _nextIndex_ be the result of performing ArrayAccumulation for |ElementList| with arguments _array_ and 0.""",
    """          1. ReturnIfAbrupt(_nextIndex_).""",
    """          1. If |Elision| is present, then""",
    """            1. Let _len_ be the result of performing ArrayAccumulation for |Elision| with arguments _array_ and _nextIndex_.""",
    """            1. ReturnIfAbrupt(_len_).""",
    """          1. Return _array_.""",
  )
}
