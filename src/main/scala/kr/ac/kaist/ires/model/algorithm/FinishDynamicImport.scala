package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FinishDynamicImport` extends Algo {
  val head = NormalHead("FinishDynamicImport", List(Param("referencingScriptOrModule", Normal), Param("specifier", Normal), Param("promiseCapability", Normal), Param("completion", Normal)))
  val ids = List(
    "sec-finishdynamicimport",
    "sec-module-semantics",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  1:app __x0__ = (IsAbruptCompletion completion)
  |  1:if __x0__ {
  |    app __x1__ = (Call promiseCapability.Reject undefined (new [completion.Value]))
  |    [! __x1__]
  |  } else {
  |    3:app __x2__ = (HostResolveImportedModule referencingScriptOrModule specifier)
  |    3:let moduleRecord = [! __x2__]
  |    5:app __x3__ = (GetModuleNamespace moduleRecord)
  |    5:let namespace = __x3__
  |    7:app __x4__ = (IsAbruptCompletion namespace)
  |    7:if __x4__ {
  |      app __x5__ = (Call promiseCapability.Reject undefined (new [namespace.Value]))
  |      [! __x5__]
  |    } else {
  |      app __x6__ = (Call promiseCapability.Resolve undefined (new [namespace.Value]))
  |      [! __x6__]
  |    }
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If _completion_ is an abrupt completion, perform ! Call(_promiseCapability_.[[Reject]], *undefined*, « _completion_.[[Value]] »).""",
    """          1. Else,""",
    """            1. Assert: _completion_ is a normal completion and _completion_.[[Value]] is *undefined*.""",
    """            1. Let _moduleRecord_ be ! HostResolveImportedModule(_referencingScriptOrModule_, _specifier_).""",
    """            1. Assert: Evaluate has already been invoked on _moduleRecord_ and successfully completed.""",
    """            1. Let _namespace_ be GetModuleNamespace(_moduleRecord_).""",
    """            1. If _namespace_ is an abrupt completion, perform ! Call(_promiseCapability_.[[Reject]], *undefined*, « _namespace_.[[Value]] »).""",
    """            1. Else, perform ! Call(_promiseCapability_.[[Resolve]], *undefined*, « _namespace_.[[Value]] »).""",
  )
}
