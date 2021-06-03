package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ParseModule` extends Algo {
  val head = NormalHead("ParseModule", List(Param("sourceText", Normal), Param("realm", Normal), Param("hostDefined", Normal)))
  val ids = List(
    "sec-parsemodule",
    "sec-source-text-module-records",
    "sec-module-semantics",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  1:app __x0__ = (ParseText sourceText Module)
  |  1:let body = __x0__
  |  3:access __x1__ = (body "ModuleRequests")
  |  3:let requestedModules = __x1__
  |  4:access __x2__ = (body "ImportEntries")
  |  4:let importEntries = __x2__
  |  5:app __x3__ = (ImportedLocalNames importEntries)
  |  5:let importedBoundNames = __x3__
  |  6:let indirectExportEntries = (new [])
  |  7:let localExportEntries = (new [])
  |  8:let starExportEntries = (new [])
  |  9:access __x4__ = (body "ExportEntries")
  |  9:let exportEntries = __x4__
  |  10:let __x5__ = exportEntries
  |  10:let __x6__ = 0i
  |  10:while (< __x6__ __x5__.length) {
  |    let ee = __x5__[__x6__]
  |    24:if (= ee.ModuleRequest null) if (! (contains importedBoundNames ee.LocalName)) append ee -> localExportEntries else {
  |      15:??? "Let id:{ie} be the element of id:{importEntries} whose [ [ LocalName ] ] is the same as id:{ee} . [ [ LocalName ] ] ."
  |      19:if (= ie.ImportName "*") append ee -> localExportEntries else append (new ExportEntryRecord("ModuleRequest" -> ie.ModuleRequest, "ImportName" -> ie.ImportName, "LocalName" -> null, "ExportName" -> ee.ExportName)) -> indirectExportEntries
  |    } else if (&& (= ee.ImportName "*") (= ee.ExportName null)) append ee -> starExportEntries else append ee -> indirectExportEntries
  |    __x6__ = (+ __x6__ 1i)
  |  }
  |  26:return (new SourceTextModuleRecord("Realm" -> realm, "Environment" -> undefined, "Namespace" -> undefined, "Status" -> CONST_unlinked, "EvaluationError" -> undefined, "HostDefined" -> hostDefined, "ECMAScriptCode" -> body, "Context" -> CONST_empty, "ImportMeta" -> CONST_empty, "RequestedModules" -> requestedModules, "ImportEntries" -> importEntries, "LocalExportEntries" -> localExportEntries, "IndirectExportEntries" -> indirectExportEntries, "StarExportEntries" -> starExportEntries, "DFSIndex" -> undefined, "DFSAncestorIndex" -> undefined))
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: _sourceText_ is an ECMAScript source text (see clause <emu-xref href="#sec-ecmascript-language-source-code"></emu-xref>).""",
    """            1. Let _body_ be ParseText(_sourceText_, |Module|).""",
    """            1. If _body_ is a List of errors, return _body_.""",
    """            1. Let _requestedModules_ be the ModuleRequests of _body_.""",
    """            1. Let _importEntries_ be ImportEntries of _body_.""",
    """            1. Let _importedBoundNames_ be ImportedLocalNames(_importEntries_).""",
    """            1. Let _indirectExportEntries_ be a new empty List.""",
    """            1. Let _localExportEntries_ be a new empty List.""",
    """            1. Let _starExportEntries_ be a new empty List.""",
    """            1. Let _exportEntries_ be ExportEntries of _body_.""",
    """            1. For each ExportEntry Record _ee_ of _exportEntries_, do""",
    """              1. If _ee_.[[ModuleRequest]] is *null*, then""",
    """                1. If _ee_.[[LocalName]] is not an element of _importedBoundNames_, then""",
    """                  1. Append _ee_ to _localExportEntries_.""",
    """                1. Else,""",
    """                  1. Let _ie_ be the element of _importEntries_ whose [[LocalName]] is the same as _ee_.[[LocalName]].""",
    """                  1. If _ie_.[[ImportName]] is *"\*"*, then""",
    """                    1. NOTE: This is a re-export of an imported module namespace object.""",
    """                    1. Append _ee_ to _localExportEntries_.""",
    """                  1. Else,""",
    """                    1. NOTE: This is a re-export of a single name.""",
    """                    1. Append the ExportEntry Record { [[ModuleRequest]]: _ie_.[[ModuleRequest]], [[ImportName]]: _ie_.[[ImportName]], [[LocalName]]: *null*, [[ExportName]]: _ee_.[[ExportName]] } to _indirectExportEntries_.""",
    """              1. Else if _ee_.[[ImportName]] is *"\*"* and _ee_.[[ExportName]] is *null*, then""",
    """                1. Append _ee_ to _starExportEntries_.""",
    """              1. Else,""",
    """                1. Append _ee_ to _indirectExportEntries_.""",
    """            1. Return Source Text Module Record { [[Realm]]: _realm_, [[Environment]]: *undefined*, [[Namespace]]: *undefined*, [[Status]]: ~unlinked~, [[EvaluationError]]: *undefined*, [[HostDefined]]: _hostDefined_, [[ECMAScriptCode]]: _body_, [[Context]]: ~empty~, [[ImportMeta]]: ~empty~, [[RequestedModules]]: _requestedModules_, [[ImportEntries]]: _importEntries_, [[LocalExportEntries]]: _localExportEntries_, [[IndirectExportEntries]]: _indirectExportEntries_, [[StarExportEntries]]: _starExportEntries_, [[DFSIndex]]: *undefined*, [[DFSAncestorIndex]]: *undefined* }.""",
  )
}
