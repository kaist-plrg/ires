package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.RegExp.prototype.exec` extends Algo {
  val head = BuiltinHead(parseRef("""RegExp.prototype.exec"""), List(Param("string", Normal)))
  val ids = List(
    "sec-regexp.prototype.exec",
    "sec-properties-of-the-regexp-prototype-object",
    "sec-regexp-regular-expression-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:let R = this
  |  1:app __x0__ = (RequireInternalSlot R "RegExpMatcher")
  |  1:[? __x0__]
  |  2:app __x1__ = (ToString string)
  |  2:let S = [? __x1__]
  |  3:app __x2__ = (RegExpBuiltinExec R S)
  |  3:return [? __x2__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _R_ be the *this* value.""",
    """          1. Perform ? RequireInternalSlot(_R_, [[RegExpMatcher]]).""",
    """          1. Let _S_ be ? ToString(_string_).""",
    """          1. Return ? RegExpBuiltinExec(_R_, _S_).""",
  )
}
