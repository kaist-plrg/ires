package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::PrepareForTailCall` extends Algo {
  val head = NormalHead("PrepareForTailCall", List())
  val ids = List(
    "sec-preparefortailcall",
    "sec-tail-position-calls",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:let leafContext = CONTEXT
  |  1:CONTEXT = null
  |  2:??? "Pop id:{leafContext} from the execution context stack . The execution context now on the top of the stack becomes the running execution context ."
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _leafContext_ be the running execution context.""",
    """        1. Suspend _leafContext_.""",
    """        1. Pop _leafContext_ from the execution context stack. The execution context now on the top of the stack becomes the running execution context.""",
    """        1. Assert: _leafContext_ has no further use. It will never be activated as the running execution context.""",
  )
}
