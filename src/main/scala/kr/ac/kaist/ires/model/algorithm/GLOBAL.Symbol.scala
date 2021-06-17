package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Symbol` extends Algo {
  val head = BuiltinHead(parseRef("""Symbol"""), List(Param("description", Optional)))
  val ids = List(
    "sec-symbol-description",
    "sec-symbol-constructor",
    "sec-symbol-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= NewTarget undefined)) throw TypeError else 73:{}
  |  2:if (= description undefined) let descString = undefined else {
  |    app __x0__ = (ToString description)
  |    let descString = [? __x0__]
  |  }
  |  3:??? "Return a new unique Symbol value whose [ [ Description ] ] value is id:{descString} ."
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If NewTarget is not *undefined*, throw a *TypeError* exception.""",
    """          1. If _description_ is *undefined*, let _descString_ be *undefined*.""",
    """          1. Else, let _descString_ be ? ToString(_description_).""",
    """          1. Return a new unique Symbol value whose [[Description]] value is _descString_.""",
  )
}
