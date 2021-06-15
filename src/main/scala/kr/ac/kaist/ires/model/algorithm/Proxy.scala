package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Proxy` extends Algo {
  val head = BuiltinHead(parseRef("""Proxy"""), List(Param("target", Normal), Param("handler", Normal)))
  val ids = List(
    "sec-proxy-target-handler",
    "sec-proxy-constructor",
    "sec-proxy-objects",
    "sec-reflection",
  )
  val rawBody = parseInst("""{
  |  0:if (= NewTarget undefined) throw TypeError else 18:{}
  |  1:app __x0__ = (ProxyCreate target handler)
  |  1:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If NewTarget is *undefined*, throw a *TypeError* exception.""",
    """          1. Return ? ProxyCreate(_target_, _handler_).""",
  )
}
