package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ImportMeta[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("ImportMeta", 0, 0, Rhs(List(Terminal("import"), Terminal("."), Terminal("meta")), None), "Evaluation", List())
  val ids = List(
    "sec-meta-properties-runtime-semantics-evaluation",
    "sec-meta-properties",
    "sec-left-hand-side-expressions",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (GetActiveScriptOrModule)
  |  0:let module = [! __x0__]
  |  1:assert (is-instance-of module SourceTextModuleRecord)
  |  2:let importMeta = module.ImportMeta
  |  11:if (= importMeta CONST_empty) {
  |    4:app __x1__ = (OrdinaryObjectCreate null)
  |    4:importMeta = [! __x1__]
  |    5:app __x2__ = (HostGetImportMetaProperties module)
  |    5:let importMetaValues = [! __x2__]
  |    6:let __x3__ = importMetaValues
  |    6:let __x4__ = 0i
  |    6:while (< __x4__ __x3__.length) {
  |      let p = __x3__[__x4__]
  |      7:app __x5__ = (CreateDataPropertyOrThrow importMeta p.Key p.Value)
  |      7:[! __x5__]
  |      __x4__ = (+ __x4__ 1i)
  |    }
  |    8:app __x6__ = (HostFinalizeImportMeta importMeta module)
  |    8:[! __x6__]
  |    9:module.ImportMeta = importMeta
  |    10:return importMeta
  |  } else {
  |    12:assert (= (typeof importMeta) Object)
  |    13:return importMeta
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _module_ be ! GetActiveScriptOrModule().""",
    """          1. Assert: _module_ is a Source Text Module Record.""",
    """          1. Let _importMeta_ be _module_.[[ImportMeta]].""",
    """          1. If _importMeta_ is ~empty~, then""",
    """            1. Set _importMeta_ to ! OrdinaryObjectCreate(*null*).""",
    """            1. Let _importMetaValues_ be ! HostGetImportMetaProperties(_module_).""",
    """            1. For each Record { [[Key]], [[Value]] } _p_ of _importMetaValues_, do""",
    """              1. Perform ! CreateDataPropertyOrThrow(_importMeta_, _p_.[[Key]], _p_.[[Value]]).""",
    """            1. Perform ! HostFinalizeImportMeta(_importMeta_, _module_).""",
    """            1. Set _module_.[[ImportMeta]] to _importMeta_.""",
    """            1. Return _importMeta_.""",
    """          1. Else,""",
    """            1. Assert: Type(_importMeta_) is Object.""",
    """            1. Return _importMeta_.""",
  )
}
