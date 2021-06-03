package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::RequireInternalSlot` extends Algo {
  val head = NormalHead("RequireInternalSlot", List(Param("O", Normal), Param("internalSlot", Normal)))
  val ids = List(
    "sec-requireinternalslot",
    "sec-ordinary-object-internal-methods-and-internal-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= (typeof O) Object)) throw TypeError else 0:{}
  |  1:if (= O[internalSlot] absent) throw TypeError else 0:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If Type(_O_) is not Object, throw a *TypeError* exception.""",
    """        1. If _O_ does not have an _internalSlot_ internal slot, throw a *TypeError* exception.""",
  )
}
