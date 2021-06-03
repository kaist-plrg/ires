package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Elision[0,0].ArrayAccumulation` extends Algo {
  val head = SyntaxDirectedHead("Elision", 0, 0, Rhs(List(Terminal(",")), None), "ArrayAccumulation", List(Param("array", Normal), Param("nextIndex", Normal)))
  val ids = List(
    "sec-runtime-semantics-arrayaccumulation",
    "sec-array-initializer",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:let len = (+ nextIndex 1i)
  |  1:app __x0__ = (Set array "length" len true)
  |  1:[? __x0__]
  |  3:return len
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _len_ be _nextIndex_ + 1.""",
    """          1. Perform ? Set(_array_, *"length"*, 𝔽(_len_), *true*).""",
    """          1. NOTE: The above Set throws if _len_ exceeds 2<sup>32</sup>-1.""",
    """          1. Return _len_.""",
  )
}
