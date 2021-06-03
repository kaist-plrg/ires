package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Set.prototype.entries` extends Algo {
  val head = BuiltinHead(parseRef("""Set.prototype.entries"""), List())
  val ids = List(
    "sec-set.prototype.entries",
    "sec-properties-of-the-set-prototype-object",
    "sec-set-objects",
    "sec-keyed-collections",
  )
  val rawBody = parseInst("""{
  |  0:let S = this
  |  1:app __x0__ = (CreateSetIterator S CONST_keyPLUSvalue)
  |  1:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _S_ be the *this* value.""",
    """          1. Return ? CreateSetIterator(_S_, ~key+value~).""",
  )
}
