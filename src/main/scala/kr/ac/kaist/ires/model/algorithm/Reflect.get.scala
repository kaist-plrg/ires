package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Reflect.get` extends Algo {
  val head = BuiltinHead(parseRef("""Reflect.get"""), List(Param("target", Normal), Param("propertyKey", Normal), Param("receiver", Optional)))
  val ids = List(
    "sec-reflect.get",
    "sec-reflect-object",
    "sec-reflection",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= (typeof target) Object)) throw TypeError else 18:{}
  |  1:app __x0__ = (ToPropertyKey propertyKey)
  |  1:let key = [? __x0__]
  |  2:if (= receiver absent) receiver = target else 18:{}
  |  4:app __x1__ = (target.Get target key receiver)
  |  4:return [? __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If Type(_target_) is not Object, throw a *TypeError* exception.""",
    """        1. Let _key_ be ? ToPropertyKey(_propertyKey_).""",
    """        1. If _receiver_ is not present, then""",
    """          1. Set _receiver_ to _target_.""",
    """        1. Return ? _target_.[[Get]](_key_, _receiver_).""",
  )
}
