package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FunctionDeclarationInstantiation` extends Algo {
  val head = NormalHead("FunctionDeclarationInstantiation", List(Param("func", Normal), Param("argumentsList", Normal)))
  val ids = List(
    "sec-functiondeclarationinstantiation",
    "sec-ecmascript-function-objects",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:let calleeContext = CONTEXT
  |  1:let code = func.ECMAScriptCode
  |  2:let strict = func.Strict
  |  3:let formals = func.FormalParameters
  |  4:access __x0__ = (formals "BoundNames")
  |  4:let parameterNames = __x0__
  |  5:app __x1__ = (IsDuplicate parameterNames)
  |  5:if __x1__ let hasDuplicates = true else let hasDuplicates = false
  |  6:access __x2__ = (formals "IsSimpleParameterList")
  |  6:let simpleParameterList = __x2__
  |  7:access __x3__ = (formals "ContainsExpression")
  |  7:let hasParameterExpressions = __x3__
  |  8:access __x4__ = (code "VarDeclaredNames")
  |  8:let varNames = __x4__
  |  9:access __x5__ = (code "VarScopedDeclarations")
  |  9:let varDeclarations = __x5__
  |  10:access __x6__ = (code "LexicallyDeclaredNames")
  |  10:let lexicalNames = __x6__
  |  11:let functionNames = (new [])
  |  12:let functionsToInitialize = (new [])
  |  13:let __x7__ = varDeclarations
  |  13:let __x8__ = __x7__.length
  |  13:while (< 0i __x8__) {
  |    __x8__ = (- __x8__ 1i)
  |    let d = __x7__[__x8__]
  |    14:if (! (|| (|| (is-instance-of d VariableDeclaration) (is-instance-of d ForBinding)) (is-instance-of d BindingIdentifier))) {
  |      15:assert (|| (|| (|| (is-instance-of d FunctionDeclaration) (is-instance-of d GeneratorDeclaration)) (is-instance-of d AsyncFunctionDeclaration)) (is-instance-of d AsyncGeneratorDeclaration))
  |      16:access __x9__ = (d "BoundNames")
  |      16:let fn = __x9__[0i]
  |      17:if (! (contains functionNames fn)) {
  |        18:prepend fn -> functionNames
  |        20:prepend d -> functionsToInitialize
  |      } else 96:{}
  |    } else 96:{}
  |  }
  |  21:let argumentsObjectNeeded = true
  |  27:if (= func.ThisMode CONST_lexical) argumentsObjectNeeded = false else if (contains parameterNames "arguments") argumentsObjectNeeded = false else if (= hasParameterExpressions false) if (|| (contains functionNames "arguments") (contains lexicalNames "arguments")) argumentsObjectNeeded = false else 96:{} else 96:{}
  |  33:if (|| (= strict true) (= hasParameterExpressions false)) {
  |    32:access __x10__ = (calleeContext "LexicalEnvironment")
  |    32:let env = __x10__
  |  } else {
  |    35:access __x11__ = (calleeContext "LexicalEnvironment")
  |    35:let calleeEnv = __x11__
  |    36:app __x12__ = (NewDeclarativeEnvironment calleeEnv)
  |    36:let env = __x12__
  |    37:access __x13__ = (calleeContext "VariableEnvironment")
  |    37:assert (= __x13__ calleeEnv)
  |    38:calleeContext.LexicalEnvironment = env
  |  }
  |  39:let __x14__ = parameterNames
  |  39:let __x15__ = 0i
  |  39:while (< __x15__ __x14__.length) {
  |    let paramName = __x14__[__x15__]
  |    40:app __x16__ = (env.HasBinding env paramName)
  |    40:let alreadyDeclared = __x16__
  |    42:if (= alreadyDeclared false) {
  |      43:app __x17__ = (env.CreateMutableBinding env paramName false)
  |      43:[! __x17__]
  |      44:if (= hasDuplicates true) {
  |        45:app __x18__ = (env.InitializeBinding env paramName undefined)
  |        45:[! __x18__]
  |      } else 96:{}
  |    } else 96:{}
  |    __x15__ = (+ __x15__ 1i)
  |  }
  |  58:if (= argumentsObjectNeeded true) {
  |    49:if (|| (= strict true) (= simpleParameterList false)) {
  |      48:app __x19__ = (CreateUnmappedArgumentsObject argumentsList)
  |      48:let ao = __x19__
  |    } else {
  |      51:app __x20__ = (CreateMappedArgumentsObject func formals argumentsList env)
  |      51:let ao = __x20__
  |    }
  |    54:if (= strict true) {
  |      53:app __x21__ = (env.CreateImmutableBinding env "arguments" false)
  |      53:[! __x21__]
  |    } else {
  |      55:app __x22__ = (env.CreateMutableBinding env "arguments" false)
  |      55:[! __x22__]
  |    }
  |    56:app __x23__ = (env.InitializeBinding env "arguments" ao)
  |    56:__x23__
  |    57:let __x24__ = (copy-obj parameterNames)
  |    57:append "arguments" -> __x24__
  |    57:let parameterBindings = __x24__
  |  } else let parameterBindings = parameterNames
  |  60:app __x25__ = (CreateListIteratorRecord argumentsList)
  |  60:let iteratorRecord = __x25__
  |  63:if (= hasDuplicates true) {
  |    62:access __x26__ = (formals "IteratorBindingInitialization" iteratorRecord undefined)
  |    62:[? __x26__]
  |  } else {
  |    64:access __x27__ = (formals "IteratorBindingInitialization" iteratorRecord env)
  |    64:[? __x27__]
  |  }
  |  74:if (= hasParameterExpressions false) {
  |    67:let instantiatedVarNames = (copy-obj parameterBindings)
  |    68:let __x28__ = varNames
  |    68:let __x29__ = 0i
  |    68:while (< __x29__ __x28__.length) {
  |      let n = __x28__[__x29__]
  |      69:if (! (contains instantiatedVarNames n)) {
  |        70:append n -> instantiatedVarNames
  |        71:app __x30__ = (env.CreateMutableBinding env n false)
  |        71:[! __x30__]
  |        72:app __x31__ = (env.InitializeBinding env n undefined)
  |        72:__x31__
  |      } else 96:{}
  |      __x29__ = (+ __x29__ 1i)
  |    }
  |    73:let varEnv = env
  |  } else {
  |    76:app __x32__ = (NewDeclarativeEnvironment env)
  |    76:let varEnv = __x32__
  |    77:calleeContext.VariableEnvironment = varEnv
  |    78:let instantiatedVarNames = (new [])
  |    79:let __x33__ = varNames
  |    79:let __x34__ = 0i
  |    79:while (< __x34__ __x33__.length) {
  |      let n = __x33__[__x34__]
  |      80:if (! (contains instantiatedVarNames n)) {
  |        81:append n -> instantiatedVarNames
  |        82:app __x35__ = (varEnv.CreateMutableBinding varEnv n false)
  |        82:[! __x35__]
  |        84:if (|| (! (contains parameterBindings n)) (contains functionNames n)) let initialValue = undefined else {
  |          85:app __x36__ = (env.GetBindingValue env n false)
  |          85:let initialValue = [! __x36__]
  |        }
  |        86:app __x37__ = (varEnv.InitializeBinding varEnv n initialValue)
  |        86:__x37__
  |      } else 96:{}
  |      __x34__ = (+ __x34__ 1i)
  |    }
  |  }
  |  92:if (= strict false) {
  |    90:app __x38__ = (NewDeclarativeEnvironment varEnv)
  |    90:let lexEnv = __x38__
  |  } else let lexEnv = varEnv
  |  93:calleeContext.LexicalEnvironment = lexEnv
  |  94:access __x39__ = (code "LexicallyScopedDeclarations")
  |  94:let lexDeclarations = __x39__
  |  95:let __x40__ = lexDeclarations
  |  95:let __x41__ = 0i
  |  95:while (< __x41__ __x40__.length) {
  |    let d = __x40__[__x41__]
  |    97:access __x42__ = (d "BoundNames")
  |    97:let __x43__ = __x42__
  |    97:let __x44__ = 0i
  |    97:while (< __x44__ __x43__.length) {
  |      let dn = __x43__[__x44__]
  |      100:access __x45__ = (d "IsConstantDeclaration")
  |      100:if (= __x45__ true) {
  |        99:app __x46__ = (lexEnv.CreateImmutableBinding lexEnv dn true)
  |        99:[! __x46__]
  |      } else {
  |        101:app __x47__ = (lexEnv.CreateMutableBinding lexEnv dn false)
  |        101:[! __x47__]
  |      }
  |      __x44__ = (+ __x44__ 1i)
  |    }
  |    __x41__ = (+ __x41__ 1i)
  |  }
  |  102:let __x48__ = functionsToInitialize
  |  102:let __x49__ = 0i
  |  102:while (< __x49__ __x48__.length) {
  |    let f = __x48__[__x49__]
  |    103:access __x50__ = (f "BoundNames")
  |    103:let fn = __x50__[0i]
  |    104:access __x51__ = (f "InstantiateFunctionObject" lexEnv)
  |    104:let fo = __x51__
  |    105:app __x52__ = (varEnv.SetMutableBinding varEnv fn fo false)
  |    105:[! __x52__]
  |    __x49__ = (+ __x49__ 1i)
  |  }
  |  106:return CONST_empty
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _calleeContext_ be the running execution context.""",
    """        1. Let _code_ be _func_.[[ECMAScriptCode]].""",
    """        1. Let _strict_ be _func_.[[Strict]].""",
    """        1. Let _formals_ be _func_.[[FormalParameters]].""",
    """        1. Let _parameterNames_ be the BoundNames of _formals_.""",
    """        1. If _parameterNames_ has any duplicate entries, let _hasDuplicates_ be *true*. Otherwise, let _hasDuplicates_ be *false*.""",
    """        1. Let _simpleParameterList_ be IsSimpleParameterList of _formals_.""",
    """        1. Let _hasParameterExpressions_ be ContainsExpression of _formals_.""",
    """        1. Let _varNames_ be the VarDeclaredNames of _code_.""",
    """        1. Let _varDeclarations_ be the VarScopedDeclarations of _code_.""",
    """        1. Let _lexicalNames_ be the LexicallyDeclaredNames of _code_.""",
    """        1. Let _functionNames_ be a new empty List.""",
    """        1. Let _functionsToInitialize_ be a new empty List.""",
    """        1. For each element _d_ of _varDeclarations_, in reverse List order, do""",
    """          1. If _d_ is neither a |VariableDeclaration| nor a |ForBinding| nor a |BindingIdentifier|, then""",
    """            1. Assert: _d_ is either a |FunctionDeclaration|, a |GeneratorDeclaration|, an |AsyncFunctionDeclaration|, or an |AsyncGeneratorDeclaration|.""",
    """            1. Let _fn_ be the sole element of the BoundNames of _d_.""",
    """            1. If _fn_ is not an element of _functionNames_, then""",
    """              1. Insert _fn_ as the first element of _functionNames_.""",
    """              1. NOTE: If there are multiple function declarations for the same name, the last declaration is used.""",
    """              1. Insert _d_ as the first element of _functionsToInitialize_.""",
    """        1. Let _argumentsObjectNeeded_ be *true*.""",
    """        1. If _func_.[[ThisMode]] is ~lexical~, then""",
    """          1. NOTE: Arrow functions never have an arguments objects.""",
    """          1. Set _argumentsObjectNeeded_ to *false*.""",
    """        1. Else if *"arguments"* is an element of _parameterNames_, then""",
    """          1. Set _argumentsObjectNeeded_ to *false*.""",
    """        1. Else if _hasParameterExpressions_ is *false*, then""",
    """          1. If *"arguments"* is an element of _functionNames_ or if *"arguments"* is an element of _lexicalNames_, then""",
    """            1. Set _argumentsObjectNeeded_ to *false*.""",
    """        1. If _strict_ is *true* or if _hasParameterExpressions_ is *false*, then""",
    """          1. NOTE: Only a single Environment Record is needed for the parameters and top-level vars.""",
    """          1. Let _env_ be the LexicalEnvironment of _calleeContext_.""",
    """        1. Else,""",
    """          1. NOTE: A separate Environment Record is needed to ensure that bindings created by direct eval calls in the formal parameter list are outside the environment where parameters are declared.""",
    """          1. Let _calleeEnv_ be the LexicalEnvironment of _calleeContext_.""",
    """          1. Let _env_ be NewDeclarativeEnvironment(_calleeEnv_).""",
    """          1. Assert: The VariableEnvironment of _calleeContext_ is _calleeEnv_.""",
    """          1. Set the LexicalEnvironment of _calleeContext_ to _env_.""",
    """        1. For each String _paramName_ of _parameterNames_, do""",
    """          1. Let _alreadyDeclared_ be _env_.HasBinding(_paramName_).""",
    """          1. NOTE: Early errors ensure that duplicate parameter names can only occur in non-strict functions that do not have parameter default values or rest parameters.""",
    """          1. If _alreadyDeclared_ is *false*, then""",
    """            1. Perform ! _env_.CreateMutableBinding(_paramName_, *false*).""",
    """            1. If _hasDuplicates_ is *true*, then""",
    """              1. Perform ! _env_.InitializeBinding(_paramName_, *undefined*).""",
    """        1. If _argumentsObjectNeeded_ is *true*, then""",
    """          1. If _strict_ is *true* or if _simpleParameterList_ is *false*, then""",
    """            1. Let _ao_ be CreateUnmappedArgumentsObject(_argumentsList_).""",
    """          1. Else,""",
    """            1. NOTE: A mapped argument object is only provided for non-strict functions that don't have a rest parameter, any parameter default value initializers, or any destructured parameters.""",
    """            1. Let _ao_ be CreateMappedArgumentsObject(_func_, _formals_, _argumentsList_, _env_).""",
    """          1. If _strict_ is *true*, then""",
    """            1. Perform ! _env_.CreateImmutableBinding(*"arguments"*, *false*).""",
    """          1. Else,""",
    """            1. Perform ! _env_.CreateMutableBinding(*"arguments"*, *false*).""",
    """          1. Call _env_.InitializeBinding(*"arguments"*, _ao_).""",
    """          1. Let _parameterBindings_ be a List whose elements are the elements of _parameterNames_, followed by *"arguments"*.""",
    """        1. Else,""",
    """          1. Let _parameterBindings_ be _parameterNames_.""",
    """        1. Let _iteratorRecord_ be CreateListIteratorRecord(_argumentsList_).""",
    """        1. If _hasDuplicates_ is *true*, then""",
    """          1. Perform ? IteratorBindingInitialization for _formals_ with _iteratorRecord_ and *undefined* as arguments.""",
    """        1. Else,""",
    """          1. Perform ? IteratorBindingInitialization for _formals_ with _iteratorRecord_ and _env_ as arguments.""",
    """        1. If _hasParameterExpressions_ is *false*, then""",
    """          1. NOTE: Only a single Environment Record is needed for the parameters and top-level vars.""",
    """          1. Let _instantiatedVarNames_ be a copy of the List _parameterBindings_.""",
    """          1. For each element _n_ of _varNames_, do""",
    """            1. If _n_ is not an element of _instantiatedVarNames_, then""",
    """              1. Append _n_ to _instantiatedVarNames_.""",
    """              1. Perform ! _env_.CreateMutableBinding(_n_, *false*).""",
    """              1. Call _env_.InitializeBinding(_n_, *undefined*).""",
    """          1. Let _varEnv_ be _env_.""",
    """        1. Else,""",
    """          1. NOTE: A separate Environment Record is needed to ensure that closures created by expressions in the formal parameter list do not have visibility of declarations in the function body.""",
    """          1. Let _varEnv_ be NewDeclarativeEnvironment(_env_).""",
    """          1. Set the VariableEnvironment of _calleeContext_ to _varEnv_.""",
    """          1. Let _instantiatedVarNames_ be a new empty List.""",
    """          1. For each element _n_ of _varNames_, do""",
    """            1. If _n_ is not an element of _instantiatedVarNames_, then""",
    """              1. Append _n_ to _instantiatedVarNames_.""",
    """              1. Perform ! _varEnv_.CreateMutableBinding(_n_, *false*).""",
    """              1. If _n_ is not an element of _parameterBindings_ or if _n_ is an element of _functionNames_, let _initialValue_ be *undefined*.""",
    """              1. Else,""",
    """                1. Let _initialValue_ be ! _env_.GetBindingValue(_n_, *false*).""",
    """              1. Call _varEnv_.InitializeBinding(_n_, _initialValue_).""",
    """              1. NOTE: A var with the same name as a formal parameter initially has the same value as the corresponding initialized parameter.""",
    """        1. [id="step-functiondeclarationinstantiation-web-compat-insertion-point"] NOTE: Annex <emu-xref href="#sec-web-compat-functiondeclarationinstantiation"></emu-xref> adds additional steps at this point.""",
    """        1. If _strict_ is *false*, then""",
    """          1. Let _lexEnv_ be NewDeclarativeEnvironment(_varEnv_).""",
    """          1. NOTE: Non-strict functions use a separate Environment Record for top-level lexical declarations so that a direct eval can determine whether any var scoped declarations introduced by the eval code conflict with pre-existing top-level lexically scoped declarations. This is not needed for strict functions because a strict direct eval always places all declarations into a new Environment Record.""",
    """        1. Else, let _lexEnv_ be _varEnv_.""",
    """        1. Set the LexicalEnvironment of _calleeContext_ to _lexEnv_.""",
    """        1. Let _lexDeclarations_ be the LexicallyScopedDeclarations of _code_.""",
    """        1. For each element _d_ of _lexDeclarations_, do""",
    """          1. NOTE: A lexically declared name cannot be the same as a function/generator declaration, formal parameter, or a var name. Lexically declared names are only instantiated here but not initialized.""",
    """          1. For each element _dn_ of the BoundNames of _d_, do""",
    """            1. If IsConstantDeclaration of _d_ is *true*, then""",
    """              1. Perform ! _lexEnv_.CreateImmutableBinding(_dn_, *true*).""",
    """            1. Else,""",
    """              1. Perform ! _lexEnv_.CreateMutableBinding(_dn_, *false*).""",
    """        1. For each Parse Node _f_ of _functionsToInitialize_, do""",
    """          1. Let _fn_ be the sole element of the BoundNames of _f_.""",
    """          1. Let _fo_ be InstantiateFunctionObject of _f_ with argument _lexEnv_.""",
    """          1. Perform ! _varEnv_.SetMutableBinding(_fn_, _fo_, *false*).""",
    """        1. Return NormalCompletion(~empty~).""",
  )
}
