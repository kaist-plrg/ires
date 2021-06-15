package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IsWordChar` extends Algo {
  val head = NormalHead("IsWordChar", List(Param("e", Normal)))
  val ids = List(
    "sec-runtime-semantics-iswordchar-abstract-operation",
    "sec-assertion",
    "sec-pattern-semantics",
    "sec-regexp-regular-expression-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:if (|| (== e -1i) (= e InputLength)) return false else 5:{}
  |  1:let c = Input[e]
  |  2:if (contains WordCharacters c) return true else 5:{}
  |  3:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. If _e_ = -1 or _e_ is _InputLength_, return *false*.""",
    """            1. Let _c_ be the character _Input_[_e_].""",
    """            1. If _c_ is in _WordCharacters_, return *true*.""",
    """            1. Return *false*.""",
  )
}
