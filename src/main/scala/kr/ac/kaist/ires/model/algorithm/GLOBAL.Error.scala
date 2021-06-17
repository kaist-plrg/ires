package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Error` extends Algo {
  val head = BuiltinHead(parseRef("""Error"""), List(Param("message", Normal)))
  val ids = List(
    "sec-error-message",
    "sec-error-constructor",
    "sec-error-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:if (= NewTarget undefined) let newTarget = CONTEXT.Function else let newTarget = NewTarget
  |  1:app __x0__ = (OrdinaryCreateFromConstructor newTarget "%Error.prototype%" (new ["ErrorData"]))
  |  1:let O = [? __x0__]
  |  2:if (! (= message undefined)) {
  |    3:app __x1__ = (ToString message)
  |    3:let msg = [? __x1__]
  |    4:let msgDesc = (new PropertyDescriptor("Value" -> msg, "Writable" -> true, "Enumerable" -> false, "Configurable" -> true))
  |    5:app __x2__ = (DefinePropertyOrThrow O "message" msgDesc)
  |    5:[! __x2__]
  |  } else 3:{}
  |  6:return O
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If NewTarget is *undefined*, let _newTarget_ be the active function object; else let _newTarget_ be NewTarget.""",
    """          1. Let _O_ be ? OrdinaryCreateFromConstructor(_newTarget_, *"%Error.prototype%"*, « [[ErrorData]] »).""",
    """          1. If _message_ is not *undefined*, then""",
    """            1. Let _msg_ be ? ToString(_message_).""",
    """            1. Let _msgDesc_ be the PropertyDescriptor { [[Value]]: _msg_, [[Writable]]: *true*, [[Enumerable]]: *false*, [[Configurable]]: *true* }.""",
    """            1. Perform ! DefinePropertyOrThrow(_O_, *"message"*, _msgDesc_).""",
    """          1. Return _O_.""",
  )
}
