package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Function.prototype.call` extends Algo {
  val head = BuiltinHead(parseRef("""Function.prototype.call"""), List(Param("thisArg", Normal), Param("args", Variadic)))
  val ids = List(
    "sec-function.prototype.call",
    "sec-properties-of-the-function-prototype-object",
    "sec-function-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:let func = this
  |  1:app __x0__ = (IsCallable func)
  |  1:if (= __x0__ false) throw TypeError else 73:{}
  |  2:app __x1__ = (PrepareForTailCall)
  |  2:__x1__
  |  3:app __x2__ = (Call func thisArg args)
  |  3:return [? __x2__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _func_ be the *this* value.""",
    """          1. If IsCallable(_func_) is *false*, throw a *TypeError* exception.""",
    """          1. Perform PrepareForTailCall().""",
    """          1. [id="step-function-proto-call-call"] Return ? Call(_func_, _thisArg_, _args_).""",
  )
}
