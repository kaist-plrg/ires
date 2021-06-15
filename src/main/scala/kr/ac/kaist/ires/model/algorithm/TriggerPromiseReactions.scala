package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::TriggerPromiseReactions` extends Algo {
  val head = NormalHead("TriggerPromiseReactions", List(Param("reactions", Normal), Param("argument", Normal)))
  val ids = List(
    "sec-triggerpromisereactions",
    "sec-promise-abstract-operations",
    "sec-promise-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:let __x0__ = reactions
  |  0:let __x1__ = 0i
  |  0:while (< __x1__ __x0__.length) {
  |    let reaction = __x0__[__x1__]
  |    1:app __x2__ = (NewPromiseReactionJob reaction argument)
  |    1:let job = __x2__
  |    2:app __x3__ = (HostEnqueuePromiseJob job.Job job.Realm)
  |    2:__x3__
  |    __x1__ = (+ __x1__ 1i)
  |  }
  |  3:return undefined
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. For each element _reaction_ of _reactions_, do""",
    """            1. Let _job_ be NewPromiseReactionJob(_reaction_, _argument_).""",
    """            1. Perform HostEnqueuePromiseJob(_job_.[[Job]], _job_.[[Realm]]).""",
    """          1. Return *undefined*.""",
  )
}
