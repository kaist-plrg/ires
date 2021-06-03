package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArrayLiteral[0,1].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("ArrayLiteral", 0, 1, Rhs(List(Terminal("["), NonTerminal("Elision", List(""), true), Terminal("]")), None), "Evaluation", List())
  val ids = List(
    "sec-array-initializer-runtime-semantics-evaluation",
    "sec-array-initializer",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ArrayCreate 0i)
  |  0:let array = [! __x0__]
  |  1:if (! (= Elision absent)) {
  |    2:access __x1__ = (Elision "ArrayAccumulation" array 0i)
  |    2:let len = __x1__
  |    3:[? len]
  |  } else 2:{}
  |  4:return array
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _array_ be ! ArrayCreate(0).""",
    """          1. If |Elision| is present, then""",
    """            1. Let _len_ be the result of performing ArrayAccumulation for |Elision| with arguments _array_ and 0.""",
    """            1. ReturnIfAbrupt(_len_).""",
    """          1. Return _array_.""",
  )
}
