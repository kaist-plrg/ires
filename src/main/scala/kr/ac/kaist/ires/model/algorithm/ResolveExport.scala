package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ResolveExport` extends Algo {
  val head = NormalHead("ResolveExport", List(Param("exportName", Normal), Param("resolveSet", Optional)))
  val ids = List(
    "sec-resolveexport",
    "sec-source-text-module-records",
    "sec-module-semantics",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:if (= resolveSet absent) resolveSet = (new []) else 33:{}
  |  2:let __x0__ = resolveSet
  |  2:let __x1__ = 0i
  |  2:while (< __x1__ __x0__.length) {
  |    let r = __x0__[__x1__]
  |    3:let __x2__ = true
  |    3:__x2__ = (= module r.Module)
  |    3:if __x2__ {
  |      app __x3__ = (SameValue exportName r.ExportName)
  |      __x2__ = (= __x3__ true)
  |    } else 33:{}
  |    3:if __x2__ return null else 33:{}
  |    __x1__ = (+ __x1__ 1i)
  |  }
  |  6:append (new Record("Module" -> module, "ExportName" -> exportName)) -> resolveSet
  |  7:let __x4__ = module.LocalExportEntries
  |  7:let __x5__ = 0i
  |  7:while (< __x5__ __x4__.length) {
  |    let e = __x4__[__x5__]
  |    8:app __x6__ = (SameValue exportName e.ExportName)
  |    8:if (= __x6__ true) return (new ResolvedBindingRecord("Module" -> module, "BindingName" -> e.LocalName)) else 33:{}
  |    __x5__ = (+ __x5__ 1i)
  |  }
  |  11:let __x7__ = module.IndirectExportEntries
  |  11:let __x8__ = 0i
  |  11:while (< __x8__ __x7__.length) {
  |    let e = __x7__[__x8__]
  |    12:app __x9__ = (SameValue exportName e.ExportName)
  |    12:if (= __x9__ true) {
  |      13:app __x10__ = (HostResolveImportedModule module e.ModuleRequest)
  |      13:let importedModule = [? __x10__]
  |      17:if (= e.ImportName "*") return (new ResolvedBindingRecord("Module" -> importedModule, "BindingName" -> "*namespace*")) else {
  |        19:app __x11__ = (importedModule.ResolveExport importedModule e.ImportName resolveSet)
  |        19:return __x11__
  |      }
  |    } else 33:{}
  |    __x8__ = (+ __x8__ 1i)
  |  }
  |  20:app __x12__ = (SameValue exportName "default")
  |  20:if (= __x12__ true) return null else 33:{}
  |  24:let starResolution = null
  |  25:let __x13__ = module.StarExportEntries
  |  25:let __x14__ = 0i
  |  25:while (< __x14__ __x13__.length) {
  |    let e = __x13__[__x14__]
  |    26:app __x15__ = (HostResolveImportedModule module e.ModuleRequest)
  |    26:let importedModule = [? __x15__]
  |    27:app __x16__ = (importedModule.ResolveExport importedModule exportName resolveSet)
  |    27:let resolution = [? __x16__]
  |    28:if (= resolution "ambiguous") return "ambiguous" else 33:{}
  |    29:if (! (= resolution null)) {
  |      30:assert (is-instance-of resolution ResolvedBindingRecord)
  |      32:if (= starResolution null) starResolution = resolution else {
  |        34:let __x17__ = true
  |        34:__x17__ = (! (= resolution.Module starResolution.Module))
  |        34:if __x17__ 33:{} else {
  |          app __x18__ = (SameValue resolution.BindingName starResolution.BindingName)
  |          __x17__ = (= __x18__ false)
  |        }
  |        34:if __x17__ return "ambiguous" else 33:{}
  |      }
  |    } else 33:{}
  |    __x14__ = (+ __x14__ 1i)
  |  }
  |  35:return starResolution
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. If _resolveSet_ is not present, set _resolveSet_ to a new empty List.""",
    """            1. Assert: _resolveSet_ is a List of Record { [[Module]], [[ExportName]] }.""",
    """            1. For each Record { [[Module]], [[ExportName]] } _r_ of _resolveSet_, do""",
    """              1. If _module_ and _r_.[[Module]] are the same Module Record and SameValue(_exportName_, _r_.[[ExportName]]) is *true*, then""",
    """                1. Assert: This is a circular import request.""",
    """                1. Return *null*.""",
    """            1. Append the Record { [[Module]]: _module_, [[ExportName]]: _exportName_ } to _resolveSet_.""",
    """            1. For each ExportEntry Record _e_ of _module_.[[LocalExportEntries]], do""",
    """              1. If SameValue(_exportName_, _e_.[[ExportName]]) is *true*, then""",
    """                1. Assert: _module_ provides the direct binding for this export.""",
    """                1. Return ResolvedBinding Record { [[Module]]: _module_, [[BindingName]]: _e_.[[LocalName]] }.""",
    """            1. For each ExportEntry Record _e_ of _module_.[[IndirectExportEntries]], do""",
    """              1. If SameValue(_exportName_, _e_.[[ExportName]]) is *true*, then""",
    """                1. Let _importedModule_ be ? HostResolveImportedModule(_module_, _e_.[[ModuleRequest]]).""",
    """                1. If _e_.[[ImportName]] is *"\*"*, then""",
    """                  1. Assert: _module_ does not provide the direct binding for this export.""",
    """                  1. Return ResolvedBinding Record { [[Module]]: _importedModule_, [[BindingName]]: *"\*namespace\*"* }.""",
    """                1. Else,""",
    """                  1. Assert: _module_ imports a specific binding for this export.""",
    """                  1. Return _importedModule_.ResolveExport(_e_.[[ImportName]], _resolveSet_).""",
    """            1. If SameValue(_exportName_, *"default"*) is *true*, then""",
    """              1. Assert: A `default` export was not explicitly defined by this module.""",
    """              1. Return *null*.""",
    """              1. NOTE: A `default` export cannot be provided by an `export *` or `export * from "mod"` declaration.""",
    """            1. Let _starResolution_ be *null*.""",
    """            1. For each ExportEntry Record _e_ of _module_.[[StarExportEntries]], do""",
    """              1. Let _importedModule_ be ? HostResolveImportedModule(_module_, _e_.[[ModuleRequest]]).""",
    """              1. Let _resolution_ be ? _importedModule_.ResolveExport(_exportName_, _resolveSet_).""",
    """              1. If _resolution_ is *"ambiguous"*, return *"ambiguous"*.""",
    """              1. If _resolution_ is not *null*, then""",
    """                1. Assert: _resolution_ is a ResolvedBinding Record.""",
    """                1. If _starResolution_ is *null*, set _starResolution_ to _resolution_.""",
    """                1. Else,""",
    """                  1. Assert: There is more than one `*` import that includes the requested name.""",
    """                  1. If _resolution_.[[Module]] and _starResolution_.[[Module]] are not the same Module Record or SameValue(_resolution_.[[BindingName]], _starResolution_.[[BindingName]]) is *false*, return *"ambiguous"*.""",
    """            1. Return _starResolution_.""",
  )
}
