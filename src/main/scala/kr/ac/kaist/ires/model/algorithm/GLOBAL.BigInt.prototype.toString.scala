package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.BigInt.prototype.toString` extends Algo {
  val head = BuiltinHead(parseRef("""BigInt.prototype.toString"""), List(Param("radix", Optional)))
  val ids = List(
    "sec-bigint.prototype.tostring",
    "sec-properties-of-the-bigint-prototype-object",
    "sec-bigint-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (thisBigIntValue this)
  |  0:let x = [? __x0__]
  |  2:if (= radix undefined) let radixMV = 10i else {
  |    app __x1__ = (ToIntegerOrInfinity radix)
  |    let radixMV = [? __x1__]
  |  }
  |  3:if (|| (< radixMV 2i) (< 36i radixMV)) throw RangeError else 2:{}
  |  4:if (== radixMV 10i) {
  |    app __x2__ = (ToString x)
  |    return [! __x2__]
  |  } else 2:{}
  |  5:??? "Return the String representation of this Number value using the radix specified by id:{radixMV} . Letters code:{a} - code:{z} are used for digits with values 10 through 35 . The precise algorithm is implementation - defined , however the algorithm should be a generalization of that specified in link:{sec-numeric-types-bigint-tostring} ."
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _x_ be ? thisBigIntValue(*this* value).""",
    """          1. If _radix_ is *undefined*, let _radixMV_ be 10.""",
    """          1. Else, let _radixMV_ be ? ToIntegerOrInfinity(_radix_).""",
    """          1. If _radixMV_ < 2 or _radixMV_ > 36, throw a *RangeError* exception.""",
    """          1. If _radixMV_ = 10, return ! ToString(_x_).""",
    """          1. Return the String representation of this Number value using the radix specified by _radixMV_. Letters `a`-`z` are used for digits with values 10 through 35. The precise algorithm is implementation-defined, however the algorithm should be a generalization of that specified in <emu-xref href="#sec-numeric-types-bigint-tostring"></emu-xref>.""",
  )
}
