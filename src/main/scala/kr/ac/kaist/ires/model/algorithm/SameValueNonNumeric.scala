package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SameValueNonNumeric` extends Algo {
  val head = NormalHead("SameValueNonNumeric", List(Param("x", Normal), Param("y", Normal)))
  val ids = List(
    "sec-samevaluenonnumeric",
    "sec-testing-and-comparison-operations",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:assert (! (|| (= (typeof x) Number) (= (typeof x) BigInt)))
  |  1:assert (= (typeof x) (typeof y))
  |  2:if (= (typeof x) Undefined) return true else 0:{}
  |  3:if (= (typeof x) Null) return true else 0:{}
  |  4:if (= (typeof x) String) return (= x y) else 0:{}
  |  6:if (= (typeof x) Boolean) if (|| (&& (= x true) (= y true)) (&& (= x false) (= y false))) return true else return false else 0:{}
  |  8:if (= (typeof x) Symbol) if (= x y) return true else return false else 0:{}
  |  10:if (= x y) return true else return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: Type(_x_) is not Number or BigInt.""",
    """        1. Assert: Type(_x_) is the same as Type(_y_).""",
    """        1. If Type(_x_) is Undefined, return *true*.""",
    """        1. If Type(_x_) is Null, return *true*.""",
    """        1. If Type(_x_) is String, then""",
    """          1. If _x_ and _y_ are exactly the same sequence of code units (same length and same code units at corresponding indices), return *true*; otherwise, return *false*.""",
    """        1. If Type(_x_) is Boolean, then""",
    """          1. If _x_ and _y_ are both *true* or both *false*, return *true*; otherwise, return *false*.""",
    """        1. If Type(_x_) is Symbol, then""",
    """          1. If _x_ and _y_ are both the same Symbol value, return *true*; otherwise, return *false*.""",
    """        1. If _x_ and _y_ are the same Object value, return *true*. Otherwise, return *false*.""",
  )
}
