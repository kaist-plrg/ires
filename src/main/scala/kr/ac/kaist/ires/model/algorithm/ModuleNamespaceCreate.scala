package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ModuleNamespaceCreate` extends Algo {
  val head = NormalHead("ModuleNamespaceCreate", List(Param("module", Normal), Param("exports", Normal)))
  val ids = List(
    "sec-modulenamespacecreate",
    "sec-module-namespace-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  1:assert (= module.Namespace undefined)
  |  3:let internalSlotsList = StrList
  |  4:app __x0__ = (MakeBasicObject internalSlotsList)
  |  4:let M = [! __x0__]
  |  6:M.Prototype = null
  |  7:M.Module = module
  |  8:let sortedExports = (copy-obj exports)
  |  9:M.Exports = sortedExports
  |  10:M.SubMap = (new SubMap())
  |  11:module.Namespace = M
  |  12:return M
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: _module_ is a Module Record.""",
    """          1. Assert: _module_.[[Namespace]] is *undefined*.""",
    """          1. Assert: _exports_ is a List of String values.""",
    """          1. Let _internalSlotsList_ be the internal slots listed in <emu-xref href="#table-internal-slots-of-module-namespace-exotic-objects"></emu-xref>.""",
    """          1. Let _M_ be ! MakeBasicObject(_internalSlotsList_).""",
    """          1. Set _M_'s essential internal methods to the definitions specified in <emu-xref href="#sec-module-namespace-exotic-objects"></emu-xref>.""",
    """          1. Set _M_.[[Prototype]] to *null*.""",
    """          1. Set _M_.[[Module]] to _module_.""",
    """          1. Let _sortedExports_ be a List whose elements are the elements of _exports_ ordered as if an Array of the same values had been sorted using %Array.prototype.sort% using *undefined* as _comparefn_.""",
    """          1. Set _M_.[[Exports]] to _sortedExports_.""",
    """          1. Create own properties of _M_ corresponding to the definitions in <emu-xref href="#sec-module-namespace-objects"></emu-xref>.""",
    """          1. Set _module_.[[Namespace]] to _M_.""",
    """          1. Return _M_.""",
  )
}
