package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Reflect.has` extends Algo {
  val head = BuiltinHead(parseRef("""Reflect.has"""), List(Param("target", Normal), Param("propertyKey", Normal)))
  val ids = List(
    "sec-reflect.has",
    "sec-reflect-object",
    "sec-reflection",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= (typeof target) Object)) throw TypeError else 18:{}
  |  1:app __x0__ = (ToPropertyKey propertyKey)
  |  1:let key = [? __x0__]
  |  2:app __x1__ = (target.HasProperty target key)
  |  2:return [? __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If Type(_target_) is not Object, throw a *TypeError* exception.""",
    """        1. Let _key_ be ? ToPropertyKey(_propertyKey_).""",
    """        1. Return ? _target_.[[HasProperty]](_key_).""",
  )
}
