package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Promise.prototype.catch` extends Algo {
  val head = BuiltinHead(parseRef("""Promise.prototype.catch"""), List(Param("onRejected", Normal)))
  val ids = List(
    "sec-promise.prototype.catch",
    "sec-properties-of-the-promise-prototype-object",
    "sec-promise-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:let promise = this
  |  1:app __x0__ = (Invoke promise "then" (new [undefined, onRejected]))
  |  1:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _promise_ be the *this* value.""",
    """          1. Return ? Invoke(_promise_, *"then"*, « *undefined*, _onRejected_ »).""",
  )
}
