package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GetSuperConstructor` extends Algo {
  val head = NormalHead("GetSuperConstructor", List())
  val ids = List(
    "sec-getsuperconstructor",
    "sec-super-keyword",
    "sec-left-hand-side-expressions",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (GetThisEnvironment)
  |  0:let envRec = __x0__
  |  1:assert (is-instance-of envRec FunctionEnvironmentRecord)
  |  2:let activeFunction = envRec.FunctionObject
  |  3:assert (is-instance-of activeFunction ECMAScriptFunctionObject)
  |  4:app __x1__ = (activeFunction.GetPrototypeOf activeFunction)
  |  4:let superConstructor = [! __x1__]
  |  5:return superConstructor
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _envRec_ be GetThisEnvironment().""",
    """          1. Assert: _envRec_ is a function Environment Record.""",
    """          1. Let _activeFunction_ be _envRec_.[[FunctionObject]].""",
    """          1. Assert: _activeFunction_ is an ECMAScript function object.""",
    """          1. Let _superConstructor_ be ! _activeFunction_.[[GetPrototypeOf]]().""",
    """          1. Return _superConstructor_.""",
  )
}
