package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ValidChosenReads` extends Algo {
  val head = NormalHead("ValidChosenReads", List())
  val ids = List(
    "sec-valid-chosen-reads",
    "sec-properties-of-valid-executions",
    "sec-memory-model",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (SharedDataBlockEventSet execution)
  |  0:let __x1__ = __x0__
  |  0:let __x2__ = 0i
  |  0:while (< __x2__ __x1__.length) {
  |    let R = __x1__[__x2__]
  |    1:??? "Let id:{chosenValueRecord} be the element of id:{execution} . [ [ ChosenValues ] ] whose [ [ Event ] ] field is id:{R} ."
  |    2:let chosenValue = chosenValueRecord.ChosenValue
  |    3:app __x3__ = (ValueOfReadEvent execution R)
  |    3:let readValue = __x3__
  |    4:let chosenLen = chosenValue.length
  |    5:let readLen = readValue.length
  |    6:if (! (== chosenLen readLen)) return false else 2:{}
  |    8:??? "If id:{chosenValue} [ id:{i} ] ≠ id:{readValue} [ id:{i} ] for any integer value id:{i} in the range 0 through id:{chosenLen} , exclusive , then in:{} out:{}"
  |    __x2__ = (+ __x2__ 1i)
  |  }
  |  10:return true
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. For each ReadSharedMemory or ReadModifyWriteSharedMemory event _R_ of SharedDataBlockEventSet(_execution_), do""",
    """          1. Let _chosenValueRecord_ be the element of _execution_.[[ChosenValues]] whose [[Event]] field is _R_.""",
    """          1. Let _chosenValue_ be _chosenValueRecord_.[[ChosenValue]].""",
    """          1. Let _readValue_ be ValueOfReadEvent(_execution_, _R_).""",
    """          1. Let _chosenLen_ be the number of elements of _chosenValue_.""",
    """          1. Let _readLen_ be the number of elements of _readValue_.""",
    """          1. If _chosenLen_ ≠ _readLen_, then""",
    """            1. Return *false*.""",
    """          1. If _chosenValue_[_i_] ≠ _readValue_[_i_] for any integer value _i_ in the range 0 through _chosenLen_, exclusive, then""",
    """            1. Return *false*.""",
    """        1. Return *true*.""",
  )
}
