package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::RegExpExec` extends Algo {
  val head = NormalHead("RegExpExec", List(Param("R", Normal), Param("S", Normal)))
  val ids = List(
    "sec-regexpexec",
    "sec-regexp.prototype.exec",
    "sec-properties-of-the-regexp-prototype-object",
    "sec-regexp-regular-expression-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof R) Object)
  |  1:assert (= (typeof S) String)
  |  2:app __x0__ = (Get R "exec")
  |  2:let exec = [? __x0__]
  |  3:app __x1__ = (IsCallable exec)
  |  3:if (= __x1__ true) {
  |    4:app __x2__ = (Call exec R (new [S]))
  |    4:let result = [? __x2__]
  |    5:if (! (|| (= (typeof result) Object) (= (typeof result) Null))) throw TypeError else 14:{}
  |    6:return result
  |  } else 14:{}
  |  7:app __x3__ = (RequireInternalSlot R "RegExpMatcher")
  |  7:[? __x3__]
  |  8:app __x4__ = (RegExpBuiltinExec R S)
  |  8:return [? __x4__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: Type(_R_) is Object.""",
    """            1. Assert: Type(_S_) is String.""",
    """            1. Let _exec_ be ? Get(_R_, *"exec"*).""",
    """            1. If IsCallable(_exec_) is *true*, then""",
    """              1. Let _result_ be ? Call(_exec_, _R_, « _S_ »).""",
    """              1. If Type(_result_) is neither Object nor Null, throw a *TypeError* exception.""",
    """              1. Return _result_.""",
    """            1. Perform ? RequireInternalSlot(_R_, [[RegExpMatcher]]).""",
    """            1. Return ? RegExpBuiltinExec(_R_, _S_).""",
  )
}
