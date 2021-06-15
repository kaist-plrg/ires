package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::TheAbstractClosureSpecificationType` extends Algo {
  val head = NormalHead("TheAbstractClosureSpecificationType", List())
  val ids = List(
    "sec-abstract-closure",
    "sec-ecmascript-specification-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:let addend = 41i
  |  1:??? "Let id:{closure} be a new Abstract Closure with parameters ( id:{x} ) that captures id:{addend} and performs the following steps when called : in:{} out:{}"
  |  3:app __x0__ = (closure 1i)
  |  3:let val = __x0__
  |  4:assert (= val 42i)
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _addend_ be 41.""",
    """        1. Let _closure_ be a new Abstract Closure with parameters (_x_) that captures _addend_ and performs the following steps when called:""",
    """          1. Return _x_ + _addend_.""",
    """        1. Let _val_ be _closure_(1).""",
    """        1. Assert: _val_ is 42.""",
  )
}
