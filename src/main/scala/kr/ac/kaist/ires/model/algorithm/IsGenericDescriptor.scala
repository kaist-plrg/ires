package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IsGenericDescriptor` extends Algo {
  val head = NormalHead("IsGenericDescriptor", List(Param("Desc", Normal)))
  val ids = List(
    "sec-isgenericdescriptor",
    "sec-property-descriptor-specification-type",
    "sec-ecmascript-specification-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:if (= Desc undefined) return false else 0:{}
  |  1:app __x0__ = (IsAccessorDescriptor Desc)
  |  1:app __x1__ = (IsDataDescriptor Desc)
  |  1:if (&& (= __x0__ false) (= __x1__ false)) return true else 0:{}
  |  2:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If _Desc_ is *undefined*, return *false*.""",
    """          1. If IsAccessorDescriptor(_Desc_) and IsDataDescriptor(_Desc_) are both *false*, return *true*.""",
    """          1. Return *false*.""",
  )
}
