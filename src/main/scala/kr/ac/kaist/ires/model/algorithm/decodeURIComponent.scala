package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::decodeURIComponent` extends Algo {
  val head = BuiltinHead(parseRef("""decodeURIComponent"""), List(Param("encodedURIComponent", Normal)))
  val ids = List(
    "sec-decodeuricomponent-encodeduricomponent",
    "sec-uri-handling-functions",
    "sec-function-properties-of-the-global-object",
    "sec-global-object",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToString encodedURIComponent)
  |  0:let componentString = [? __x0__]
  |  1:let reservedURIComponentSet = ""
  |  2:app __x1__ = (Decode componentString reservedURIComponentSet)
  |  2:return [? __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _componentString_ be ? ToString(_encodedURIComponent_).""",
    """          1. Let _reservedURIComponentSet_ be the empty String.""",
    """          1. Return ? Decode(_componentString_, _reservedURIComponentSet_).""",
  )
}
