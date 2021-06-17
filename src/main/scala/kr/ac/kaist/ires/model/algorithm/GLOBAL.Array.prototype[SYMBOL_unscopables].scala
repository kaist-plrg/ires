package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Array.prototype[SYMBOL_unscopables]` extends Algo {
  val head = BuiltinHead(parseRef("""Array.prototype[SYMBOL_unscopables]"""), List())
  val ids = List(
    "sec-array.prototype-@@unscopables",
    "sec-properties-of-the-array-prototype-object",
    "sec-array-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (OrdinaryObjectCreate null)
  |  0:let unscopableList = [! __x0__]
  |  1:app __x1__ = (CreateDataPropertyOrThrow unscopableList "copyWithin" true)
  |  1:[! __x1__]
  |  2:app __x2__ = (CreateDataPropertyOrThrow unscopableList "entries" true)
  |  2:[! __x2__]
  |  3:app __x3__ = (CreateDataPropertyOrThrow unscopableList "fill" true)
  |  3:[! __x3__]
  |  4:app __x4__ = (CreateDataPropertyOrThrow unscopableList "find" true)
  |  4:[! __x4__]
  |  5:app __x5__ = (CreateDataPropertyOrThrow unscopableList "findIndex" true)
  |  5:[! __x5__]
  |  6:app __x6__ = (CreateDataPropertyOrThrow unscopableList "flat" true)
  |  6:[! __x6__]
  |  7:app __x7__ = (CreateDataPropertyOrThrow unscopableList "flatMap" true)
  |  7:[! __x7__]
  |  8:app __x8__ = (CreateDataPropertyOrThrow unscopableList "includes" true)
  |  8:[! __x8__]
  |  9:app __x9__ = (CreateDataPropertyOrThrow unscopableList "keys" true)
  |  9:[! __x9__]
  |  10:app __x10__ = (CreateDataPropertyOrThrow unscopableList "values" true)
  |  10:[! __x10__]
  |  11:return unscopableList
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _unscopableList_ be ! OrdinaryObjectCreate(*null*).""",
    """          1. Perform ! CreateDataPropertyOrThrow(_unscopableList_, *"copyWithin"*, *true*).""",
    """          1. Perform ! CreateDataPropertyOrThrow(_unscopableList_, *"entries"*, *true*).""",
    """          1. Perform ! CreateDataPropertyOrThrow(_unscopableList_, *"fill"*, *true*).""",
    """          1. Perform ! CreateDataPropertyOrThrow(_unscopableList_, *"find"*, *true*).""",
    """          1. Perform ! CreateDataPropertyOrThrow(_unscopableList_, *"findIndex"*, *true*).""",
    """          1. Perform ! CreateDataPropertyOrThrow(_unscopableList_, *"flat"*, *true*).""",
    """          1. Perform ! CreateDataPropertyOrThrow(_unscopableList_, *"flatMap"*, *true*).""",
    """          1. Perform ! CreateDataPropertyOrThrow(_unscopableList_, *"includes"*, *true*).""",
    """          1. Perform ! CreateDataPropertyOrThrow(_unscopableList_, *"keys"*, *true*).""",
    """          1. Perform ! CreateDataPropertyOrThrow(_unscopableList_, *"values"*, *true*).""",
    """          1. Return _unscopableList_.""",
  )
}
