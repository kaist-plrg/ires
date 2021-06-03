package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Reflect.setPrototypeOf` extends Algo {
  val head = BuiltinHead(parseRef("""Reflect.setPrototypeOf"""), List(Param("target", Normal), Param("proto", Normal)))
  val ids = List(
    "sec-reflect.setprototypeof",
    "sec-reflect-object",
    "sec-reflection",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= (typeof target) Object)) throw TypeError else 18:{}
  |  1:if (&& (! (= (typeof proto) Object)) (! (= proto null))) throw TypeError else 18:{}
  |  2:app __x0__ = (target.SetPrototypeOf target proto)
  |  2:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If Type(_target_) is not Object, throw a *TypeError* exception.""",
    """        1. If Type(_proto_) is not Object and _proto_ is not *null*, throw a *TypeError* exception.""",
    """        1. Return ? _target_.[[SetPrototypeOf]](_proto_).""",
  )
}
