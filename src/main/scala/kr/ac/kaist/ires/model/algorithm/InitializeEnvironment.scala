package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::InitializeEnvironment` extends Algo {
  val head = NormalHead("InitializeEnvironment", List())
  val ids = List(
    "sec-source-text-module-record-initialize-environment",
    "sec-source-text-module-records",
    "sec-module-semantics",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:let __x0__ = module.IndirectExportEntries
  |  0:let __x1__ = 0i
  |  0:while (< __x1__ __x0__.length) {
  |    let e = __x0__[__x1__]
  |    1:app __x2__ = (module.ResolveExport module e.ExportName)
  |    1:let resolution = [? __x2__]
  |    2:if (|| (= resolution null) (= resolution "ambiguous")) throw SyntaxError else 11:{}
  |    3:assert (is-instance-of resolution ResolvedBindingRecord)
  |    __x1__ = (+ __x1__ 1i)
  |  }
  |  5:let realm = module.Realm
  |  6:assert (! (= realm undefined))
  |  7:app __x3__ = (NewModuleEnvironment realm.GlobalEnv)
  |  7:let env = __x3__
  |  8:module.Environment = env
  |  9:let __x4__ = module.ImportEntries
  |  9:let __x5__ = 0i
  |  9:while (< __x5__ __x4__.length) {
  |    let in = __x4__[__x5__]
  |    10:app __x6__ = (HostResolveImportedModule module in.ModuleRequest)
  |    10:let importedModule = [! __x6__]
  |    16:if (= in.ImportName "*") {
  |      13:app __x7__ = (GetModuleNamespace importedModule)
  |      13:let namespace = [? __x7__]
  |      14:app __x8__ = (env.CreateImmutableBinding env in.LocalName true)
  |      14:[! __x8__]
  |      15:app __x9__ = (env.InitializeBinding env in.LocalName namespace)
  |      15:__x9__
  |    } else {
  |      17:app __x10__ = (importedModule.ResolveExport importedModule in.ImportName)
  |      17:let resolution = [? __x10__]
  |      18:if (|| (= resolution null) (= resolution "ambiguous")) throw SyntaxError else 11:{}
  |      23:if (= resolution.BindingName "*namespace*") {
  |        20:app __x11__ = (GetModuleNamespace resolution.Module)
  |        20:let namespace = [? __x11__]
  |        21:app __x12__ = (env.CreateImmutableBinding env in.LocalName true)
  |        21:[! __x12__]
  |        22:app __x13__ = (env.InitializeBinding env in.LocalName namespace)
  |        22:__x13__
  |      } else {
  |        24:app __x14__ = (env.CreateImportBinding env in.LocalName resolution.Module resolution.BindingName)
  |        24:__x14__
  |      }
  |    }
  |    __x5__ = (+ __x5__ 1i)
  |  }
  |  25:let moduleContext = (new ExecutionContext())
  |  26:moduleContext.Function = null
  |  27:assert (! (= module.Realm undefined))
  |  28:moduleContext.Realm = module.Realm
  |  29:moduleContext.ScriptOrModule = module
  |  30:moduleContext.VariableEnvironment = module.Environment
  |  31:moduleContext.LexicalEnvironment = module.Environment
  |  32:module.Context = moduleContext
  |  33:append moduleContext -> EXECUTION_STACK
  |  33:CONTEXT = EXECUTION_STACK[(- EXECUTION_STACK.length 1i)]
  |  34:let code = module.ECMAScriptCode
  |  35:access __x15__ = (code "VarScopedDeclarations")
  |  35:let varDeclarations = __x15__
  |  36:let declaredVarNames = (new [])
  |  37:let __x16__ = varDeclarations
  |  37:let __x17__ = 0i
  |  37:while (< __x17__ __x16__.length) {
  |    let d = __x16__[__x17__]
  |    38:access __x18__ = (d "BoundNames")
  |    38:let __x19__ = __x18__
  |    38:let __x20__ = 0i
  |    38:while (< __x20__ __x19__.length) {
  |      let dn = __x19__[__x20__]
  |      39:if (! (contains declaredVarNames dn)) {
  |        40:app __x21__ = (env.CreateMutableBinding env dn false)
  |        40:[! __x21__]
  |        41:app __x22__ = (env.InitializeBinding env dn undefined)
  |        41:__x22__
  |        42:append dn -> declaredVarNames
  |      } else 11:{}
  |      __x20__ = (+ __x20__ 1i)
  |    }
  |    __x17__ = (+ __x17__ 1i)
  |  }
  |  43:access __x23__ = (code "LexicallyScopedDeclarations")
  |  43:let lexDeclarations = __x23__
  |  44:let __x24__ = lexDeclarations
  |  44:let __x25__ = 0i
  |  44:while (< __x25__ __x24__.length) {
  |    let d = __x24__[__x25__]
  |    45:access __x26__ = (d "BoundNames")
  |    45:let __x27__ = __x26__
  |    45:let __x28__ = 0i
  |    45:while (< __x28__ __x27__.length) {
  |      let dn = __x27__[__x28__]
  |      48:access __x29__ = (d "IsConstantDeclaration")
  |      48:if (= __x29__ true) {
  |        47:app __x30__ = (env.CreateImmutableBinding env dn true)
  |        47:[! __x30__]
  |      } else {
  |        49:app __x31__ = (env.CreateMutableBinding env dn false)
  |        49:[! __x31__]
  |      }
  |      50:if (|| (|| (|| (is-instance-of d FunctionDeclaration) (is-instance-of d GeneratorDeclaration)) (is-instance-of d AsyncFunctionDeclaration)) (is-instance-of d AsyncGeneratorDeclaration)) {
  |        51:access __x32__ = (d "InstantiateFunctionObject" env)
  |        51:let fo = __x32__
  |        52:app __x33__ = (env.InitializeBinding env dn fo)
  |        52:__x33__
  |      } else 11:{}
  |      __x28__ = (+ __x28__ 1i)
  |    }
  |    __x25__ = (+ __x25__ 1i)
  |  }
  |  53:??? "Remove id:{moduleContext} from the execution context stack ."
  |  54:return CONST_empty
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. For each ExportEntry Record _e_ of _module_.[[IndirectExportEntries]], do""",
    """              1. Let _resolution_ be ? _module_.ResolveExport(_e_.[[ExportName]]).""",
    """              1. If _resolution_ is *null* or *"ambiguous"*, throw a *SyntaxError* exception.""",
    """              1. Assert: _resolution_ is a ResolvedBinding Record.""",
    """            1. Assert: All named exports from _module_ are resolvable.""",
    """            1. Let _realm_ be _module_.[[Realm]].""",
    """            1. Assert: _realm_ is not *undefined*.""",
    """            1. Let _env_ be NewModuleEnvironment(_realm_.[[GlobalEnv]]).""",
    """            1. Set _module_.[[Environment]] to _env_.""",
    """            1. For each ImportEntry Record _in_ of _module_.[[ImportEntries]], do""",
    """              1. Let _importedModule_ be ! HostResolveImportedModule(_module_, _in_.[[ModuleRequest]]).""",
    """              1. NOTE: The above call cannot fail because imported module requests are a subset of _module_.[[RequestedModules]], and these have been resolved earlier in this algorithm.""",
    """              1. If _in_.[[ImportName]] is *"\*"*, then""",
    """                1. Let _namespace_ be ? GetModuleNamespace(_importedModule_).""",
    """                1. Perform ! _env_.CreateImmutableBinding(_in_.[[LocalName]], *true*).""",
    """                1. Call _env_.InitializeBinding(_in_.[[LocalName]], _namespace_).""",
    """              1. Else,""",
    """                1. Let _resolution_ be ? _importedModule_.ResolveExport(_in_.[[ImportName]]).""",
    """                1. If _resolution_ is *null* or *"ambiguous"*, throw a *SyntaxError* exception.""",
    """                1. If _resolution_.[[BindingName]] is *"\*namespace\*"*, then""",
    """                  1. Let _namespace_ be ? GetModuleNamespace(_resolution_.[[Module]]).""",
    """                  1. Perform ! _env_.CreateImmutableBinding(_in_.[[LocalName]], *true*).""",
    """                  1. Call _env_.InitializeBinding(_in_.[[LocalName]], _namespace_).""",
    """                1. Else,""",
    """                  1. Call _env_.CreateImportBinding(_in_.[[LocalName]], _resolution_.[[Module]], _resolution_.[[BindingName]]).""",
    """            1. Let _moduleContext_ be a new ECMAScript code execution context.""",
    """            1. Set the Function of _moduleContext_ to *null*.""",
    """            1. Assert: _module_.[[Realm]] is not *undefined*.""",
    """            1. Set the Realm of _moduleContext_ to _module_.[[Realm]].""",
    """            1. Set the ScriptOrModule of _moduleContext_ to _module_.""",
    """            1. Set the VariableEnvironment of _moduleContext_ to _module_.[[Environment]].""",
    """            1. Set the LexicalEnvironment of _moduleContext_ to _module_.[[Environment]].""",
    """            1. Set _module_.[[Context]] to _moduleContext_.""",
    """            1. Push _moduleContext_ onto the execution context stack; _moduleContext_ is now the running execution context.""",
    """            1. Let _code_ be _module_.[[ECMAScriptCode]].""",
    """            1. Let _varDeclarations_ be the VarScopedDeclarations of _code_.""",
    """            1. Let _declaredVarNames_ be a new empty List.""",
    """            1. For each element _d_ of _varDeclarations_, do""",
    """              1. For each element _dn_ of the BoundNames of _d_, do""",
    """                1. If _dn_ is not an element of _declaredVarNames_, then""",
    """                  1. Perform ! _env_.CreateMutableBinding(_dn_, *false*).""",
    """                  1. Call _env_.InitializeBinding(_dn_, *undefined*).""",
    """                  1. Append _dn_ to _declaredVarNames_.""",
    """            1. Let _lexDeclarations_ be the LexicallyScopedDeclarations of _code_.""",
    """            1. For each element _d_ of _lexDeclarations_, do""",
    """              1. For each element _dn_ of the BoundNames of _d_, do""",
    """                1. If IsConstantDeclaration of _d_ is *true*, then""",
    """                  1. Perform ! _env_.CreateImmutableBinding(_dn_, *true*).""",
    """                1. Else,""",
    """                  1. Perform ! _env_.CreateMutableBinding(_dn_, *false*).""",
    """                1. If _d_ is a |FunctionDeclaration|, a |GeneratorDeclaration|, an |AsyncFunctionDeclaration|, or an |AsyncGeneratorDeclaration|, then""",
    """                  1. Let _fo_ be InstantiateFunctionObject of _d_ with argument _env_.""",
    """                  1. Call _env_.InitializeBinding(_dn_, _fo_).""",
    """            1. Remove _moduleContext_ from the execution context stack.""",
    """            1. Return NormalCompletion(~empty~).""",
  )
}
