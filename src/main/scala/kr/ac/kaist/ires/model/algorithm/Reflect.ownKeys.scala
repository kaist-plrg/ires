package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Reflect.ownKeys` extends Algo {
  val head = BuiltinHead(parseRef("""Reflect.ownKeys"""), List(Param("target", Normal)))
  val ids = List(
    "sec-reflect.ownkeys",
    "sec-reflect-object",
    "sec-reflection",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= (typeof target) Object)) throw TypeError else 18:{}
  |  1:app __x0__ = (target.OwnPropertyKeys target)
  |  1:let keys = [? __x0__]
  |  2:app __x1__ = (CreateArrayFromList keys)
  |  2:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If Type(_target_) is not Object, throw a *TypeError* exception.""",
    """        1. Let _keys_ be ? _target_.[[OwnPropertyKeys]]().""",
    """        1. Return CreateArrayFromList(_keys_).""",
  )
}
