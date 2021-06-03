package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Reflect.construct` extends Algo {
  val head = BuiltinHead(parseRef("""Reflect.construct"""), List(Param("target", Normal), Param("argumentsList", Normal), Param("newTarget", Optional)))
  val ids = List(
    "sec-reflect.construct",
    "sec-reflect-object",
    "sec-reflection",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsConstructor target)
  |  0:if (= __x0__ false) throw TypeError else 18:{}
  |  2:if (= newTarget absent) newTarget = target else {
  |    app __x1__ = (IsConstructor newTarget)
  |    if (= __x1__ false) throw TypeError else 18:{}
  |  }
  |  3:app __x2__ = (CreateListFromArrayLike argumentsList)
  |  3:let args = [? __x2__]
  |  4:app __x3__ = (Construct target args newTarget)
  |  4:return [? __x3__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If IsConstructor(_target_) is *false*, throw a *TypeError* exception.""",
    """        1. If _newTarget_ is not present, set _newTarget_ to _target_.""",
    """        1. Else if IsConstructor(_newTarget_) is *false*, throw a *TypeError* exception.""",
    """        1. Let _args_ be ? CreateListFromArrayLike(_argumentsList_).""",
    """        1. Return ? Construct(_target_, _args_, _newTarget_).""",
  )
}
