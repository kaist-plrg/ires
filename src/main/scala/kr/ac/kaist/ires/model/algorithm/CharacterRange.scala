package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CharacterRange` extends Algo {
  val head = NormalHead("CharacterRange", List(Param("A", Normal), Param("B", Normal)))
  val ids = List(
    "sec-runtime-semantics-characterrange-abstract-operation",
    "sec-nonemptyclassranges",
    "sec-pattern-semantics",
    "sec-regexp-regular-expression-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  1:let a = A[0i]
  |  2:let b = B[0i]
  |  3:let i = a
  |  4:let j = b
  |  5:assert (! (< j i))
  |  6:??? "Return the CharSet containing all characters with a character value greater than or equal to id:{i} and less than or equal to id:{j} ."
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: _A_ and _B_ each contain exactly one character.""",
    """            1. Let _a_ be the one character in CharSet _A_.""",
    """            1. Let _b_ be the one character in CharSet _B_.""",
    """            1. Let _i_ be the character value of character _a_.""",
    """            1. Let _j_ be the character value of character _b_.""",
    """            1. Assert: _i_ ≤ _j_.""",
    """            1. Return the CharSet containing all characters with a character value greater than or equal to _i_ and less than or equal to _j_.""",
  )
}
