package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GetExportedNames` extends Algo {
  val head = NormalHead("GetExportedNames", List(Param("exportStarSet", Optional)))
  val ids = List(
    "sec-getexportednames",
    "sec-source-text-module-records",
    "sec-module-semantics",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:if (= exportStarSet absent) exportStarSet = (new []) else 11:{}
  |  2:if (contains exportStarSet module) return (new []) else 11:{}
  |  5:append module -> exportStarSet
  |  6:let exportedNames = (new [])
  |  7:let __x0__ = module.LocalExportEntries
  |  7:let __x1__ = 0i
  |  7:while (< __x1__ __x0__.length) {
  |    let e = __x0__[__x1__]
  |    9:append e.ExportName -> exportedNames
  |    __x1__ = (+ __x1__ 1i)
  |  }
  |  10:let __x2__ = module.IndirectExportEntries
  |  10:let __x3__ = 0i
  |  10:while (< __x3__ __x2__.length) {
  |    let e = __x2__[__x3__]
  |    12:append e.ExportName -> exportedNames
  |    __x3__ = (+ __x3__ 1i)
  |  }
  |  13:let __x4__ = module.StarExportEntries
  |  13:let __x5__ = 0i
  |  13:while (< __x5__ __x4__.length) {
  |    let e = __x4__[__x5__]
  |    14:app __x6__ = (HostResolveImportedModule module e.ModuleRequest)
  |    14:let requestedModule = [? __x6__]
  |    15:app __x7__ = (requestedModule.GetExportedNames requestedModule exportStarSet)
  |    15:let starNames = [? __x7__]
  |    16:let __x8__ = starNames
  |    16:let __x9__ = 0i
  |    16:while (< __x9__ __x8__.length) {
  |      let n = __x8__[__x9__]
  |      17:app __x10__ = (SameValue n "default")
  |      17:if (= __x10__ false) if (! (contains exportedNames n)) append n -> exportedNames else 11:{} else 11:{}
  |      __x9__ = (+ __x9__ 1i)
  |    }
  |    __x5__ = (+ __x5__ 1i)
  |  }
  |  20:return exportedNames
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. If _exportStarSet_ is not present, set _exportStarSet_ to a new empty List.""",
    """            1. Assert: _exportStarSet_ is a List of Source Text Module Records.""",
    """            1. If _exportStarSet_ contains _module_, then""",
    """              1. Assert: We've reached the starting point of an `export *` circularity.""",
    """              1. Return a new empty List.""",
    """            1. Append _module_ to _exportStarSet_.""",
    """            1. Let _exportedNames_ be a new empty List.""",
    """            1. For each ExportEntry Record _e_ of _module_.[[LocalExportEntries]], do""",
    """              1. Assert: _module_ provides the direct binding for this export.""",
    """              1. Append _e_.[[ExportName]] to _exportedNames_.""",
    """            1. For each ExportEntry Record _e_ of _module_.[[IndirectExportEntries]], do""",
    """              1. Assert: _module_ imports a specific binding for this export.""",
    """              1. Append _e_.[[ExportName]] to _exportedNames_.""",
    """            1. For each ExportEntry Record _e_ of _module_.[[StarExportEntries]], do""",
    """              1. Let _requestedModule_ be ? HostResolveImportedModule(_module_, _e_.[[ModuleRequest]]).""",
    """              1. Let _starNames_ be ? _requestedModule_.GetExportedNames(_exportStarSet_).""",
    """              1. For each element _n_ of _starNames_, do""",
    """                1. If SameValue(_n_, *"default"*) is *false*, then""",
    """                  1. If _n_ is not an element of _exportedNames_, then""",
    """                    1. Append _n_ to _exportedNames_.""",
    """            1. Return _exportedNames_.""",
  )
}
