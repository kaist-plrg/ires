package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Object.create` extends Algo {
  val head = BuiltinHead(parseRef("""Object.create"""), List(Param("O", Normal), Param("Properties", Normal)))
  val ids = List(
    "sec-object.create",
    "sec-properties-of-the-object-constructor",
    "sec-object-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:if (! (|| (= (typeof O) Object) (= (typeof O) Null))) throw TypeError else 71:{}
  |  1:app __x0__ = (OrdinaryObjectCreate O)
  |  1:let obj = [! __x0__]
  |  2:if (! (= Properties undefined)) {
  |    3:app __x1__ = (ObjectDefineProperties obj Properties)
  |    3:return [? __x1__]
  |  } else 71:{}
  |  4:return obj
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If Type(_O_) is neither Object nor Null, throw a *TypeError* exception.""",
    """          1. Let _obj_ be ! OrdinaryObjectCreate(_O_).""",
    """          1. If _Properties_ is not *undefined*, then""",
    """            1. Return ? ObjectDefineProperties(_obj_, _Properties_).""",
    """          1. Return _obj_.""",
  )
}
