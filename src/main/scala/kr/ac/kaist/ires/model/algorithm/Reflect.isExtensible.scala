package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Reflect.isExtensible` extends Algo {
  val head = BuiltinHead(parseRef("""Reflect.isExtensible"""), List(Param("target", Normal)))
  val ids = List(
    "sec-reflect.isextensible",
    "sec-reflect-object",
    "sec-reflection",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= (typeof target) Object)) throw TypeError else 18:{}
  |  1:app __x0__ = (target.IsExtensible target)
  |  1:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If Type(_target_) is not Object, throw a *TypeError* exception.""",
    """        1. Return ? _target_.[[IsExtensible]]().""",
  )
}
