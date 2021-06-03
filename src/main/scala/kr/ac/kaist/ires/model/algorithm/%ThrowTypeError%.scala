package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::%ThrowTypeError%` extends Algo {
  val head = NormalHead("%ThrowTypeError%", List())
  val ids = List(
    "sec-%throwtypeerror%",
    "sec-addrestrictedfunctionproperties",
    "sec-ecmascript-function-objects",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""throw TypeError""".stripMargin)
  val code = scala.Array[String](
    """          1. Throw a *TypeError* exception.""",
  )
}
