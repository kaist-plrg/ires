package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IteratorNext` extends Algo {
  val head = NormalHead("IteratorNext", List(Param("iteratorRecord", Normal), Param("value", Optional)))
  val ids = List(
    "sec-iteratornext",
    "sec-operations-on-iterator-objects",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  2:if (= value absent) {
  |    1:app __x0__ = (Call iteratorRecord.NextMethod iteratorRecord.Iterator)
  |    1:let result = [? __x0__]
  |  } else {
  |    3:app __x1__ = (Call iteratorRecord.NextMethod iteratorRecord.Iterator (new [value]))
  |    3:let result = [? __x1__]
  |  }
  |  4:if (! (= (typeof result) Object)) throw TypeError else 1:{}
  |  5:return result
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If _value_ is not present, then""",
    """          1. Let _result_ be ? Call(_iteratorRecord_.[[NextMethod]], _iteratorRecord_.[[Iterator]]).""",
    """        1. Else,""",
    """          1. Let _result_ be ? Call(_iteratorRecord_.[[NextMethod]], _iteratorRecord_.[[Iterator]], « _value_ »).""",
    """        1. If Type(_result_) is not Object, throw a *TypeError* exception.""",
    """        1. Return _result_.""",
  )
}
