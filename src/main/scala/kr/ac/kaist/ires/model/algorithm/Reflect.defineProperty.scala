package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Reflect.defineProperty` extends Algo {
  val head = BuiltinHead(parseRef("""Reflect.defineProperty"""), List(Param("target", Normal), Param("propertyKey", Normal), Param("attributes", Normal)))
  val ids = List(
    "sec-reflect.defineproperty",
    "sec-reflect-object",
    "sec-reflection",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= (typeof target) Object)) throw TypeError else 18:{}
  |  1:app __x0__ = (ToPropertyKey propertyKey)
  |  1:let key = [? __x0__]
  |  2:app __x1__ = (ToPropertyDescriptor attributes)
  |  2:let desc = [? __x1__]
  |  3:app __x2__ = (target.DefineOwnProperty target key desc)
  |  3:return [? __x2__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If Type(_target_) is not Object, throw a *TypeError* exception.""",
    """        1. Let _key_ be ? ToPropertyKey(_propertyKey_).""",
    """        1. Let _desc_ be ? ToPropertyDescriptor(_attributes_).""",
    """        1. Return ? _target_.[[DefineOwnProperty]](_key_, _desc_).""",
  )
}
