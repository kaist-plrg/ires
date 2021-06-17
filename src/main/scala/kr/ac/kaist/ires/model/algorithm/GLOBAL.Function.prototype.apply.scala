package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Function.prototype.apply` extends Algo {
  val head = BuiltinHead(parseRef("""Function.prototype.apply"""), List(Param("thisArg", Normal), Param("argArray", Normal)))
  val ids = List(
    "sec-function.prototype.apply",
    "sec-properties-of-the-function-prototype-object",
    "sec-function-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:let func = this
  |  1:app __x0__ = (IsCallable func)
  |  1:if (= __x0__ false) throw TypeError else 73:{}
  |  2:if (|| (= argArray undefined) (= argArray null)) {
  |    3:app __x1__ = (PrepareForTailCall)
  |    3:__x1__
  |    4:app __x2__ = (Call func thisArg)
  |    4:return [? __x2__]
  |  } else 73:{}
  |  5:app __x3__ = (CreateListFromArrayLike argArray)
  |  5:let argList = [? __x3__]
  |  6:app __x4__ = (PrepareForTailCall)
  |  6:__x4__
  |  7:app __x5__ = (Call func thisArg argList)
  |  7:return [? __x5__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _func_ be the *this* value.""",
    """          1. If IsCallable(_func_) is *false*, throw a *TypeError* exception.""",
    """          1. If _argArray_ is *undefined* or *null*, then""",
    """            1. Perform PrepareForTailCall().""",
    """            1. Return ? Call(_func_, _thisArg_).""",
    """          1. Let _argList_ be ? CreateListFromArrayLike(_argArray_).""",
    """          1. Perform PrepareForTailCall().""",
    """          1. [id="step-function-proto-apply-call"] Return ? Call(_func_, _thisArg_, _argList_).""",
  )
}
