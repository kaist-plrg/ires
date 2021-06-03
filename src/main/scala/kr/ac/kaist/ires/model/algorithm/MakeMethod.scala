package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::MakeMethod` extends Algo {
  val head = NormalHead("MakeMethod", List(Param("F", Normal), Param("homeObject", Normal)))
  val ids = List(
    "sec-makemethod",
    "sec-ecmascript-function-objects",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  1:assert (= (typeof homeObject) Object)
  |  2:F.HomeObject = homeObject
  |  3:return undefined
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: _F_ is an ECMAScript function object.""",
    """        1. Assert: Type(_homeObject_) is Object.""",
    """        1. Set _F_.[[HomeObject]] to _homeObject_.""",
    """        1. Return NormalCompletion(*undefined*).""",
  )
}
