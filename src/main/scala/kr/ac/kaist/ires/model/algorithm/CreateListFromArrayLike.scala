package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CreateListFromArrayLike` extends Algo {
  val head = NormalHead("CreateListFromArrayLike", List(Param("obj", Normal), Param("elementTypes", Optional)))
  val ids = List(
    "sec-createlistfromarraylike",
    "sec-operations-on-objects",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:if (= elementTypes absent) elementTypes = (new [Undefined, Null, Boolean, String, Symbol, Number, BigInt, Object]) else 0:{}
  |  1:if (! (= (typeof obj) Object)) throw TypeError else 0:{}
  |  2:app __x0__ = (LengthOfArrayLike obj)
  |  2:let len = [? __x0__]
  |  3:let list = (new [])
  |  4:let index = 0i
  |  5:while (< index len) {
  |    6:app __x1__ = (ToString index)
  |    6:let indexName = [! __x1__]
  |    7:app __x2__ = (Get obj indexName)
  |    7:let next = [? __x2__]
  |    8:if (! (contains elementTypes (typeof next))) throw TypeError else 0:{}
  |    9:append next -> list
  |    10:index = (+ index 1i)
  |  }
  |  11:return list
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If _elementTypes_ is not present, set _elementTypes_ to « Undefined, Null, Boolean, String, Symbol, Number, BigInt, Object ».""",
    """        1. If Type(_obj_) is not Object, throw a *TypeError* exception.""",
    """        1. Let _len_ be ? LengthOfArrayLike(_obj_).""",
    """        1. Let _list_ be a new empty List.""",
    """        1. Let _index_ be 0.""",
    """        1. Repeat, while _index_ < _len_,""",
    """          1. Let _indexName_ be ! ToString(𝔽(_index_)).""",
    """          1. Let _next_ be ? Get(_obj_, _indexName_).""",
    """          1. If Type(_next_) is not an element of _elementTypes_, throw a *TypeError* exception.""",
    """          1. Append _next_ as the last element of _list_.""",
    """          1. Set _index_ to _index_ + 1.""",
    """        1. Return _list_.""",
  )
}
