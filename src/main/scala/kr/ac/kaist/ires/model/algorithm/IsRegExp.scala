package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IsRegExp` extends Algo {
  val head = NormalHead("IsRegExp", List(Param("argument", Normal)))
  val ids = List(
    "sec-isregexp",
    "sec-testing-and-comparison-operations",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= (typeof argument) Object)) return false else 0:{}
  |  1:app __x0__ = (Get argument SYMBOL_match)
  |  1:let matcher = [? __x0__]
  |  2:if (! (= matcher undefined)) {
  |    app __x1__ = (ToBoolean matcher)
  |    return [! __x1__]
  |  } else 0:{}
  |  3:if (! (= argument.RegExpMatcher absent)) return true else 0:{}
  |  4:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If Type(_argument_) is not Object, return *false*.""",
    """        1. Let _matcher_ be ? Get(_argument_, @@match).""",
    """        1. If _matcher_ is not *undefined*, return ! ToBoolean(_matcher_).""",
    """        1. If _argument_ has a [[RegExpMatcher]] internal slot, return *true*.""",
    """        1. Return *false*.""",
  )
}
