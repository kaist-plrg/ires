package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ForInOfBodyEvaluation` extends Algo {
  val head = NormalHead("ForInOfBodyEvaluation", List(Param("lhs", Normal), Param("stmt", Normal), Param("iteratorRecord", Normal), Param("iterationKind", Normal), Param("lhsKind", Normal), Param("labelSet", Normal), Param("iteratorKind", Optional)))
  val ids = List(
    "sec-runtime-semantics-forin-div-ofbodyevaluation-lhs-stmt-iterator-lhskind-labelset",
    "sec-for-in-and-for-of-statements",
    "sec-iteration-statements",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:if (= iteratorKind absent) iteratorKind = CONST_sync else 24:{}
  |  1:let oldEnv = CONTEXT.LexicalEnvironment
  |  2:let V = undefined
  |  3:access __x0__ = (lhs "IsDestructuring")
  |  3:let destructuring = __x0__
  |  4:if (&& (= destructuring true) (= lhsKind CONST_assignment)) {
  |    5:assert (is-instance-of lhs LeftHandSideExpression)
  |    6:let assignmentPattern = (parse-syntax lhs "AssignmentPattern" (new []))
  |  } else 24:{}
  |  7:while true {
  |    8:app __x1__ = (Call iteratorRecord.NextMethod iteratorRecord.Iterator)
  |    8:let nextResult = [? __x1__]
  |    9:if (= iteratorKind CONST_async) {
  |      app __x2__ = (Await nextResult)
  |      nextResult = [? __x2__]
  |    } else 24:{}
  |    10:if (! (= (typeof nextResult) Object)) throw TypeError else 24:{}
  |    11:app __x3__ = (IteratorComplete nextResult)
  |    11:let done = [? __x3__]
  |    12:if (= done true) return V else 24:{}
  |    13:app __x4__ = (IteratorValue nextResult)
  |    13:let nextValue = [? __x4__]
  |    17:if (|| (= lhsKind CONST_assignment) (= lhsKind CONST_varBinding)) if (= destructuring false) {
  |      16:access __x5__ = (lhs "Evaluation")
  |      16:let lhsRef = __x5__
  |    } else 24:{} else {
  |      18:assert (= lhsKind CONST_lexicalBinding)
  |      19:assert (is-instance-of lhs ForDeclaration)
  |      20:app __x6__ = (NewDeclarativeEnvironment oldEnv)
  |      20:let iterationEnv = __x6__
  |      21:access __x7__ = (lhs "ForDeclarationBindingInstantiation" iterationEnv)
  |      21:__x7__
  |      22:CONTEXT.LexicalEnvironment = iterationEnv
  |      23:if (= destructuring false) {
  |        25:access __x8__ = (lhs "BoundNames")
  |        25:let lhsName = __x8__[0i]
  |        26:app __x9__ = (ResolveBinding lhsName)
  |        26:let lhsRef = [! __x9__]
  |      } else 24:{}
  |    }
  |    34:if (= destructuring false) {
  |      32:app __x10__ = (IsAbruptCompletion lhsRef)
  |      32:if __x10__ let status = lhsRef else if (= lhsKind CONST_lexicalBinding) {
  |        31:app __x11__ = (InitializeReferencedBinding lhsRef nextValue)
  |        31:let status = __x11__
  |      } else {
  |        33:app __x12__ = (PutValue lhsRef nextValue)
  |        33:let status = __x12__
  |      }
  |    } else if (= lhsKind CONST_assignment) {
  |      36:access __x13__ = (assignmentPattern "DestructuringAssignmentEvaluation" nextValue)
  |      36:let status = __x13__
  |    } else if (= lhsKind CONST_varBinding) {
  |      38:assert (is-instance-of lhs ForBinding)
  |      39:access __x14__ = (lhs "BindingInitialization" nextValue undefined)
  |      39:let status = __x14__
  |    } else {
  |      41:assert (= lhsKind CONST_lexicalBinding)
  |      42:assert (is-instance-of lhs ForDeclaration)
  |      43:access __x15__ = (lhs "ForDeclarationBindingInitialization" nextValue iterationEnv)
  |      43:let status = __x15__
  |    }
  |    44:app __x16__ = (IsAbruptCompletion status)
  |    44:if __x16__ {
  |      45:CONTEXT.LexicalEnvironment = oldEnv
  |      46:if (= iteratorKind CONST_async) {
  |        app __x17__ = (AsyncIteratorClose iteratorRecord status)
  |        return [? __x17__]
  |      } else 24:{}
  |      49:if (= iterationKind CONST_enumerate) return status else {
  |        50:assert (= iterationKind CONST_iterate)
  |        51:app __x18__ = (IteratorClose iteratorRecord status)
  |        51:return [? __x18__]
  |      }
  |    } else 24:{}
  |    52:access __x19__ = (stmt "Evaluation")
  |    52:let result = __x19__
  |    53:CONTEXT.LexicalEnvironment = oldEnv
  |    54:app __x20__ = (LoopContinues result labelSet)
  |    54:if (= __x20__ false) if (= iterationKind CONST_enumerate) {
  |      56:app __x21__ = (UpdateEmpty result V)
  |      56:return __x21__
  |    } else {
  |      58:assert (= iterationKind CONST_iterate)
  |      59:app __x22__ = (UpdateEmpty result V)
  |      59:status = __x22__
  |      60:if (= iteratorKind CONST_async) {
  |        app __x23__ = (AsyncIteratorClose iteratorRecord status)
  |        return [? __x23__]
  |      } else 24:{}
  |      61:app __x24__ = (IteratorClose iteratorRecord status)
  |      61:return [? __x24__]
  |    } else 24:{}
  |    62:if (! (= result.Value CONST_empty)) V = result.Value else 24:{}
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If _iteratorKind_ is not present, set _iteratorKind_ to ~sync~.""",
    """          1. Let _oldEnv_ be the running execution context's LexicalEnvironment.""",
    """          1. Let _V_ be *undefined*.""",
    """          1. Let _destructuring_ be IsDestructuring of _lhs_.""",
    """          1. If _destructuring_ is *true* and if _lhsKind_ is ~assignment~, then""",
    """            1. Assert: _lhs_ is a |LeftHandSideExpression|.""",
    """            1. Let _assignmentPattern_ be the |AssignmentPattern| that is covered by _lhs_.""",
    """          1. Repeat,""",
    """            1. Let _nextResult_ be ? Call(_iteratorRecord_.[[NextMethod]], _iteratorRecord_.[[Iterator]]).""",
    """            1. If _iteratorKind_ is ~async~, set _nextResult_ to ? Await(_nextResult_).""",
    """            1. If Type(_nextResult_) is not Object, throw a *TypeError* exception.""",
    """            1. Let _done_ be ? IteratorComplete(_nextResult_).""",
    """            1. If _done_ is *true*, return NormalCompletion(_V_).""",
    """            1. Let _nextValue_ be ? IteratorValue(_nextResult_).""",
    """            1. If _lhsKind_ is either ~assignment~ or ~varBinding~, then""",
    """              1. If _destructuring_ is *false*, then""",
    """                1. Let _lhsRef_ be the result of evaluating _lhs_. (It may be evaluated repeatedly.)""",
    """            1. Else,""",
    """              1. Assert: _lhsKind_ is ~lexicalBinding~.""",
    """              1. Assert: _lhs_ is a |ForDeclaration|.""",
    """              1. Let _iterationEnv_ be NewDeclarativeEnvironment(_oldEnv_).""",
    """              1. Perform ForDeclarationBindingInstantiation for _lhs_ passing _iterationEnv_ as the argument.""",
    """              1. Set the running execution context's LexicalEnvironment to _iterationEnv_.""",
    """              1. If _destructuring_ is *false*, then""",
    """                1. Assert: _lhs_ binds a single name.""",
    """                1. Let _lhsName_ be the sole element of BoundNames of _lhs_.""",
    """                1. Let _lhsRef_ be ! ResolveBinding(_lhsName_).""",
    """            1. If _destructuring_ is *false*, then""",
    """              1. If _lhsRef_ is an abrupt completion, then""",
    """                1. Let _status_ be _lhsRef_.""",
    """              1. Else if _lhsKind_ is ~lexicalBinding~, then""",
    """                1. Let _status_ be InitializeReferencedBinding(_lhsRef_, _nextValue_).""",
    """              1. Else,""",
    """                1. Let _status_ be PutValue(_lhsRef_, _nextValue_).""",
    """            1. Else,""",
    """              1. If _lhsKind_ is ~assignment~, then""",
    """                1. Let _status_ be DestructuringAssignmentEvaluation of _assignmentPattern_ with argument _nextValue_.""",
    """              1. Else if _lhsKind_ is ~varBinding~, then""",
    """                1. Assert: _lhs_ is a |ForBinding|.""",
    """                1. Let _status_ be BindingInitialization of _lhs_ with arguments _nextValue_ and *undefined*.""",
    """              1. Else,""",
    """                1. Assert: _lhsKind_ is ~lexicalBinding~.""",
    """                1. Assert: _lhs_ is a |ForDeclaration|.""",
    """                1. Let _status_ be ForDeclarationBindingInitialization of _lhs_ with arguments _nextValue_ and _iterationEnv_.""",
    """            1. If _status_ is an abrupt completion, then""",
    """              1. Set the running execution context's LexicalEnvironment to _oldEnv_.""",
    """              1. If _iteratorKind_ is ~async~, return ? AsyncIteratorClose(_iteratorRecord_, _status_).""",
    """              1. If _iterationKind_ is ~enumerate~, then""",
    """                1. Return _status_.""",
    """              1. Else,""",
    """                1. Assert: _iterationKind_ is ~iterate~.""",
    """                1. Return ? IteratorClose(_iteratorRecord_, _status_).""",
    """            1. Let _result_ be the result of evaluating _stmt_.""",
    """            1. Set the running execution context's LexicalEnvironment to _oldEnv_.""",
    """            1. If LoopContinues(_result_, _labelSet_) is *false*, then""",
    """              1. If _iterationKind_ is ~enumerate~, then""",
    """                1. Return Completion(UpdateEmpty(_result_, _V_)).""",
    """              1. Else,""",
    """                1. Assert: _iterationKind_ is ~iterate~.""",
    """                1. Set _status_ to UpdateEmpty(_result_, _V_).""",
    """                1. If _iteratorKind_ is ~async~, return ? AsyncIteratorClose(_iteratorRecord_, _status_).""",
    """                1. Return ? IteratorClose(_iteratorRecord_, _status_).""",
    """            1. If _result_.[[Value]] is not ~empty~, set _V_ to _result_.[[Value]].""",
  )
}
