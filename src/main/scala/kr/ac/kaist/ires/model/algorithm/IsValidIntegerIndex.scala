package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IsValidIntegerIndex` extends Algo {
  val head = NormalHead("IsValidIntegerIndex", List(Param("O", Normal), Param("index", Normal)))
  val ids = List(
    "sec-isvalidintegerindex",
    "sec-integer-indexed-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  1:app __x0__ = (IsDetachedBuffer O.ViewedArrayBuffer)
  |  1:if (= __x0__ true) return false else 0:{}
  |  2:app __x1__ = (IsIntegralNumber index)
  |  2:if (= [! __x1__] false) return false else 0:{}
  |  3:if (= index -0.0) return false else 0:{}
  |  4:if (|| (< index 0i) (! (< index O.ArrayLength))) return false else 0:{}
  |  5:return true
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: _O_ is an Integer-Indexed exotic object.""",
    """          1. If IsDetachedBuffer(_O_.[[ViewedArrayBuffer]]) is *true*, return *false*.""",
    """          1. If ! IsIntegralNumber(_index_) is *false*, return *false*.""",
    """          1. If _index_ is *-0*<sub>𝔽</sub>, return *false*.""",
    """          1. If ℝ(_index_) < 0 or ℝ(_index_) ≥ _O_.[[ArrayLength]], return *false*.""",
    """          1. Return *true*.""",
  )
}
