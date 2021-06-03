package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IsArray` extends Algo {
  val head = NormalHead("IsArray", List(Param("argument", Normal)))
  val ids = List(
    "sec-isarray",
    "sec-testing-and-comparison-operations",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= (typeof argument) Object)) return false else 0:{}
  |  1:if (is-instance-of argument ArrayExoticObject) return true else 0:{}
  |  2:if (is-instance-of argument ProxyExoticObject) {
  |    3:if (= argument.ProxyHandler null) throw TypeError else 0:{}
  |    4:let target = argument.ProxyTarget
  |    5:app __x0__ = (IsArray target)
  |    5:return [? __x0__]
  |  } else 0:{}
  |  6:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If Type(_argument_) is not Object, return *false*.""",
    """        1. If _argument_ is an Array exotic object, return *true*.""",
    """        1. If _argument_ is a Proxy exotic object, then""",
    """          1. If _argument_.[[ProxyHandler]] is *null*, throw a *TypeError* exception.""",
    """          1. Let _target_ be _argument_.[[ProxyTarget]].""",
    """          1. Return ? IsArray(_target_).""",
    """        1. Return *false*.""",
  )
}
