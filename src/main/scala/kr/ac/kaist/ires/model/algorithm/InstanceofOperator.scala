package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::InstanceofOperator` extends Algo {
  val head = NormalHead("InstanceofOperator", List(Param("V", Normal), Param("target", Normal)))
  val ids = List(
    "sec-instanceofoperator",
    "sec-relational-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= (typeof target) Object)) throw TypeError else 3:{}
  |  1:app __x0__ = (GetMethod target SYMBOL_hasInstance)
  |  1:let instOfHandler = [? __x0__]
  |  2:if (! (= instOfHandler undefined)) {
  |    3:app __x1__ = (Call instOfHandler target (new [V]))
  |    3:app __x2__ = (ToBoolean [? __x1__])
  |    3:return [! __x2__]
  |  } else 3:{}
  |  4:app __x3__ = (IsCallable target)
  |  4:if (= __x3__ false) throw TypeError else 3:{}
  |  5:app __x4__ = (OrdinaryHasInstance target V)
  |  5:return [? __x4__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If Type(_target_) is not Object, throw a *TypeError* exception.""",
    """        1. Let _instOfHandler_ be ? GetMethod(_target_, @@hasInstance).""",
    """        1. If _instOfHandler_ is not *undefined*, then""",
    """          1. Return ! ToBoolean(? Call(_instOfHandler_, _target_, « _V_ »)).""",
    """        1. [id="step-instanceof-check-function"] If IsCallable(_target_) is *false*, throw a *TypeError* exception.""",
    """        1. [id="step-instanceof-fallback"] Return ? OrdinaryHasInstance(_target_, _V_).""",
  )
}
