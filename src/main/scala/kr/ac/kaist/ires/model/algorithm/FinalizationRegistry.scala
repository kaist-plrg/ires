package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FinalizationRegistry` extends Algo {
  val head = BuiltinHead(parseRef("""FinalizationRegistry"""), List(Param("cleanupCallback", Normal)))
  val ids = List(
    "sec-finalization-registry-cleanup-callback",
    "sec-finalization-registry-constructor",
    "sec-finalization-registry-objects",
    "sec-managing-memory",
  )
  val rawBody = parseInst("""{
  |  0:if (= NewTarget undefined) throw TypeError else 7:{}
  |  1:app __x0__ = (IsCallable cleanupCallback)
  |  1:if (= __x0__ false) throw TypeError else 7:{}
  |  2:app __x1__ = (OrdinaryCreateFromConstructor NewTarget "%FinalizationRegistry.prototype%" (new ["Realm", "CleanupCallback", "Cells"]))
  |  2:let finalizationRegistry = [? __x1__]
  |  3:let fn = CONTEXT.Function
  |  4:finalizationRegistry.Realm = fn.Realm
  |  5:finalizationRegistry.CleanupCallback = cleanupCallback
  |  6:finalizationRegistry.Cells = (new [])
  |  7:return finalizationRegistry
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If NewTarget is *undefined*, throw a *TypeError* exception.""",
    """          1. If IsCallable(_cleanupCallback_) is *false*, throw a *TypeError* exception.""",
    """          1. Let _finalizationRegistry_ be ? OrdinaryCreateFromConstructor(NewTarget, *"%FinalizationRegistry.prototype%"*, « [[Realm]], [[CleanupCallback]], [[Cells]] »).""",
    """          1. Let _fn_ be the active function object.""",
    """          1. Set _finalizationRegistry_.[[Realm]] to _fn_.[[Realm]].""",
    """          1. Set _finalizationRegistry_.[[CleanupCallback]] to _cleanupCallback_.""",
    """          1. Set _finalizationRegistry_.[[Cells]] to a new empty List.""",
    """          1. Return _finalizationRegistry_.""",
  )
}
