package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::OrdinaryFunctionCreate` extends Algo {
  val head = NormalHead("OrdinaryFunctionCreate", List(Param("functionPrototype", Normal), Param("sourceText", Normal), Param("ParameterList", Normal), Param("Body", Normal), Param("thisMode", Normal), Param("Scope", Normal)))
  val ids = List(
    "sec-ordinaryfunctioncreate",
    "sec-ecmascript-function-objects",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof functionPrototype) Object)
  |  1:let internalSlotsList = StrList
  |  2:app __x0__ = (OrdinaryObjectCreate functionPrototype internalSlotsList)
  |  2:let F = [! __x0__]
  |  3:F.Call = ALGORITHM["ECMAScriptFunctionObject.Call"]
  |  4:F.SourceText = sourceText
  |  5:F.FormalParameters = ParameterList
  |  6:F.ECMAScriptCode = Body
  |  7:if true let Strict = true else let Strict = false
  |  8:F.Strict = Strict
  |  11:if (= thisMode CONST_lexicalDASHthis) F.ThisMode = CONST_lexical else if (= Strict true) F.ThisMode = CONST_strict else F.ThisMode = CONST_global
  |  12:F.IsClassConstructor = false
  |  13:F.Environment = Scope
  |  14:app __x1__ = (GetActiveScriptOrModule)
  |  14:F.ScriptOrModule = __x1__
  |  15:F.Realm = REALM
  |  16:F.HomeObject = undefined
  |  17:access __x2__ = (ParameterList "ExpectedArgumentCount")
  |  17:let len = __x2__
  |  18:app __x3__ = (SetFunctionLength F len)
  |  18:[! __x3__]
  |  19:return F
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: Type(_functionPrototype_) is Object.""",
    """        1. Let _internalSlotsList_ be the internal slots listed in <emu-xref href="#table-internal-slots-of-ecmascript-function-objects"></emu-xref>.""",
    """        1. Let _F_ be ! OrdinaryObjectCreate(_functionPrototype_, _internalSlotsList_).""",
    """        1. Set _F_.[[Call]] to the definition specified in <emu-xref href="#sec-ecmascript-function-objects-call-thisargument-argumentslist"></emu-xref>.""",
    """        1. Set _F_.[[SourceText]] to _sourceText_.""",
    """        1. Set _F_.[[FormalParameters]] to _ParameterList_.""",
    """        1. Set _F_.[[ECMAScriptCode]] to _Body_.""",
    """        1. If the source text matching _Body_ is strict mode code, let _Strict_ be *true*; else let _Strict_ be *false*.""",
    """        1. Set _F_.[[Strict]] to _Strict_.""",
    """        1. If _thisMode_ is ~lexical-this~, set _F_.[[ThisMode]] to ~lexical~.""",
    """        1. Else if _Strict_ is *true*, set _F_.[[ThisMode]] to ~strict~.""",
    """        1. Else, set _F_.[[ThisMode]] to ~global~.""",
    """        1. Set _F_.[[IsClassConstructor]] to *false*.""",
    """        1. Set _F_.[[Environment]] to _Scope_.""",
    """        1. Set _F_.[[ScriptOrModule]] to GetActiveScriptOrModule().""",
    """        1. Set _F_.[[Realm]] to the current Realm Record.""",
    """        1. Set _F_.[[HomeObject]] to *undefined*.""",
    """        1. Let _len_ be the ExpectedArgumentCount of _ParameterList_.""",
    """        1. Perform ! SetFunctionLength(_F_, _len_).""",
    """        1. Return _F_.""",
  )
}
