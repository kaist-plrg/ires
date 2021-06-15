package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IsConcatSpreadable` extends Algo {
  val head = NormalHead("IsConcatSpreadable", List(Param("O", Normal)))
  val ids = List(
    "sec-isconcatspreadable",
    "sec-array.prototype.concat",
    "sec-properties-of-the-array-prototype-object",
    "sec-array-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= (typeof O) Object)) return false else 19:{}
  |  1:app __x0__ = (Get O SYMBOL_isConcatSpreadable)
  |  1:let spreadable = [? __x0__]
  |  2:if (! (= spreadable undefined)) {
  |    app __x1__ = (ToBoolean spreadable)
  |    return [! __x1__]
  |  } else 19:{}
  |  3:app __x2__ = (IsArray O)
  |  3:return [? __x2__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. If Type(_O_) is not Object, return *false*.""",
    """            1. Let _spreadable_ be ? Get(_O_, @@isConcatSpreadable).""",
    """            1. If _spreadable_ is not *undefined*, return ! ToBoolean(_spreadable_).""",
    """            1. Return ? IsArray(_O_).""",
  )
}
