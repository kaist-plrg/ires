package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::YearFromTime` extends Algo {
  val head = NormalHead("YearFromTime", List(Param("t", Normal)))
  val ids = List(
    "eqn-YearFromTime",
    "sec-year-number",
    "sec-overview-of-date-objects-and-definitions-of-abstract-operations",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""??? "YearFromTime ( id:{t} ) = the largest integral Number id:{y} ( closest to + ∞ ) such that TimeFromYear ( id:{y} ) ≤ id:{t}"""".stripMargin)
  val code = scala.Array[String](
    """YearFromTime(_t_) = the largest integral Number _y_ (closest to +∞) such that TimeFromYear(_y_) ≤ _t_""",
  )
}
