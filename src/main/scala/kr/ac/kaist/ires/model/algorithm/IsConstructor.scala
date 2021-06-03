package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IsConstructor` extends Algo {
  val head = NormalHead("IsConstructor", List(Param("argument", Normal)))
  val ids = List(
    "sec-isconstructor",
    "sec-testing-and-comparison-operations",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= (typeof argument) Object)) return false else 0:{}
  |  1:if (! (= argument.Construct absent)) return true else 0:{}
  |  2:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If Type(_argument_) is not Object, return *false*.""",
    """        1. If _argument_ has a [[Construct]] internal method, return *true*.""",
    """        1. Return *false*.""",
  )
}
