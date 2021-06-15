package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Elision[1,0].ArrayAccumulation` extends Algo {
  val head = SyntaxDirectedHead("Elision", 1, 0, Rhs(List(NonTerminal("Elision", List(""), false), Terminal(",")), None), "ArrayAccumulation", List(Param("array", Normal), Param("nextIndex", Normal)))
  val ids = List(
    "sec-runtime-semantics-arrayaccumulation",
    "sec-array-initializer",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (Elision "ArrayAccumulation" array (+ nextIndex 1i))
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return the result of performing ArrayAccumulation for |Elision| with arguments _array_ and _nextIndex_ + 1.""",
  )
}
