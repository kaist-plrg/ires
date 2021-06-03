package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CreateByteDataBlock` extends Algo {
  val head = NormalHead("CreateByteDataBlock", List(Param("size", Normal)))
  val ids = List(
    "sec-createbytedatablock",
    "sec-data-blocks",
    "sec-ecmascript-specification-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:assert (! (< size 0i))
  |  1:??? "Let id:{db} be a new Data Block value consisting of id:{size} bytes . If it is impossible to create such a Data Block , throw a value:{RangeError} exception ."
  |  2:??? "Set all of the bytes of id:{db} to 0 ."
  |  3:return db
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: _size_ ≥ 0.""",
    """          1. Let _db_ be a new Data Block value consisting of _size_ bytes. If it is impossible to create such a Data Block, throw a *RangeError* exception.""",
    """          1. Set all of the bytes of _db_ to 0.""",
    """          1. Return _db_.""",
  )
}
