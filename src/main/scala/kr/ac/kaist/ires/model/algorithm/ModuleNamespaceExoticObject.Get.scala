package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ModuleNamespaceExoticObject.Get` extends Algo {
  val head = MethodHead("ModuleNamespaceExoticObject", "Get", Param("O", Normal), List(Param("P", Normal), Param("Receiver", Normal)))
  val ids = List(
    "sec-module-namespace-exotic-objects-get-p-receiver",
    "sec-module-namespace-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsPropertyKey P)
  |  0:assert (= __x0__ true)
  |  1:if (= (typeof P) Symbol) {
  |    2:app __x1__ = (OrdinaryGet O P Receiver)
  |    2:return [? __x1__]
  |  } else 0:{}
  |  3:let exports = O.Exports
  |  4:if (! (contains exports P)) return undefined else 0:{}
  |  5:let m = O.Module
  |  6:app __x2__ = (m.ResolveExport m P)
  |  6:let binding = [! __x2__]
  |  7:assert (is-instance-of binding ResolvedBindingRecord)
  |  8:let targetModule = binding.Module
  |  9:assert (! (= targetModule undefined))
  |  10:if (= binding.BindingName "*namespace*") {
  |    11:app __x3__ = (GetModuleNamespace targetModule)
  |    11:return [? __x3__]
  |  } else 0:{}
  |  12:let targetEnv = targetModule.Environment
  |  13:if (= targetEnv undefined) throw ReferenceError else 0:{}
  |  14:app __x4__ = (targetEnv.GetBindingValue targetEnv binding.BindingName true)
  |  14:return [? __x4__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: IsPropertyKey(_P_) is *true*.""",
    """          1. If Type(_P_) is Symbol, then""",
    """            1. Return ? OrdinaryGet(_O_, _P_, _Receiver_).""",
    """          1. Let _exports_ be _O_.[[Exports]].""",
    """          1. If _P_ is not an element of _exports_, return *undefined*.""",
    """          1. Let _m_ be _O_.[[Module]].""",
    """          1. Let _binding_ be ! _m_.ResolveExport(_P_).""",
    """          1. Assert: _binding_ is a ResolvedBinding Record.""",
    """          1. Let _targetModule_ be _binding_.[[Module]].""",
    """          1. Assert: _targetModule_ is not *undefined*.""",
    """          1. If _binding_.[[BindingName]] is *"\*namespace\*"*, then""",
    """            1. Return ? GetModuleNamespace(_targetModule_).""",
    """          1. Let _targetEnv_ be _targetModule_.[[Environment]].""",
    """          1. If _targetEnv_ is *undefined*, throw a *ReferenceError* exception.""",
    """          1. Return ? _targetEnv_.GetBindingValue(_binding_.[[BindingName]], *true*).""",
  )
}
