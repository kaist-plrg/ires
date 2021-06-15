package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IsDataDescriptor` extends Algo {
  val head = NormalHead("IsDataDescriptor", List(Param("Desc", Normal)))
  val ids = List(
    "sec-isdatadescriptor",
    "sec-property-descriptor-specification-type",
    "sec-ecmascript-specification-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:if (= Desc undefined) return false else 0:{}
  |  1:if (&& (= Desc.Value absent) (= Desc.Writable absent)) return false else 0:{}
  |  2:return true
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If _Desc_ is *undefined*, return *false*.""",
    """          1. If both _Desc_.[[Value]] and _Desc_.[[Writable]] are absent, return *false*.""",
    """          1. Return *true*.""",
  )
}
