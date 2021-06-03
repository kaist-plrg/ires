package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IsNoTearConfiguration` extends Algo {
  val head = NormalHead("IsNoTearConfiguration", List(Param("type", Normal), Param("order", Normal)))
  val ids = List(
    "sec-isnotearconfiguration",
    "sec-abstract-operations-for-arraybuffer-objects",
    "sec-arraybuffer-objects",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsUnclampedIntegerElementType type)
  |  0:if (= [! __x0__] true) return true else 0:{}
  |  1:app __x1__ = (IsBigIntElementType type)
  |  1:if (&& (= [! __x1__] true) (! (|| (= order CONST_Init) (= order CONST_Unordered)))) return true else 0:{}
  |  2:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If ! IsUnclampedIntegerElementType(_type_) is *true*, return *true*.""",
    """          1. If ! IsBigIntElementType(_type_) is *true* and _order_ is not ~Init~ or ~Unordered~, return *true*.""",
    """          1. Return *false*.""",
  )
}
