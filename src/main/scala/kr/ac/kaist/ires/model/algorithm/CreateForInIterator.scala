package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CreateForInIterator` extends Algo {
  val head = NormalHead("CreateForInIterator", List(Param("object", Normal)))
  val ids = List(
    "sec-createforiniterator",
    "sec-for-in-iterator-objects",
    "sec-for-in-and-for-of-statements",
    "sec-iteration-statements",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof object) Object)
  |  1:app __x0__ = (OrdinaryObjectCreate INTRINSIC_ForInIteratorPrototype (new ["Object", "ObjectWasVisited", "VisitedKeys", "RemainingKeys"]))
  |  1:let iterator = [! __x0__]
  |  2:iterator.Object = object
  |  3:iterator.ObjectWasVisited = false
  |  4:iterator.VisitedKeys = (new [])
  |  5:iterator.RemainingKeys = (new [])
  |  6:return iterator
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: Type(_object_) is Object.""",
    """            1. Let _iterator_ be ! OrdinaryObjectCreate(%ForInIteratorPrototype%, « [[Object]], [[ObjectWasVisited]], [[VisitedKeys]], [[RemainingKeys]] »).""",
    """            1. Set _iterator_.[[Object]] to _object_.""",
    """            1. Set _iterator_.[[ObjectWasVisited]] to *false*.""",
    """            1. Set _iterator_.[[VisitedKeys]] to a new empty List.""",
    """            1. Set _iterator_.[[RemainingKeys]] to a new empty List.""",
    """            1. Return _iterator_.""",
  )
}
