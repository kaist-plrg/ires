package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ClassTail[0,3].ClassDefinitionEvaluation` extends Algo {
  val head = SyntaxDirectedHead("ClassTail", 0, 3, Rhs(List(NonTerminal("ClassHeritage", List(""), true), Terminal("{"), NonTerminal("ClassBody", List(""), true), Terminal("}")), None), "ClassDefinitionEvaluation", List(Param("classBinding", Normal), Param("className", Normal)))
  val ids = List(
    "sec-runtime-semantics-classdefinitionevaluation",
    "sec-class-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (CONTEXT "LexicalEnvironment")
  |  0:let env = __x0__
  |  1:app __x1__ = (NewDeclarativeEnvironment env)
  |  1:let classScope = __x1__
  |  2:if (! (= classBinding undefined)) {
  |    3:app __x2__ = (classScope.CreateImmutableBinding classScope classBinding true)
  |    3:__x2__
  |  } else 33:{}
  |  7:if (= ClassHeritage absent) {
  |    5:let protoParent = INTRINSIC_Object_prototype
  |    6:let constructorParent = INTRINSIC_Function_prototype
  |  } else {
  |    8:CONTEXT.LexicalEnvironment = classScope
  |    9:access __x3__ = (ClassHeritage "Evaluation")
  |    9:let superclassRef = __x3__
  |    10:CONTEXT.LexicalEnvironment = env
  |    11:app __x4__ = (GetValue superclassRef)
  |    11:let superclass = [? __x4__]
  |    16:if (= superclass null) {
  |      13:let protoParent = null
  |      14:let constructorParent = INTRINSIC_Function_prototype
  |    } else {
  |      app __x5__ = (IsConstructor superclass)
  |      if (= __x5__ false) throw TypeError else {
  |        17:app __x6__ = (Get superclass "prototype")
  |        17:let protoParent = [? __x6__]
  |        18:if (! (|| (= (typeof protoParent) Object) (= (typeof protoParent) Null))) throw TypeError else 33:{}
  |        19:let constructorParent = superclass
  |      }
  |    }
  |  }
  |  20:app __x7__ = (OrdinaryObjectCreate protoParent)
  |  20:let proto = [! __x7__]
  |  22:if (= ClassBody absent) let constructor = CONST_empty else {
  |    access __x8__ = (ClassBody "ConstructorMethod")
  |    let constructor = __x8__
  |  }
  |  23:CONTEXT.LexicalEnvironment = classScope
  |  27:if (= constructor CONST_empty) {
  |    25:let steps = DefaultConstructorFunctions
  |    26:app __x9__ = (CreateBuiltinFunction steps 0i className (new ["ConstructorKind", "SourceText"]) CONST_empty constructorParent)
  |    26:let F = [! __x9__]
  |  } else {
  |    28:access __x10__ = (constructor "DefineMethod" proto constructorParent)
  |    28:let constructorInfo = [! __x10__]
  |    29:let F = constructorInfo.Closure
  |    30:app __x11__ = (MakeClassConstructor F)
  |    30:[! __x11__]
  |    31:app __x12__ = (SetFunctionName F className)
  |    31:[! __x12__]
  |  }
  |  32:app __x13__ = (MakeConstructor F false proto)
  |  32:[! __x13__]
  |  33:if (! (= ClassHeritage absent)) F.ConstructorKind = CONST_derived else 33:{}
  |  34:app __x14__ = (CreateMethodProperty proto "constructor" F)
  |  34:[! __x14__]
  |  36:if (= ClassBody absent) let methods = (new []) else {
  |    access __x15__ = (ClassBody "NonConstructorMethodDefinitions")
  |    let methods = __x15__
  |  }
  |  37:let __x16__ = methods
  |  37:let __x17__ = 0i
  |  37:while (< __x17__ __x16__.length) {
  |    let m = __x16__[__x17__]
  |    40:access __x18__ = (m "IsStatic")
  |    40:if (= __x18__ false) {
  |      39:access __x19__ = (m "PropertyDefinitionEvaluation" proto false)
  |      39:let status = __x19__
  |    } else {
  |      41:access __x20__ = (m "PropertyDefinitionEvaluation" F false)
  |      41:let status = __x20__
  |    }
  |    42:app __x21__ = (IsAbruptCompletion status)
  |    42:if __x21__ {
  |      43:CONTEXT.LexicalEnvironment = env
  |      44:return status
  |    } else 33:{}
  |    __x17__ = (+ __x17__ 1i)
  |  }
  |  45:CONTEXT.LexicalEnvironment = env
  |  46:if (! (= classBinding undefined)) {
  |    47:app __x22__ = (classScope.InitializeBinding classScope classBinding F)
  |    47:__x22__
  |  } else 33:{}
  |  48:return F
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _env_ be the LexicalEnvironment of the running execution context.""",
    """        1. Let _classScope_ be NewDeclarativeEnvironment(_env_).""",
    """        1. If _classBinding_ is not *undefined*, then""",
    """          1. Perform _classScope_.CreateImmutableBinding(_classBinding_, *true*).""",
    """        1. If |ClassHeritage_opt| is not present, then""",
    """          1. Let _protoParent_ be %Object.prototype%.""",
    """          1. Let _constructorParent_ be %Function.prototype%.""",
    """        1. Else,""",
    """          1. Set the running execution context's LexicalEnvironment to _classScope_.""",
    """          1. Let _superclassRef_ be the result of evaluating |ClassHeritage|.""",
    """          1. Set the running execution context's LexicalEnvironment to _env_.""",
    """          1. Let _superclass_ be ? GetValue(_superclassRef_).""",
    """          1. If _superclass_ is *null*, then""",
    """            1. Let _protoParent_ be *null*.""",
    """            1. Let _constructorParent_ be %Function.prototype%.""",
    """          1. Else if IsConstructor(_superclass_) is *false*, throw a *TypeError* exception.""",
    """          1. Else,""",
    """            1. Let _protoParent_ be ? Get(_superclass_, *"prototype"*).""",
    """            1. If Type(_protoParent_) is neither Object nor Null, throw a *TypeError* exception.""",
    """            1. Let _constructorParent_ be _superclass_.""",
    """        1. Let _proto_ be ! OrdinaryObjectCreate(_protoParent_).""",
    """        1. If |ClassBody_opt| is not present, let _constructor_ be ~empty~.""",
    """        1. Else, let _constructor_ be ConstructorMethod of |ClassBody|.""",
    """        1. Set the running execution context's LexicalEnvironment to _classScope_.""",
    """        1. If _constructor_ is ~empty~, then""",
    """          1. Let _steps_ be the algorithm steps defined in <emu-xref href="#sec-default-constructor-functions" title></emu-xref>.""",
    """          1. Let _F_ be ! CreateBuiltinFunction(_steps_, 0, _className_, « [[ConstructorKind]], [[SourceText]] », ~empty~, _constructorParent_).""",
    """        1. Else,""",
    """          1. Let _constructorInfo_ be ! DefineMethod of _constructor_ with arguments _proto_ and _constructorParent_.""",
    """          1. Let _F_ be _constructorInfo_.[[Closure]].""",
    """          1. Perform ! MakeClassConstructor(_F_).""",
    """          1. Perform ! SetFunctionName(_F_, _className_).""",
    """        1. Perform ! MakeConstructor(_F_, *false*, _proto_).""",
    """        1. If |ClassHeritage_opt| is present, set _F_.[[ConstructorKind]] to ~derived~.""",
    """        1. Perform ! CreateMethodProperty(_proto_, *"constructor"*, _F_).""",
    """        1. If |ClassBody_opt| is not present, let _methods_ be a new empty List.""",
    """        1. Else, let _methods_ be NonConstructorMethodDefinitions of |ClassBody|.""",
    """        1. For each |ClassElement| _m_ of _methods_, do""",
    """          1. If IsStatic of _m_ is *false*, then""",
    """            1. Let _status_ be PropertyDefinitionEvaluation of _m_ with arguments _proto_ and *false*.""",
    """          1. Else,""",
    """            1. Let _status_ be PropertyDefinitionEvaluation of _m_ with arguments _F_ and *false*.""",
    """          1. If _status_ is an abrupt completion, then""",
    """            1. Set the running execution context's LexicalEnvironment to _env_.""",
    """            1. Return Completion(_status_).""",
    """        1. Set the running execution context's LexicalEnvironment to _env_.""",
    """        1. If _classBinding_ is not *undefined*, then""",
    """          1. Perform _classScope_.InitializeBinding(_classBinding_, _F_).""",
    """        1. Return _F_.""",
  )
}
