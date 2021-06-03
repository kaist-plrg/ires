package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::LoopContinues` extends Algo {
  val head = NormalHead("LoopContinues", List(Param("completion", Normal), Param("labelSet", Normal)))
  val ids = List(
    "sec-loopcontinues",
    "sec-iteration-statements-semantics",
    "sec-iteration-statements",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:if (= completion.Type CONST_normal) return true else 10:{}
  |  1:if (! (= completion.Type CONST_continue)) return false else 10:{}
  |  2:if (= completion.Target CONST_empty) return true else 10:{}
  |  3:if (contains labelSet completion.Target) return true else 10:{}
  |  4:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If _completion_.[[Type]] is ~normal~, return *true*.""",
    """          1. If _completion_.[[Type]] is not ~continue~, return *false*.""",
    """          1. If _completion_.[[Target]] is ~empty~, return *true*.""",
    """          1. If _completion_.[[Target]] is an element of _labelSet_, return *true*.""",
    """          1. Return *false*.""",
  )
}
