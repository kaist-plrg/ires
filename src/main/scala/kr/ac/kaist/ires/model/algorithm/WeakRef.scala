package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::WeakRef` extends Algo {
  val head = BuiltinHead(parseRef("""WeakRef"""), List(Param("target", Normal)))
  val ids = List(
    "sec-weak-ref-target",
    "sec-weak-ref-constructor",
    "sec-weak-ref-objects",
    "sec-managing-memory",
  )
  val rawBody = parseInst("""{
  |  0:if (= NewTarget undefined) throw TypeError else 7:{}
  |  1:if (! (= (typeof target) Object)) throw TypeError else 7:{}
  |  2:app __x0__ = (OrdinaryCreateFromConstructor NewTarget "%WeakRef.prototype%" (new ["WeakRefTarget"]))
  |  2:let weakRef = [? __x0__]
  |  3:app __x1__ = (AddToKeptObjects target)
  |  3:[! __x1__]
  |  4:weakRef.WeakRefTarget = target
  |  5:return weakRef
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If NewTarget is *undefined*, throw a *TypeError* exception.""",
    """          1. If Type(_target_) is not Object, throw a *TypeError* exception.""",
    """          1. Let _weakRef_ be ? OrdinaryCreateFromConstructor(NewTarget, *"%WeakRef.prototype%"*, « [[WeakRefTarget]] »).""",
    """          1. Perform ! AddToKeptObjects(_target_).""",
    """          1. Set _weakRef_.[[WeakRefTarget]] to _target_.""",
    """          1. Return _weakRef_.""",
  )
}
