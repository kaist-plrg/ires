package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GlobalDeclarationInstantiation` extends Algo {
  val head = NormalHead("GlobalDeclarationInstantiation", List(Param("script", Normal), Param("env", Normal)))
  val ids = List(
    "sec-globaldeclarationinstantiation",
    "sec-scripts",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:assert (is-instance-of env GlobalEnvironmentRecord)
  |  1:access __x0__ = (script "LexicallyDeclaredNames")
  |  1:let lexNames = __x0__
  |  2:access __x1__ = (script "VarDeclaredNames")
  |  2:let varNames = __x1__
  |  3:let __x2__ = lexNames
  |  3:let __x3__ = 0i
  |  3:while (< __x3__ __x2__.length) {
  |    let name = __x2__[__x3__]
  |    4:app __x4__ = (env.HasVarDeclaration env name)
  |    4:if (= __x4__ true) throw SyntaxError else 36:{}
  |    5:app __x5__ = (env.HasLexicalDeclaration env name)
  |    5:if (= __x5__ true) throw SyntaxError else 36:{}
  |    6:app __x6__ = (env.HasRestrictedGlobalProperty env name)
  |    6:let hasRestrictedGlobal = [? __x6__]
  |    7:if (= hasRestrictedGlobal true) throw SyntaxError else 36:{}
  |    __x3__ = (+ __x3__ 1i)
  |  }
  |  8:let __x7__ = varNames
  |  8:let __x8__ = 0i
  |  8:while (< __x8__ __x7__.length) {
  |    let name = __x7__[__x8__]
  |    9:app __x9__ = (env.HasLexicalDeclaration env name)
  |    9:if (= __x9__ true) throw SyntaxError else 36:{}
  |    __x8__ = (+ __x8__ 1i)
  |  }
  |  10:access __x10__ = (script "VarScopedDeclarations")
  |  10:let varDeclarations = __x10__
  |  11:let functionsToInitialize = (new [])
  |  12:let declaredFunctionNames = (new [])
  |  13:let __x11__ = varDeclarations
  |  13:let __x12__ = __x11__.length
  |  13:while (< 0i __x12__) {
  |    __x12__ = (- __x12__ 1i)
  |    let d = __x11__[__x12__]
  |    14:if (! (|| (|| (is-instance-of d VariableDeclaration) (is-instance-of d ForBinding)) (is-instance-of d BindingIdentifier))) {
  |      15:assert (|| (|| (|| (is-instance-of d FunctionDeclaration) (is-instance-of d GeneratorDeclaration)) (is-instance-of d AsyncFunctionDeclaration)) (is-instance-of d AsyncGeneratorDeclaration))
  |      17:access __x13__ = (d "BoundNames")
  |      17:let fn = __x13__[0i]
  |      18:if (! (contains declaredFunctionNames fn)) {
  |        19:app __x14__ = (env.CanDeclareGlobalFunction env fn)
  |        19:let fnDefinable = [? __x14__]
  |        20:if (= fnDefinable false) throw TypeError else 36:{}
  |        21:append fn -> declaredFunctionNames
  |        22:prepend d -> functionsToInitialize
  |      } else 36:{}
  |    } else 36:{}
  |  }
  |  23:let declaredVarNames = (new [])
  |  24:let __x15__ = varDeclarations
  |  24:let __x16__ = 0i
  |  24:while (< __x16__ __x15__.length) {
  |    let d = __x15__[__x16__]
  |    25:if (|| (|| (is-instance-of d VariableDeclaration) (is-instance-of d ForBinding)) (is-instance-of d BindingIdentifier)) {
  |      26:access __x17__ = (d "BoundNames")
  |      26:let __x18__ = __x17__
  |      26:let __x19__ = 0i
  |      26:while (< __x19__ __x18__.length) {
  |        let vn = __x18__[__x19__]
  |        27:if (! (contains declaredFunctionNames vn)) {
  |          28:app __x20__ = (env.CanDeclareGlobalVar env vn)
  |          28:let vnDefinable = [? __x20__]
  |          29:if (= vnDefinable false) throw TypeError else 36:{}
  |          30:if (! (contains declaredVarNames vn)) append vn -> declaredVarNames else 36:{}
  |        } else 36:{}
  |        __x19__ = (+ __x19__ 1i)
  |      }
  |    } else 36:{}
  |    __x16__ = (+ __x16__ 1i)
  |  }
  |  34:access __x21__ = (script "LexicallyScopedDeclarations")
  |  34:let lexDeclarations = __x21__
  |  35:let __x22__ = lexDeclarations
  |  35:let __x23__ = 0i
  |  35:while (< __x23__ __x22__.length) {
  |    let d = __x22__[__x23__]
  |    37:access __x24__ = (d "BoundNames")
  |    37:let __x25__ = __x24__
  |    37:let __x26__ = 0i
  |    37:while (< __x26__ __x25__.length) {
  |      let dn = __x25__[__x26__]
  |      40:access __x27__ = (d "IsConstantDeclaration")
  |      40:if (= __x27__ true) {
  |        39:app __x28__ = (env.CreateImmutableBinding env dn true)
  |        39:[? __x28__]
  |      } else {
  |        41:app __x29__ = (env.CreateMutableBinding env dn false)
  |        41:[? __x29__]
  |      }
  |      __x26__ = (+ __x26__ 1i)
  |    }
  |    __x23__ = (+ __x23__ 1i)
  |  }
  |  42:let __x30__ = functionsToInitialize
  |  42:let __x31__ = 0i
  |  42:while (< __x31__ __x30__.length) {
  |    let f = __x30__[__x31__]
  |    43:access __x32__ = (f "BoundNames")
  |    43:let fn = __x32__[0i]
  |    44:access __x33__ = (f "InstantiateFunctionObject" env)
  |    44:let fo = __x33__
  |    45:app __x34__ = (env.CreateGlobalFunctionBinding env fn fo false)
  |    45:[? __x34__]
  |    __x31__ = (+ __x31__ 1i)
  |  }
  |  46:let __x35__ = declaredVarNames
  |  46:let __x36__ = 0i
  |  46:while (< __x36__ __x35__.length) {
  |    let vn = __x35__[__x36__]
  |    47:app __x37__ = (env.CreateGlobalVarBinding env vn false)
  |    47:[? __x37__]
  |    __x36__ = (+ __x36__ 1i)
  |  }
  |  48:return CONST_empty
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: _env_ is a global Environment Record.""",
    """        1. Let _lexNames_ be the LexicallyDeclaredNames of _script_.""",
    """        1. Let _varNames_ be the VarDeclaredNames of _script_.""",
    """        1. For each element _name_ of _lexNames_, do""",
    """          1. If _env_.HasVarDeclaration(_name_) is *true*, throw a *SyntaxError* exception.""",
    """          1. If _env_.HasLexicalDeclaration(_name_) is *true*, throw a *SyntaxError* exception.""",
    """          1. Let _hasRestrictedGlobal_ be ? _env_.HasRestrictedGlobalProperty(_name_).""",
    """          1. If _hasRestrictedGlobal_ is *true*, throw a *SyntaxError* exception.""",
    """        1. For each element _name_ of _varNames_, do""",
    """          1. If _env_.HasLexicalDeclaration(_name_) is *true*, throw a *SyntaxError* exception.""",
    """        1. Let _varDeclarations_ be the VarScopedDeclarations of _script_.""",
    """        1. Let _functionsToInitialize_ be a new empty List.""",
    """        1. Let _declaredFunctionNames_ be a new empty List.""",
    """        1. For each element _d_ of _varDeclarations_, in reverse List order, do""",
    """          1. If _d_ is neither a |VariableDeclaration| nor a |ForBinding| nor a |BindingIdentifier|, then""",
    """            1. Assert: _d_ is either a |FunctionDeclaration|, a |GeneratorDeclaration|, an |AsyncFunctionDeclaration|, or an |AsyncGeneratorDeclaration|.""",
    """            1. NOTE: If there are multiple function declarations for the same name, the last declaration is used.""",
    """            1. Let _fn_ be the sole element of the BoundNames of _d_.""",
    """            1. If _fn_ is not an element of _declaredFunctionNames_, then""",
    """              1. Let _fnDefinable_ be ? _env_.CanDeclareGlobalFunction(_fn_).""",
    """              1. If _fnDefinable_ is *false*, throw a *TypeError* exception.""",
    """              1. Append _fn_ to _declaredFunctionNames_.""",
    """              1. Insert _d_ as the first element of _functionsToInitialize_.""",
    """        1. Let _declaredVarNames_ be a new empty List.""",
    """        1. For each element _d_ of _varDeclarations_, do""",
    """          1. If _d_ is a |VariableDeclaration|, a |ForBinding|, or a |BindingIdentifier|, then""",
    """            1. For each String _vn_ of the BoundNames of _d_, do""",
    """              1. If _vn_ is not an element of _declaredFunctionNames_, then""",
    """                1. Let _vnDefinable_ be ? _env_.CanDeclareGlobalVar(_vn_).""",
    """                1. If _vnDefinable_ is *false*, throw a *TypeError* exception.""",
    """                1. If _vn_ is not an element of _declaredVarNames_, then""",
    """                  1. Append _vn_ to _declaredVarNames_.""",
    """        1. NOTE: No abnormal terminations occur after this algorithm step if the global object is an ordinary object. However, if the global object is a Proxy exotic object it may exhibit behaviours that cause abnormal terminations in some of the following steps.""",
    """        1. [id="step-globaldeclarationinstantiation-web-compat-insertion-point"] NOTE: Annex <emu-xref href="#sec-web-compat-globaldeclarationinstantiation"></emu-xref> adds additional steps at this point.""",
    """        1. Let _lexDeclarations_ be the LexicallyScopedDeclarations of _script_.""",
    """        1. For each element _d_ of _lexDeclarations_, do""",
    """          1. NOTE: Lexically declared names are only instantiated here but not initialized.""",
    """          1. For each element _dn_ of the BoundNames of _d_, do""",
    """            1. If IsConstantDeclaration of _d_ is *true*, then""",
    """              1. Perform ? _env_.CreateImmutableBinding(_dn_, *true*).""",
    """            1. Else,""",
    """              1. Perform ? _env_.CreateMutableBinding(_dn_, *false*).""",
    """        1. For each Parse Node _f_ of _functionsToInitialize_, do""",
    """          1. Let _fn_ be the sole element of the BoundNames of _f_.""",
    """          1. Let _fo_ be InstantiateFunctionObject of _f_ with argument _env_.""",
    """          1. Perform ? _env_.CreateGlobalFunctionBinding(_fn_, _fo_, *false*).""",
    """        1. For each String _vn_ of _declaredVarNames_, do""",
    """          1. Perform ? _env_.CreateGlobalVarBinding(_vn_, *false*).""",
    """        1. Return NormalCompletion(~empty~).""",
  )
}
