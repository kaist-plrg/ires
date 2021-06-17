package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CreateDynamicFunction` extends Algo {
  val head = NormalHead("CreateDynamicFunction", List(Param("constructor", Normal), Param("newTarget", Normal), Param("kind", Normal), Param("args", Normal)))
  val ids = List(
    "sec-createdynamicfunction",
    "sec-function-p1-p2-pn-body",
    "sec-function-constructor",
    "sec-function-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  1:let callerContext = EXECUTION_STACK[(- EXECUTION_STACK.length 2i)]
  |  2:let callerRealm = callerContext.Realm
  |  3:let calleeRealm = REALM
  |  4:app __x0__ = (HostEnsureCanCompileStrings callerRealm calleeRealm)
  |  4:[? __x0__]
  |  5:if (= newTarget undefined) newTarget = constructor else 73:{}
  |  18:if (= kind CONST_normal) {
  |    7:??? "Let id:{goal} be the grammar symbol | FunctionBody [ ~ Yield , ~ Await ] | ."
  |    8:??? "Let id:{parameterGoal} be the grammar symbol | FormalParameters [ ~ Yield , ~ Await ] | ."
  |    9:let fallbackProto = "%Function.prototype%"
  |  } else if (= kind CONST_generator) {
  |    11:let goal = "GeneratorBody"
  |    12:??? "Let id:{parameterGoal} be the grammar symbol | FormalParameters [ + Yield , ~ Await ] | ."
  |    13:let fallbackProto = "%GeneratorFunction.prototype%"
  |  } else if (= kind CONST_async) {
  |    15:let goal = "AsyncFunctionBody"
  |    16:??? "Let id:{parameterGoal} be the grammar symbol | FormalParameters [ ~ Yield , + Await ] | ."
  |    17:let fallbackProto = "%AsyncFunction.prototype%"
  |  } else {
  |    19:assert (= kind CONST_asyncGenerator)
  |    20:let goal = "AsyncGeneratorBody"
  |    21:??? "Let id:{parameterGoal} be the grammar symbol | FormalParameters [ + Yield , + Await ] | ."
  |    22:let fallbackProto = "%AsyncGeneratorFunction.prototype%"
  |  }
  |  23:let argCount = args.length
  |  24:let P = ""
  |  27:if (== argCount 0i) let bodyArg = "" else if (== argCount 1i) let bodyArg = args[0i] else {
  |    28:assert (< 1i argCount)
  |    29:let firstArg = args[0i]
  |    30:app __x1__ = (ToString firstArg)
  |    30:P = [? __x1__]
  |    31:let k = 1i
  |    32:while (< k (- argCount 1i)) {
  |      33:let nextArg = args[k]
  |      34:app __x2__ = (ToString nextArg)
  |      34:let nextArgString = [? __x2__]
  |      35:??? "Set id:{P} to the string - concatenation of id:{P} , value:{\",\"} ( a comma ) , and id:{nextArgString} ."
  |      36:k = (+ k 1i)
  |    }
  |    37:let bodyArg = args[k]
  |  }
  |  38:app __x3__ = (ToString bodyArg)
  |  38:let bodyString = (+ (+ "\n" [? __x3__]) "\n")
  |  39:??? "Let id:{prefix} be the prefix associated with id:{kind} in link:{table-dynamic-function-sourcetext-prefixes} ."
  |  40:let sourceString = (+ (+ (+ (+ (+ (+ prefix "anonymous(") P) "\n") ") {") bodyString) "}")
  |  41:app __x4__ = (StringToCodePoints sourceString)
  |  41:let sourceText = [! __x4__]
  |  42:let parameterGoal = AST_FormalParameters
  |  42:let script = AST_Script
  |  42:let body = AST_ScriptBody
  |  61:app __x5__ = (GetPrototypeFromConstructor newTarget fallbackProto)
  |  61:let proto = [? __x5__]
  |  62:let realmF = REALM
  |  63:let scope = realmF.GlobalEnv
  |  64:app __x6__ = (OrdinaryFunctionCreate proto sourceText parameters body CONST_nonDASHlexicalDASHthis scope)
  |  64:let F = [! __x6__]
  |  65:app __x7__ = (SetFunctionName F "anonymous")
  |  65:__x7__
  |  72:if (= kind CONST_generator) {
  |    67:app __x8__ = (OrdinaryObjectCreate INTRINSIC_GeneratorFunction_prototype_prototype)
  |    67:let prototype = [! __x8__]
  |    68:app __x9__ = (DefinePropertyOrThrow F "prototype" (new PropertyDescriptor("Value" -> prototype, "Writable" -> true, "Enumerable" -> false, "Configurable" -> false)))
  |    68:__x9__
  |  } else if (= kind CONST_asyncGenerator) {
  |    70:app __x10__ = (OrdinaryObjectCreate INTRINSIC_AsyncGeneratorFunction_prototype_prototype)
  |    70:let prototype = [! __x10__]
  |    71:app __x11__ = (DefinePropertyOrThrow F "prototype" (new PropertyDescriptor("Value" -> prototype, "Writable" -> true, "Enumerable" -> false, "Configurable" -> false)))
  |    71:__x11__
  |  } else if (= kind CONST_normal) {
  |    app __x12__ = (MakeConstructor F)
  |    __x12__
  |  } else 73:{}
  |  74:return F
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: The execution context stack has at least two elements.""",
    """            1. Let _callerContext_ be the second to top element of the execution context stack.""",
    """            1. Let _callerRealm_ be _callerContext_'s Realm.""",
    """            1. Let _calleeRealm_ be the current Realm Record.""",
    """            1. Perform ? HostEnsureCanCompileStrings(_callerRealm_, _calleeRealm_).""",
    """            1. If _newTarget_ is *undefined*, set _newTarget_ to _constructor_.""",
    """            1. If _kind_ is ~normal~, then""",
    """              1. Let _goal_ be the grammar symbol |FunctionBody[~Yield, ~Await]|.""",
    """              1. Let _parameterGoal_ be the grammar symbol |FormalParameters[~Yield, ~Await]|.""",
    """              1. Let _fallbackProto_ be *"%Function.prototype%"*.""",
    """            1. Else if _kind_ is ~generator~, then""",
    """              1. Let _goal_ be the grammar symbol |GeneratorBody|.""",
    """              1. Let _parameterGoal_ be the grammar symbol |FormalParameters[+Yield, ~Await]|.""",
    """              1. Let _fallbackProto_ be *"%GeneratorFunction.prototype%"*.""",
    """            1. Else if _kind_ is ~async~, then""",
    """              1. Let _goal_ be the grammar symbol |AsyncFunctionBody|.""",
    """              1. Let _parameterGoal_ be the grammar symbol |FormalParameters[~Yield, +Await]|.""",
    """              1. Let _fallbackProto_ be *"%AsyncFunction.prototype%"*.""",
    """            1. Else,""",
    """              1. Assert: _kind_ is ~asyncGenerator~.""",
    """              1. Let _goal_ be the grammar symbol |AsyncGeneratorBody|.""",
    """              1. Let _parameterGoal_ be the grammar symbol |FormalParameters[+Yield, +Await]|.""",
    """              1. Let _fallbackProto_ be *"%AsyncGeneratorFunction.prototype%"*.""",
    """            1. Let _argCount_ be the number of elements in _args_.""",
    """            1. Let _P_ be the empty String.""",
    """            1. If _argCount_ = 0, let _bodyArg_ be the empty String.""",
    """            1. Else if _argCount_ = 1, let _bodyArg_ be _args_[0].""",
    """            1. Else,""",
    """              1. Assert: _argCount_ > 1.""",
    """              1. Let _firstArg_ be _args_[0].""",
    """              1. Set _P_ to ? ToString(_firstArg_).""",
    """              1. Let _k_ be 1.""",
    """              1. Repeat, while _k_ < _argCount_ - 1,""",
    """                1. Let _nextArg_ be _args_[_k_].""",
    """                1. Let _nextArgString_ be ? ToString(_nextArg_).""",
    """                1. Set _P_ to the string-concatenation of _P_, *","* (a comma), and _nextArgString_.""",
    """                1. Set _k_ to _k_ + 1.""",
    """              1. Let _bodyArg_ be _args_[_k_].""",
    """            1. Let _bodyString_ be the string-concatenation of 0x000A (LINE FEED), ? ToString(_bodyArg_), and 0x000A (LINE FEED).""",
    """            1. Let _prefix_ be the prefix associated with _kind_ in <emu-xref href="#table-dynamic-function-sourcetext-prefixes"></emu-xref>.""",
    """            1. Let _sourceString_ be the string-concatenation of _prefix_, *" anonymous("*, _P_, 0x000A (LINE FEED), *") {"*, _bodyString_, and *"}"*.""",
    """            1. Let _sourceText_ be ! StringToCodePoints(_sourceString_).""",
    """            1. Perform the following substeps in an implementation-defined order, possibly interleaving parsing and error detection:""",
    """              1. Let _parameters_ be ParseText(! StringToCodePoints(_P_), _parameterGoal_).""",
    """              1. If _parameters_ is a List of errors, throw a *SyntaxError* exception.""",
    """              1. Let _body_ be ParseText(! StringToCodePoints(_bodyString_), _goal_).""",
    """              1. If _body_ is a List of errors, throw a *SyntaxError* exception.""",
    """              1. Let _strict_ be FunctionBodyContainsUseStrict of _body_.""",
    """              1. If _strict_ is *true*, apply the early error rules for <emu-grammar>UniqueFormalParameters : FormalParameters</emu-grammar> to _parameters_.""",
    """              1. If _strict_ is *true* and IsSimpleParameterList of _parameters_ is *false*, throw a *SyntaxError* exception.""",
    """              1. If any element of the BoundNames of _parameters_ also occurs in the LexicallyDeclaredNames of _body_, throw a *SyntaxError* exception.""",
    """              1. If _body_ Contains |SuperCall| is *true*, throw a *SyntaxError* exception.""",
    """              1. If _parameters_ Contains |SuperCall| is *true*, throw a *SyntaxError* exception.""",
    """              1. If _body_ Contains |SuperProperty| is *true*, throw a *SyntaxError* exception.""",
    """              1. If _parameters_ Contains |SuperProperty| is *true*, throw a *SyntaxError* exception.""",
    """              1. If _kind_ is ~generator~ or ~asyncGenerator~, then""",
    """                1. If _parameters_ Contains |YieldExpression| is *true*, throw a *SyntaxError* exception.""",
    """              1. If _kind_ is ~async~ or ~asyncGenerator~, then""",
    """                1. If _parameters_ Contains |AwaitExpression| is *true*, throw a *SyntaxError* exception.""",
    """              1. If _strict_ is *true*, then""",
    """                1. If BoundNames of _parameters_ contains any duplicate elements, throw a *SyntaxError* exception.""",
    """            1. Let _proto_ be ? GetPrototypeFromConstructor(_newTarget_, _fallbackProto_).""",
    """            1. Let _realmF_ be the current Realm Record.""",
    """            1. Let _scope_ be _realmF_.[[GlobalEnv]].""",
    """            1. Let _F_ be ! OrdinaryFunctionCreate(_proto_, _sourceText_, _parameters_, _body_, ~non-lexical-this~, _scope_).""",
    """            1. Perform SetFunctionName(_F_, *"anonymous"*).""",
    """            1. If _kind_ is ~generator~, then""",
    """              1. Let _prototype_ be ! OrdinaryObjectCreate(%GeneratorFunction.prototype.prototype%).""",
    """              1. Perform DefinePropertyOrThrow(_F_, *"prototype"*, PropertyDescriptor { [[Value]]: _prototype_, [[Writable]]: *true*, [[Enumerable]]: *false*, [[Configurable]]: *false* }).""",
    """            1. Else if _kind_ is ~asyncGenerator~, then""",
    """              1. Let _prototype_ be ! OrdinaryObjectCreate(%AsyncGeneratorFunction.prototype.prototype%).""",
    """              1. Perform DefinePropertyOrThrow(_F_, *"prototype"*, PropertyDescriptor { [[Value]]: _prototype_, [[Writable]]: *true*, [[Enumerable]]: *false*, [[Configurable]]: *false* }).""",
    """            1. Else if _kind_ is ~normal~, perform MakeConstructor(_F_).""",
    """            1. NOTE: Functions whose _kind_ is ~async~ are not constructible and do not have a [[Construct]] internal method or a *"prototype"* property.""",
    """            1. Return _F_.""",
  )
}
