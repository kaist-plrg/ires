package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Object.keys` extends Algo {
  val head = BuiltinHead(parseRef("""Object.keys"""), List(Param("O", Normal)))
  val ids = List(
    "sec-object.keys",
    "sec-properties-of-the-object-constructor",
    "sec-object-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToObject O)
  |  0:let obj = [? __x0__]
  |  1:app __x1__ = (EnumerableOwnPropertyNames obj CONST_key)
  |  1:let nameList = [? __x1__]
  |  2:app __x2__ = (CreateArrayFromList nameList)
  |  2:return __x2__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _obj_ be ? ToObject(_O_).""",
    """          1. Let _nameList_ be ? EnumerableOwnPropertyNames(_obj_, ~key~).""",
    """          1. Return CreateArrayFromList(_nameList_).""",
  )
}
