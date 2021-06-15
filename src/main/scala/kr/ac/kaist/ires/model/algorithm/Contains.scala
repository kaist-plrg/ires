package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Contains` extends Algo {
  val head = NormalHead("Contains", List())
  val ids = List(
    "sec-static-semantics-contains",
    "sec-syntax-directed-operations-contains",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:??? "For each child node id:{child} of this Parse Node , do in:{} out:{}"
  |  5:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. For each child node _child_ of this Parse Node, do""",
    """          1. If _child_ is an instance of _symbol_, return *true*.""",
    """          1. If _child_ is an instance of a nonterminal, then""",
    """            1. Let _contained_ be the result of _child_ Contains _symbol_.""",
    """            1. If _contained_ is *true*, return *true*.""",
    """        1. Return *false*.""",
  )
}
