package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Reflect.apply` extends Algo {
  val head = BuiltinHead(parseRef("""Reflect.apply"""), List(Param("target", Normal), Param("thisArgument", Normal), Param("argumentsList", Normal)))
  val ids = List(
    "sec-reflect.apply",
    "sec-reflect-object",
    "sec-reflection",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsCallable target)
  |  0:if (= __x0__ false) throw TypeError else 18:{}
  |  1:app __x1__ = (CreateListFromArrayLike argumentsList)
  |  1:let args = [? __x1__]
  |  2:app __x2__ = (PrepareForTailCall)
  |  2:__x2__
  |  3:app __x3__ = (Call target thisArgument args)
  |  3:return [? __x3__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If IsCallable(_target_) is *false*, throw a *TypeError* exception.""",
    """        1. Let _args_ be ? CreateListFromArrayLike(_argumentsList_).""",
    """        1. Perform PrepareForTailCall().""",
    """        1. Return ? Call(_target_, _thisArgument_, _args_).""",
  )
}
