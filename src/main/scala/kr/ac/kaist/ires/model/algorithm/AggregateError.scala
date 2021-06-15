package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AggregateError` extends Algo {
  val head = BuiltinHead(parseRef("""AggregateError"""), List(Param("errors", Normal), Param("message", Normal)))
  val ids = List(
    "sec-aggregate-error",
    "sec-aggregate-error-constructor",
    "sec-aggregate-error-objects",
    "sec-error-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:if (= NewTarget undefined) let newTarget = CONTEXT.Function else let newTarget = NewTarget
  |  1:app __x0__ = (OrdinaryCreateFromConstructor newTarget "%AggregateError.prototype%" (new ["ErrorData"]))
  |  1:let O = [? __x0__]
  |  2:if (! (= message undefined)) {
  |    3:app __x1__ = (ToString message)
  |    3:let msg = [? __x1__]
  |    4:let msgDesc = (new PropertyDescriptor("Value" -> msg, "Writable" -> true, "Enumerable" -> false, "Configurable" -> true))
  |    5:app __x2__ = (DefinePropertyOrThrow O "message" msgDesc)
  |    5:[! __x2__]
  |  } else 3:{}
  |  6:app __x3__ = (IterableToList errors)
  |  6:let errorsList = [? __x3__]
  |  7:app __x4__ = (CreateArrayFromList errorsList)
  |  7:app __x5__ = (DefinePropertyOrThrow O "errors" (new PropertyDescriptor("Configurable" -> true, "Enumerable" -> false, "Writable" -> true, "Value" -> [! __x4__])))
  |  7:[! __x5__]
  |  8:return O
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. If NewTarget is *undefined*, let _newTarget_ be the active function object; else let _newTarget_ be NewTarget.""",
    """            1. Let _O_ be ? OrdinaryCreateFromConstructor(_newTarget_, *"%AggregateError.prototype%"*, « [[ErrorData]] »).""",
    """            1. If _message_ is not *undefined*, then""",
    """              1. Let _msg_ be ? ToString(_message_).""",
    """              1. Let _msgDesc_ be the PropertyDescriptor { [[Value]]: _msg_, [[Writable]]: *true*, [[Enumerable]]: *false*, [[Configurable]]: *true* }.""",
    """              1. Perform ! DefinePropertyOrThrow(_O_, *"message"*, _msgDesc_).""",
    """            1. Let _errorsList_ be ? IterableToList(_errors_).""",
    """            1. Perform ! DefinePropertyOrThrow(_O_, *"errors"*, PropertyDescriptor { [[Configurable]]: *true*, [[Enumerable]]: *false*, [[Writable]]: *true*, [[Value]]: ! CreateArrayFromList(_errorsList_) }).""",
    """            1. Return _O_.""",
  )
}
