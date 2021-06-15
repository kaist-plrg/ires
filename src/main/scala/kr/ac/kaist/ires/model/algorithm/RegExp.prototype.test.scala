package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::RegExp.prototype.test` extends Algo {
  val head = BuiltinHead(parseRef("""RegExp.prototype.test"""), List(Param("S", Normal)))
  val ids = List(
    "sec-regexp.prototype.test",
    "sec-properties-of-the-regexp-prototype-object",
    "sec-regexp-regular-expression-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:let R = this
  |  1:if (! (= (typeof R) Object)) throw TypeError else 56:{}
  |  2:app __x0__ = (ToString S)
  |  2:let string = [? __x0__]
  |  3:app __x1__ = (RegExpExec R string)
  |  3:let match = [? __x1__]
  |  4:if (! (= match null)) return true else return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _R_ be the *this* value.""",
    """          1. If Type(_R_) is not Object, throw a *TypeError* exception.""",
    """          1. Let _string_ be ? ToString(_S_).""",
    """          1. Let _match_ be ? RegExpExec(_R_, _string_).""",
    """          1. If _match_ is not *null*, return *true*; else return *false*.""",
  )
}
