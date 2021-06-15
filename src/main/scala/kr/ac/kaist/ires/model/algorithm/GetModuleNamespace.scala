package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GetModuleNamespace` extends Algo {
  val head = NormalHead("GetModuleNamespace", List(Param("module", Normal)))
  val ids = List(
    "sec-getmodulenamespace",
    "sec-module-semantics",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  2:let namespace = module.Namespace
  |  3:if (= namespace undefined) {
  |    4:app __x0__ = (module.GetExportedNames module)
  |    4:let exportedNames = [? __x0__]
  |    5:let unambiguousNames = (new [])
  |    6:let __x1__ = exportedNames
  |    6:let __x2__ = 0i
  |    6:while (< __x2__ __x1__.length) {
  |      let name = __x1__[__x2__]
  |      7:app __x3__ = (module.ResolveExport module name)
  |      7:let resolution = [? __x3__]
  |      8:if (is-instance-of resolution ResolvedBindingRecord) append name -> unambiguousNames else 1:{}
  |      __x2__ = (+ __x2__ 1i)
  |    }
  |    9:app __x4__ = (ModuleNamespaceCreate module unambiguousNames)
  |    9:namespace = __x4__
  |  } else 1:{}
  |  10:return namespace
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: _module_ is an instance of a concrete subclass of Module Record.""",
    """          1. Assert: If _module_ is a Cyclic Module Record, then _module_.[[Status]] is not ~unlinked~.""",
    """          1. Let _namespace_ be _module_.[[Namespace]].""",
    """          1. If _namespace_ is *undefined*, then""",
    """            1. Let _exportedNames_ be ? _module_.GetExportedNames().""",
    """            1. Let _unambiguousNames_ be a new empty List.""",
    """            1. For each element _name_ of _exportedNames_, do""",
    """              1. Let _resolution_ be ? _module_.ResolveExport(_name_).""",
    """              1. If _resolution_ is a ResolvedBinding Record, append _name_ to _unambiguousNames_.""",
    """            1. Set _namespace_ to ModuleNamespaceCreate(_module_, _unambiguousNames_).""",
    """          1. Return _namespace_.""",
  )
}
