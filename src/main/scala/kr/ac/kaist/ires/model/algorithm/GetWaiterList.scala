package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GetWaiterList` extends Algo {
  val head = NormalHead("GetWaiterList", List(Param("block", Normal), Param("i", Normal)))
  val ids = List(
    "sec-getwaiterlist",
    "sec-abstract-operations-for-atomics",
    "sec-atomics-object",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:assert (is-instance-of block SharedDataBlock)
  |  3:??? "Return the WaiterList that is referenced by the pair ( id:{block} , id:{i} ) ."
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: _block_ is a Shared Data Block.""",
    """          1. Assert: _i_ and _i_ + 3 are valid byte offsets within the memory of _block_.""",
    """          1. Assert: _i_ is divisible by 4.""",
    """          1. Return the WaiterList that is referenced by the pair (_block_, _i_).""",
  )
}
