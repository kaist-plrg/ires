package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CreatePerIterationEnvironment` extends Algo {
  val head = NormalHead("CreatePerIterationEnvironment", List(Param("perIterationBindings", Normal)))
  val ids = List(
    "sec-createperiterationenvironment",
    "sec-for-statement",
    "sec-iteration-statements",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:if (< 0i perIterationBindings.length) {
  |    1:let lastIterationEnv = CONTEXT.LexicalEnvironment
  |    2:let outer = lastIterationEnv.OuterEnv
  |    3:assert (! (= outer null))
  |    4:app __x0__ = (NewDeclarativeEnvironment outer)
  |    4:let thisIterationEnv = __x0__
  |    5:let __x1__ = perIterationBindings
  |    5:let __x2__ = 0i
  |    5:while (< __x2__ __x1__.length) {
  |      let bn = __x1__[__x2__]
  |      6:app __x3__ = (thisIterationEnv.CreateMutableBinding thisIterationEnv bn false)
  |      6:[! __x3__]
  |      7:app __x4__ = (lastIterationEnv.GetBindingValue lastIterationEnv bn true)
  |      7:let lastValue = [? __x4__]
  |      8:app __x5__ = (thisIterationEnv.InitializeBinding thisIterationEnv bn lastValue)
  |      8:__x5__
  |      __x2__ = (+ __x2__ 1i)
  |    }
  |    9:CONTEXT.LexicalEnvironment = thisIterationEnv
  |  } else 10:{}
  |  10:return undefined
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If _perIterationBindings_ has any elements, then""",
    """            1. Let _lastIterationEnv_ be the running execution context's LexicalEnvironment.""",
    """            1. Let _outer_ be _lastIterationEnv_.[[OuterEnv]].""",
    """            1. Assert: _outer_ is not *null*.""",
    """            1. Let _thisIterationEnv_ be NewDeclarativeEnvironment(_outer_).""",
    """            1. For each element _bn_ of _perIterationBindings_, do""",
    """              1. Perform ! _thisIterationEnv_.CreateMutableBinding(_bn_, *false*).""",
    """              1. Let _lastValue_ be ? _lastIterationEnv_.GetBindingValue(_bn_, *true*).""",
    """              1. Perform _thisIterationEnv_.InitializeBinding(_bn_, _lastValue_).""",
    """            1. Set the running execution context's LexicalEnvironment to _thisIterationEnv_.""",
    """          1. Return *undefined*.""",
  )
}
