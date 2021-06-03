package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::EvalDeclarationInstantiation` extends Algo {
  val head = NormalHead("EvalDeclarationInstantiation", List(Param("body", Normal), Param("varEnv", Normal), Param("lexEnv", Normal), Param("strict", Normal)))
  val ids = List(
    "sec-evaldeclarationinstantiation",
    "sec-eval-x",
    "sec-function-properties-of-the-global-object",
    "sec-global-object",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (body "VarDeclaredNames")
  |  0:let varNames = __x0__
  |  1:access __x1__ = (body "VarScopedDeclarations")
  |  1:let varDeclarations = __x1__
  |  2:if (= strict false) {
  |    3:if (is-instance-of varEnv GlobalEnvironmentRecord) {
  |      4:let __x2__ = varNames
  |      4:let __x3__ = 0i
  |      4:while (< __x3__ __x2__.length) {
  |        let name = __x2__[__x3__]
  |        5:app __x4__ = (varEnv.HasLexicalDeclaration varEnv name)
  |        5:if (= __x4__ true) throw SyntaxError else 71:{}
  |        __x3__ = (+ __x3__ 1i)
  |      }
  |    } else 71:{}
  |    7:let thisEnv = lexEnv
  |    9:while (! (= thisEnv varEnv)) {
  |      10:if (! (is-instance-of thisEnv ObjectEnvironmentRecord)) {
  |        12:let __x5__ = varNames
  |        12:let __x6__ = 0i
  |        12:while (< __x6__ __x5__.length) {
  |          let name = __x5__[__x6__]
  |          13:app __x7__ = (thisEnv.HasBinding thisEnv name)
  |          13:if (= __x7__ true) throw SyntaxError else 71:{}
  |          __x6__ = (+ __x6__ 1i)
  |        }
  |      } else 71:{}
  |      17:thisEnv = thisEnv.OuterEnv
  |    }
  |  } else 71:{}
  |  18:let functionsToInitialize = (new [])
  |  19:let declaredFunctionNames = (new [])
  |  20:let __x8__ = varDeclarations
  |  20:let __x9__ = __x8__.length
  |  20:while (< 0i __x9__) {
  |    __x9__ = (- __x9__ 1i)
  |    let d = __x8__[__x9__]
  |    21:if (! (|| (|| (is-instance-of d VariableDeclaration) (is-instance-of d ForBinding)) (is-instance-of d BindingIdentifier))) {
  |      22:assert (|| (|| (|| (is-instance-of d FunctionDeclaration) (is-instance-of d GeneratorDeclaration)) (is-instance-of d AsyncFunctionDeclaration)) (is-instance-of d AsyncGeneratorDeclaration))
  |      24:access __x10__ = (d "BoundNames")
  |      24:let fn = __x10__[0i]
  |      25:if (! (contains declaredFunctionNames fn)) {
  |        26:if (is-instance-of varEnv GlobalEnvironmentRecord) {
  |          27:app __x11__ = (varEnv.CanDeclareGlobalFunction varEnv fn)
  |          27:let fnDefinable = [? __x11__]
  |          28:if (= fnDefinable false) throw TypeError else 71:{}
  |        } else 71:{}
  |        29:append fn -> declaredFunctionNames
  |        30:prepend d -> functionsToInitialize
  |      } else 71:{}
  |    } else 71:{}
  |  }
  |  32:let declaredVarNames = (new [])
  |  33:let __x12__ = varDeclarations
  |  33:let __x13__ = 0i
  |  33:while (< __x13__ __x12__.length) {
  |    let d = __x12__[__x13__]
  |    34:if (|| (|| (is-instance-of d VariableDeclaration) (is-instance-of d ForBinding)) (is-instance-of d BindingIdentifier)) {
  |      35:access __x14__ = (d "BoundNames")
  |      35:let __x15__ = __x14__
  |      35:let __x16__ = 0i
  |      35:while (< __x16__ __x15__.length) {
  |        let vn = __x15__[__x16__]
  |        36:if (! (contains declaredFunctionNames vn)) {
  |          37:if (is-instance-of varEnv GlobalEnvironmentRecord) {
  |            38:app __x17__ = (varEnv.CanDeclareGlobalVar varEnv vn)
  |            38:let vnDefinable = [? __x17__]
  |            39:if (= vnDefinable false) throw TypeError else 71:{}
  |          } else 71:{}
  |          40:if (! (contains declaredVarNames vn)) append vn -> declaredVarNames else 71:{}
  |        } else 71:{}
  |        __x16__ = (+ __x16__ 1i)
  |      }
  |    } else 71:{}
  |    __x13__ = (+ __x13__ 1i)
  |  }
  |  43:access __x18__ = (body "LexicallyScopedDeclarations")
  |  43:let lexDeclarations = __x18__
  |  44:let __x19__ = lexDeclarations
  |  44:let __x20__ = 0i
  |  44:while (< __x20__ __x19__.length) {
  |    let d = __x19__[__x20__]
  |    46:access __x21__ = (d "BoundNames")
  |    46:let __x22__ = __x21__
  |    46:let __x23__ = 0i
  |    46:while (< __x23__ __x22__.length) {
  |      let dn = __x22__[__x23__]
  |      49:access __x24__ = (d "IsConstantDeclaration")
  |      49:if (= __x24__ true) {
  |        48:app __x25__ = (lexEnv.CreateImmutableBinding lexEnv dn true)
  |        48:[? __x25__]
  |      } else {
  |        50:app __x26__ = (lexEnv.CreateMutableBinding lexEnv dn false)
  |        50:[? __x26__]
  |      }
  |      __x23__ = (+ __x23__ 1i)
  |    }
  |    __x20__ = (+ __x20__ 1i)
  |  }
  |  51:let __x27__ = functionsToInitialize
  |  51:let __x28__ = 0i
  |  51:while (< __x28__ __x27__.length) {
  |    let f = __x27__[__x28__]
  |    52:access __x29__ = (f "BoundNames")
  |    52:let fn = __x29__[0i]
  |    53:access __x30__ = (f "InstantiateFunctionObject" lexEnv)
  |    53:let fo = __x30__
  |    56:if (is-instance-of varEnv GlobalEnvironmentRecord) {
  |      55:app __x31__ = (varEnv.CreateGlobalFunctionBinding varEnv fn fo true)
  |      55:[? __x31__]
  |    } else {
  |      57:app __x32__ = (varEnv.HasBinding varEnv fn)
  |      57:let bindingExists = __x32__
  |      62:if (= bindingExists false) {
  |        59:app __x33__ = (varEnv.CreateMutableBinding varEnv fn true)
  |        59:let status = [! __x33__]
  |        61:app __x34__ = (varEnv.InitializeBinding varEnv fn fo)
  |        61:[! __x34__]
  |      } else {
  |        63:app __x35__ = (varEnv.SetMutableBinding varEnv fn fo false)
  |        63:[! __x35__]
  |      }
  |    }
  |    __x28__ = (+ __x28__ 1i)
  |  }
  |  64:let __x36__ = declaredVarNames
  |  64:let __x37__ = 0i
  |  64:while (< __x37__ __x36__.length) {
  |    let vn = __x36__[__x37__]
  |    67:if (is-instance-of varEnv GlobalEnvironmentRecord) {
  |      66:app __x38__ = (varEnv.CreateGlobalVarBinding varEnv vn true)
  |      66:[? __x38__]
  |    } else {
  |      68:app __x39__ = (varEnv.HasBinding varEnv vn)
  |      68:let bindingExists = __x39__
  |      69:if (= bindingExists false) {
  |        70:app __x40__ = (varEnv.CreateMutableBinding varEnv vn true)
  |        70:let status = [! __x40__]
  |        72:app __x41__ = (varEnv.InitializeBinding varEnv vn undefined)
  |        72:[! __x41__]
  |      } else 71:{}
  |    }
  |    __x37__ = (+ __x37__ 1i)
  |  }
  |  73:return CONST_empty
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _varNames_ be the VarDeclaredNames of _body_.""",
    """          1. Let _varDeclarations_ be the VarScopedDeclarations of _body_.""",
    """          1. If _strict_ is *false*, then""",
    """            1. If _varEnv_ is a global Environment Record, then""",
    """              1. For each element _name_ of _varNames_, do""",
    """                1. If _varEnv_.HasLexicalDeclaration(_name_) is *true*, throw a *SyntaxError* exception.""",
    """                1. NOTE: `eval` will not create a global var declaration that would be shadowed by a global lexical declaration.""",
    """            1. Let _thisEnv_ be _lexEnv_.""",
    """            1. Assert: The following loop will terminate.""",
    """            1. Repeat, while _thisEnv_ is not the same as _varEnv_,""",
    """              1. If _thisEnv_ is not an object Environment Record, then""",
    """                1. NOTE: The environment of with statements cannot contain any lexical declaration so it doesn't need to be checked for var/let hoisting conflicts.""",
    """                1. For each element _name_ of _varNames_, do""",
    """                  1. If _thisEnv_.HasBinding(_name_) is *true*, then""",
    """                    1. [id="step-evaldeclarationinstantiation-throw-duplicate-binding"] Throw a *SyntaxError* exception.""",
    """                    1. NOTE: Annex <emu-xref href="#sec-variablestatements-in-catch-blocks"></emu-xref> defines alternate semantics for the above step.""",
    """                  1. NOTE: A direct eval will not hoist var declaration over a like-named lexical declaration.""",
    """              1. Set _thisEnv_ to _thisEnv_.[[OuterEnv]].""",
    """          1. Let _functionsToInitialize_ be a new empty List.""",
    """          1. Let _declaredFunctionNames_ be a new empty List.""",
    """          1. For each element _d_ of _varDeclarations_, in reverse List order, do""",
    """            1. If _d_ is neither a |VariableDeclaration| nor a |ForBinding| nor a |BindingIdentifier|, then""",
    """              1. Assert: _d_ is either a |FunctionDeclaration|, a |GeneratorDeclaration|, an |AsyncFunctionDeclaration|, or an |AsyncGeneratorDeclaration|.""",
    """              1. NOTE: If there are multiple function declarations for the same name, the last declaration is used.""",
    """              1. Let _fn_ be the sole element of the BoundNames of _d_.""",
    """              1. If _fn_ is not an element of _declaredFunctionNames_, then""",
    """                1. If _varEnv_ is a global Environment Record, then""",
    """                  1. Let _fnDefinable_ be ? _varEnv_.CanDeclareGlobalFunction(_fn_).""",
    """                  1. If _fnDefinable_ is *false*, throw a *TypeError* exception.""",
    """                1. Append _fn_ to _declaredFunctionNames_.""",
    """                1. Insert _d_ as the first element of _functionsToInitialize_.""",
    """          1. [id="step-evaldeclarationinstantiation-web-compat-insertion-point"] NOTE: Annex <emu-xref href="#sec-web-compat-evaldeclarationinstantiation"></emu-xref> adds additional steps at this point.""",
    """          1. Let _declaredVarNames_ be a new empty List.""",
    """          1. For each element _d_ of _varDeclarations_, do""",
    """            1. If _d_ is a |VariableDeclaration|, a |ForBinding|, or a |BindingIdentifier|, then""",
    """              1. For each String _vn_ of the BoundNames of _d_, do""",
    """                1. If _vn_ is not an element of _declaredFunctionNames_, then""",
    """                  1. If _varEnv_ is a global Environment Record, then""",
    """                    1. Let _vnDefinable_ be ? _varEnv_.CanDeclareGlobalVar(_vn_).""",
    """                    1. If _vnDefinable_ is *false*, throw a *TypeError* exception.""",
    """                  1. If _vn_ is not an element of _declaredVarNames_, then""",
    """                    1. Append _vn_ to _declaredVarNames_.""",
    """          1. [id="step-evaldeclarationinstantiation-post-validation"] NOTE: No abnormal terminations occur after this algorithm step unless _varEnv_ is a global Environment Record and the global object is a Proxy exotic object.""",
    """          1. Let _lexDeclarations_ be the LexicallyScopedDeclarations of _body_.""",
    """          1. For each element _d_ of _lexDeclarations_, do""",
    """            1. NOTE: Lexically declared names are only instantiated here but not initialized.""",
    """            1. For each element _dn_ of the BoundNames of _d_, do""",
    """              1. If IsConstantDeclaration of _d_ is *true*, then""",
    """                1. Perform ? _lexEnv_.CreateImmutableBinding(_dn_, *true*).""",
    """              1. Else,""",
    """                1. Perform ? _lexEnv_.CreateMutableBinding(_dn_, *false*).""",
    """          1. For each Parse Node _f_ of _functionsToInitialize_, do""",
    """            1. Let _fn_ be the sole element of the BoundNames of _f_.""",
    """            1. Let _fo_ be InstantiateFunctionObject of _f_ with argument _lexEnv_.""",
    """            1. If _varEnv_ is a global Environment Record, then""",
    """              1. Perform ? _varEnv_.CreateGlobalFunctionBinding(_fn_, _fo_, *true*).""",
    """            1. Else,""",
    """              1. Let _bindingExists_ be _varEnv_.HasBinding(_fn_).""",
    """              1. If _bindingExists_ is *false*, then""",
    """                1. Let _status_ be ! _varEnv_.CreateMutableBinding(_fn_, *true*).""",
    """                1. Assert: _status_ is not an abrupt completion because of validation preceding step <emu-xref href="#step-evaldeclarationinstantiation-post-validation"></emu-xref>.""",
    """                1. Perform ! _varEnv_.InitializeBinding(_fn_, _fo_).""",
    """              1. Else,""",
    """                1. Perform ! _varEnv_.SetMutableBinding(_fn_, _fo_, *false*).""",
    """          1. For each String _vn_ of _declaredVarNames_, do""",
    """            1. If _varEnv_ is a global Environment Record, then""",
    """              1. Perform ? _varEnv_.CreateGlobalVarBinding(_vn_, *true*).""",
    """            1. Else,""",
    """              1. Let _bindingExists_ be _varEnv_.HasBinding(_vn_).""",
    """              1. If _bindingExists_ is *false*, then""",
    """                1. Let _status_ be ! _varEnv_.CreateMutableBinding(_vn_, *true*).""",
    """                1. Assert: _status_ is not an abrupt completion because of validation preceding step <emu-xref href="#step-evaldeclarationinstantiation-post-validation"></emu-xref>.""",
    """                1. Perform ! _varEnv_.InitializeBinding(_vn_, *undefined*).""",
    """          1. Return NormalCompletion(~empty~).""",
  )
}
