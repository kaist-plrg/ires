package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::InnerModuleLinking` extends Algo {
  val head = NormalHead("InnerModuleLinking", List(Param("module", Normal), Param("stack", Normal), Param("index", Normal)))
  val ids = List(
    "sec-InnerModuleLinking",
    "sec-moduledeclarationlinking",
    "sec-cyclic-module-records",
    "sec-module-semantics",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:if (! (is-instance-of module CyclicModuleRecord)) {
  |    1:app __x0__ = (module.Link module)
  |    1:[? __x0__]
  |    2:return index
  |  } else 20:{}
  |  3:if (|| (|| (= module.Status CONST_linking) (= module.Status CONST_linked)) (= module.Status CONST_evaluated)) return index else 20:{}
  |  5:assert (= module.Status CONST_unlinked)
  |  6:module.Status = CONST_linking
  |  7:module.DFSIndex = index
  |  8:module.DFSAncestorIndex = index
  |  9:index = (+ index 1i)
  |  10:append module -> stack
  |  11:let __x1__ = module.RequestedModules
  |  11:let __x2__ = 0i
  |  11:while (< __x2__ __x1__.length) {
  |    let required = __x1__[__x2__]
  |    12:app __x3__ = (HostResolveImportedModule module required)
  |    12:let requiredModule = [? __x3__]
  |    13:app __x4__ = (InnerModuleLinking requiredModule stack index)
  |    13:index = [? __x4__]
  |    14:if (is-instance-of requiredModule CyclicModuleRecord) {
  |      15:assert (|| (|| (= requiredModule.Status CONST_linking) (= requiredModule.Status CONST_linked)) (= requiredModule.Status CONST_evaluated))
  |      17:if (= requiredModule.Status CONST_linking) {
  |        18:app __x5__ = (min module.DFSAncestorIndex requiredModule.DFSAncestorIndex)
  |        18:module.DFSAncestorIndex = __x5__
  |      } else 20:{}
  |    } else 20:{}
  |    __x2__ = (+ __x2__ 1i)
  |  }
  |  19:app __x6__ = (module.InitializeEnvironment module)
  |  19:[? __x6__]
  |  21:assert (! (< module.DFSIndex module.DFSAncestorIndex))
  |  22:if (== module.DFSAncestorIndex module.DFSIndex) {
  |    23:let done = false
  |    24:while (= done false) {
  |      25:let requiredModule = stack[(- stack.length 1i)]
  |      26:(pop stack (- stack.length 1i))
  |      27:assert (is-instance-of requiredModule CyclicModuleRecord)
  |      28:requiredModule.Status = CONST_linked
  |      29:if (= requiredModule module) done = true else 20:{}
  |    }
  |  } else 20:{}
  |  30:return index
  |}""".stripMargin)
  val code = scala.Array[String](
    """              1. If _module_ is not a Cyclic Module Record, then""",
    """                1. Perform ? _module_.Link().""",
    """                1. Return _index_.""",
    """              1. If _module_.[[Status]] is ~linking~, ~linked~, or ~evaluated~, then""",
    """                1. Return _index_.""",
    """              1. Assert: _module_.[[Status]] is ~unlinked~.""",
    """              1. Set _module_.[[Status]] to ~linking~.""",
    """              1. Set _module_.[[DFSIndex]] to _index_.""",
    """              1. Set _module_.[[DFSAncestorIndex]] to _index_.""",
    """              1. Set _index_ to _index_ + 1.""",
    """              1. Append _module_ to _stack_.""",
    """              1. For each String _required_ of _module_.[[RequestedModules]], do""",
    """                1. Let _requiredModule_ be ? HostResolveImportedModule(_module_, _required_).""",
    """                1. Set _index_ to ? InnerModuleLinking(_requiredModule_, _stack_, _index_).""",
    """                1. If _requiredModule_ is a Cyclic Module Record, then""",
    """                  1. Assert: _requiredModule_.[[Status]] is either ~linking~, ~linked~, or ~evaluated~.""",
    """                  1. Assert: _requiredModule_.[[Status]] is ~linking~ if and only if _requiredModule_ is in _stack_.""",
    """                  1. If _requiredModule_.[[Status]] is ~linking~, then""",
    """                    1. Set _module_.[[DFSAncestorIndex]] to min(_module_.[[DFSAncestorIndex]], _requiredModule_.[[DFSAncestorIndex]]).""",
    """              1. Perform ? _module_.InitializeEnvironment().""",
    """              1. Assert: _module_ occurs exactly once in _stack_.""",
    """              1. Assert: _module_.[[DFSAncestorIndex]] ≤ _module_.[[DFSIndex]].""",
    """              1. If _module_.[[DFSAncestorIndex]] = _module_.[[DFSIndex]], then""",
    """                1. Let _done_ be *false*.""",
    """                1. Repeat, while _done_ is *false*,""",
    """                  1. Let _requiredModule_ be the last element in _stack_.""",
    """                  1. Remove the last element of _stack_.""",
    """                  1. Assert: _requiredModule_ is a Cyclic Module Record.""",
    """                  1. Set _requiredModule_.[[Status]] to ~linked~.""",
    """                  1. If _requiredModule_ and _module_ are the same Module Record, set _done_ to *true*.""",
    """              1. Return _index_.""",
  )
}
