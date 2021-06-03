package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::InnerModuleEvaluation` extends Algo {
  val head = NormalHead("InnerModuleEvaluation", List(Param("module", Normal), Param("stack", Normal), Param("index", Normal)))
  val ids = List(
    "sec-innermoduleevaluation",
    "sec-moduleevaluation",
    "sec-cyclic-module-records",
    "sec-module-semantics",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:if (! (is-instance-of module CyclicModuleRecord)) {
  |    1:app __x0__ = (module.Evaluate module)
  |    1:[? __x0__]
  |    2:return index
  |  } else 23:{}
  |  3:if (= module.Status CONST_evaluated) if (= module.EvaluationError undefined) return index else return module.EvaluationError else 23:{}
  |  6:if (= module.Status CONST_evaluating) return index else 23:{}
  |  7:assert (= module.Status CONST_linked)
  |  8:module.Status = CONST_evaluating
  |  9:module.DFSIndex = index
  |  10:module.DFSAncestorIndex = index
  |  11:index = (+ index 1i)
  |  12:append module -> stack
  |  13:let __x1__ = module.RequestedModules
  |  13:let __x2__ = 0i
  |  13:while (< __x2__ __x1__.length) {
  |    let required = __x1__[__x2__]
  |    14:app __x3__ = (HostResolveImportedModule module required)
  |    14:let requiredModule = [! __x3__]
  |    16:app __x4__ = (InnerModuleEvaluation requiredModule stack index)
  |    16:index = [? __x4__]
  |    17:if (is-instance-of requiredModule CyclicModuleRecord) {
  |      18:assert (|| (= requiredModule.Status CONST_evaluating) (= requiredModule.Status CONST_evaluated))
  |      20:if (= requiredModule.Status CONST_evaluating) {
  |        21:app __x5__ = (min module.DFSAncestorIndex requiredModule.DFSAncestorIndex)
  |        21:module.DFSAncestorIndex = __x5__
  |      } else 23:{}
  |    } else 23:{}
  |    __x2__ = (+ __x2__ 1i)
  |  }
  |  22:app __x6__ = (module.ExecuteModule module)
  |  22:[? __x6__]
  |  24:assert (! (< module.DFSIndex module.DFSAncestorIndex))
  |  25:if (== module.DFSAncestorIndex module.DFSIndex) {
  |    26:let done = false
  |    27:while (= done false) {
  |      28:let requiredModule = stack[(- stack.length 1i)]
  |      29:(pop stack (- stack.length 1i))
  |      30:assert (is-instance-of requiredModule CyclicModuleRecord)
  |      31:requiredModule.Status = CONST_evaluated
  |      32:if (= requiredModule module) done = true else 23:{}
  |    }
  |  } else 23:{}
  |  33:return index
  |}""".stripMargin)
  val code = scala.Array[String](
    """              1. If _module_ is not a Cyclic Module Record, then""",
    """                1. Perform ? _module_.Evaluate().""",
    """                1. Return _index_.""",
    """              1. If _module_.[[Status]] is ~evaluated~, then""",
    """                1. If _module_.[[EvaluationError]] is *undefined*, return _index_.""",
    """                1. Otherwise, return _module_.[[EvaluationError]].""",
    """              1. If _module_.[[Status]] is ~evaluating~, return _index_.""",
    """              1. Assert: _module_.[[Status]] is ~linked~.""",
    """              1. Set _module_.[[Status]] to ~evaluating~.""",
    """              1. Set _module_.[[DFSIndex]] to _index_.""",
    """              1. Set _module_.[[DFSAncestorIndex]] to _index_.""",
    """              1. Set _index_ to _index_ + 1.""",
    """              1. Append _module_ to _stack_.""",
    """              1. For each String _required_ of _module_.[[RequestedModules]], do""",
    """                1. Let _requiredModule_ be ! HostResolveImportedModule(_module_, _required_).""",
    """                1. NOTE: Link must be completed successfully prior to invoking this method, so every requested module is guaranteed to resolve successfully.""",
    """                1. Set _index_ to ? InnerModuleEvaluation(_requiredModule_, _stack_, _index_).""",
    """                1. If _requiredModule_ is a Cyclic Module Record, then""",
    """                  1. Assert: _requiredModule_.[[Status]] is either ~evaluating~ or ~evaluated~.""",
    """                  1. Assert: _requiredModule_.[[Status]] is ~evaluating~ if and only if _requiredModule_ is in _stack_.""",
    """                  1. If _requiredModule_.[[Status]] is ~evaluating~, then""",
    """                    1. Set _module_.[[DFSAncestorIndex]] to min(_module_.[[DFSAncestorIndex]], _requiredModule_.[[DFSAncestorIndex]]).""",
    """              1. Perform ? _module_.ExecuteModule().""",
    """              1. Assert: _module_ occurs exactly once in _stack_.""",
    """              1. Assert: _module_.[[DFSAncestorIndex]] ≤ _module_.[[DFSIndex]].""",
    """              1. If _module_.[[DFSAncestorIndex]] = _module_.[[DFSIndex]], then""",
    """                1. Let _done_ be *false*.""",
    """                1. Repeat, while _done_ is *false*,""",
    """                  1. Let _requiredModule_ be the last element in _stack_.""",
    """                  1. Remove the last element of _stack_.""",
    """                  1. Assert: _requiredModule_ is a Cyclic Module Record.""",
    """                  1. Set _requiredModule_.[[Status]] to ~evaluated~.""",
    """                  1. If _requiredModule_ and _module_ are the same Module Record, set _done_ to *true*.""",
    """              1. Return _index_.""",
  )
}
