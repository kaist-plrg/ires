package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::eval` extends Algo {
  val head = BuiltinHead(parseRef("""eval"""), List(Param("x", Normal)))
  val ids = List(
    "sec-eval-x",
    "sec-function-properties-of-the-global-object",
    "sec-global-object",
  )
  val rawBody = parseInst("""{
  |  1:let callerContext = EXECUTION_STACK[(- EXECUTION_STACK.length 2i)]
  |  2:let callerRealm = callerContext.Realm
  |  3:app __x0__ = (PerformEval x callerRealm false false)
  |  3:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: The execution context stack has at least two elements.""",
    """        1. Let _callerContext_ be the second to top element of the execution context stack.""",
    """        1. Let _callerRealm_ be _callerContext_'s Realm.""",
    """        1. Return ? PerformEval(_x_, _callerRealm_, *false*, *false*).""",
  )
}
