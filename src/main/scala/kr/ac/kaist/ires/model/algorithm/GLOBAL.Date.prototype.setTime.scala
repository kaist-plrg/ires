package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Date.prototype.setTime` extends Algo {
  val head = BuiltinHead(parseRef("""Date.prototype.setTime"""), List(Param("time", Normal)))
  val ids = List(
    "sec-date.prototype.settime",
    "sec-properties-of-the-date-prototype-object",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (thisTimeValue this)
  |  0:[? __x0__]
  |  1:app __x1__ = (ToNumber time)
  |  1:let t = [? __x1__]
  |  2:app __x2__ = (TimeClip t)
  |  2:let v = __x2__
  |  3:this.DateValue = v
  |  4:return v
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Perform ? thisTimeValue(*this* value).""",
    """          1. Let _t_ be ? ToNumber(_time_).""",
    """          1. Let _v_ be TimeClip(_t_).""",
    """          1. Set the [[DateValue]] internal slot of this Date object to _v_.""",
    """          1. Return _v_.""",
  )
}
