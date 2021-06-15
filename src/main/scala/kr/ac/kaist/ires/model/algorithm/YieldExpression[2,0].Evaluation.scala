package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::YieldExpression[2,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("YieldExpression", 2, 0, Rhs(List(Terminal("yield"), Terminal("*"), NonTerminal("AssignmentExpression", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-generator-function-definitions-runtime-semantics-evaluation",
    "sec-generator-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (GetGeneratorKind)
  |  0:let generatorKind = [! __x0__]
  |  1:access __x1__ = (AssignmentExpression "Evaluation")
  |  1:let exprRef = __x1__
  |  2:app __x2__ = (GetValue exprRef)
  |  2:let value = [? __x2__]
  |  3:app __x3__ = (GetIterator value generatorKind)
  |  3:let iteratorRecord = [? __x3__]
  |  4:let iterator = iteratorRecord.Iterator
  |  5:app __x4__ = (NormalCompletion undefined)
  |  5:let received = __x4__
  |  6:while true if (= received.Type CONST_normal) {
  |    8:app __x5__ = (Call iteratorRecord.NextMethod iteratorRecord.Iterator (new [received.Value]))
  |    8:let innerResult = [? __x5__]
  |    9:if (= generatorKind CONST_async) {
  |      app __x6__ = (Await innerResult)
  |      innerResult = [? __x6__]
  |    } else 33:{}
  |    10:if (! (= (typeof innerResult) Object)) throw TypeError else 33:{}
  |    11:app __x7__ = (IteratorComplete innerResult)
  |    11:let done = [? __x7__]
  |    12:if (= done true) {
  |      13:app __x8__ = (IteratorValue innerResult)
  |      13:return [? __x8__]
  |    } else 33:{}
  |    15:if (= generatorKind CONST_async) {
  |      app __x9__ = (IteratorValue innerResult)
  |      app __x10__ = (AsyncGeneratorYield [? __x9__])
  |      received = __x10__
  |    } else {
  |      app __x11__ = (GeneratorYield innerResult)
  |      received = __x11__
  |    }
  |  } else if (= received.Type CONST_throw) {
  |    17:app __x12__ = (GetMethod iterator "throw")
  |    17:let throw = [? __x12__]
  |    28:if (! (= throw undefined)) {
  |      19:app __x13__ = (Call throw iterator (new [received.Value]))
  |      19:let innerResult = [? __x13__]
  |      20:if (= generatorKind CONST_async) {
  |        app __x14__ = (Await innerResult)
  |        innerResult = [? __x14__]
  |      } else 33:{}
  |      22:if (! (= (typeof innerResult) Object)) throw TypeError else 33:{}
  |      23:app __x15__ = (IteratorComplete innerResult)
  |      23:let done = [? __x15__]
  |      24:if (= done true) {
  |        25:app __x16__ = (IteratorValue innerResult)
  |        25:return [? __x16__]
  |      } else 33:{}
  |      27:if (= generatorKind CONST_async) {
  |        app __x17__ = (IteratorValue innerResult)
  |        app __x18__ = (AsyncGeneratorYield [? __x17__])
  |        received = __x18__
  |      } else {
  |        app __x19__ = (GeneratorYield innerResult)
  |        received = __x19__
  |      }
  |    } else {
  |      30:let closeCompletion = (new Completion("Type" -> CONST_normal, "Value" -> CONST_empty, "Target" -> CONST_empty))
  |      32:if (= generatorKind CONST_async) {
  |        app __x20__ = (AsyncIteratorClose iteratorRecord closeCompletion)
  |        [? __x20__]
  |      } else {
  |        app __x21__ = (IteratorClose iteratorRecord closeCompletion)
  |        [? __x21__]
  |      }
  |      34:throw TypeError
  |    }
  |  } else {
  |    36:assert (= received.Type CONST_return)
  |    37:app __x22__ = (GetMethod iterator "return")
  |    37:let return = [? __x22__]
  |    38:if (= return undefined) {
  |      39:if (= generatorKind CONST_async) {
  |        app __x23__ = (Await received.Value)
  |        received.Value = [? __x23__]
  |      } else 33:{}
  |      40:return received
  |    } else 33:{}
  |    41:app __x24__ = (Call return iterator (new [received.Value]))
  |    41:let innerReturnResult = [? __x24__]
  |    42:if (= generatorKind CONST_async) {
  |      app __x25__ = (Await innerReturnResult)
  |      innerReturnResult = [? __x25__]
  |    } else 33:{}
  |    43:if (! (= (typeof innerReturnResult) Object)) throw TypeError else 33:{}
  |    44:app __x26__ = (IteratorComplete innerReturnResult)
  |    44:let done = [? __x26__]
  |    45:if (= done true) {
  |      46:app __x27__ = (IteratorValue innerReturnResult)
  |      46:let value = [? __x27__]
  |      47:return (new Completion("Type" -> CONST_return, "Value" -> value, "Target" -> CONST_empty))
  |    } else 33:{}
  |    49:if (= generatorKind CONST_async) {
  |      app __x28__ = (IteratorValue innerReturnResult)
  |      app __x29__ = (AsyncGeneratorYield [? __x28__])
  |      received = __x29__
  |    } else {
  |      app __x30__ = (GeneratorYield innerReturnResult)
  |      received = __x30__
  |    }
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _generatorKind_ be ! GetGeneratorKind().""",
    """        1. Let _exprRef_ be the result of evaluating |AssignmentExpression|.""",
    """        1. Let _value_ be ? GetValue(_exprRef_).""",
    """        1. Let _iteratorRecord_ be ? GetIterator(_value_, _generatorKind_).""",
    """        1. Let _iterator_ be _iteratorRecord_.[[Iterator]].""",
    """        1. Let _received_ be NormalCompletion(*undefined*).""",
    """        1. Repeat,""",
    """          1. If _received_.[[Type]] is ~normal~, then""",
    """            1. Let _innerResult_ be ? Call(_iteratorRecord_.[[NextMethod]], _iteratorRecord_.[[Iterator]], « _received_.[[Value]] »).""",
    """            1. If _generatorKind_ is ~async~, set _innerResult_ to ? Await(_innerResult_).""",
    """            1. If Type(_innerResult_) is not Object, throw a *TypeError* exception.""",
    """            1. Let _done_ be ? IteratorComplete(_innerResult_).""",
    """            1. If _done_ is *true*, then""",
    """              1. Return ? IteratorValue(_innerResult_).""",
    """            1. If _generatorKind_ is ~async~, set _received_ to AsyncGeneratorYield(? IteratorValue(_innerResult_)).""",
    """            1. Else, set _received_ to GeneratorYield(_innerResult_).""",
    """          1. Else if _received_.[[Type]] is ~throw~, then""",
    """            1. Let _throw_ be ? GetMethod(_iterator_, *"throw"*).""",
    """            1. If _throw_ is not *undefined*, then""",
    """              1. Let _innerResult_ be ? Call(_throw_, _iterator_, « _received_.[[Value]] »).""",
    """              1. If _generatorKind_ is ~async~, set _innerResult_ to ? Await(_innerResult_).""",
    """              1. NOTE: Exceptions from the inner iterator `throw` method are propagated. Normal completions from an inner `throw` method are processed similarly to an inner `next`.""",
    """              1. If Type(_innerResult_) is not Object, throw a *TypeError* exception.""",
    """              1. Let _done_ be ? IteratorComplete(_innerResult_).""",
    """              1. If _done_ is *true*, then""",
    """                1. Return ? IteratorValue(_innerResult_).""",
    """              1. If _generatorKind_ is ~async~, set _received_ to AsyncGeneratorYield(? IteratorValue(_innerResult_)).""",
    """              1. Else, set _received_ to GeneratorYield(_innerResult_).""",
    """            1. Else,""",
    """              1. NOTE: If _iterator_ does not have a `throw` method, this throw is going to terminate the `yield*` loop. But first we need to give _iterator_ a chance to clean up.""",
    """              1. Let _closeCompletion_ be Completion { [[Type]]: ~normal~, [[Value]]: ~empty~, [[Target]]: ~empty~ }.""",
    """              1. If _generatorKind_ is ~async~, perform ? AsyncIteratorClose(_iteratorRecord_, _closeCompletion_).""",
    """              1. Else, perform ? IteratorClose(_iteratorRecord_, _closeCompletion_).""",
    """              1. NOTE: The next step throws a *TypeError* to indicate that there was a `yield*` protocol violation: _iterator_ does not have a `throw` method.""",
    """              1. Throw a *TypeError* exception.""",
    """          1. Else,""",
    """            1. Assert: _received_.[[Type]] is ~return~.""",
    """            1. Let _return_ be ? GetMethod(_iterator_, *"return"*).""",
    """            1. If _return_ is *undefined*, then""",
    """              1. If _generatorKind_ is ~async~, set _received_.[[Value]] to ? Await(_received_.[[Value]]).""",
    """              1. Return Completion(_received_).""",
    """            1. Let _innerReturnResult_ be ? Call(_return_, _iterator_, « _received_.[[Value]] »).""",
    """            1. If _generatorKind_ is ~async~, set _innerReturnResult_ to ? Await(_innerReturnResult_).""",
    """            1. If Type(_innerReturnResult_) is not Object, throw a *TypeError* exception.""",
    """            1. Let _done_ be ? IteratorComplete(_innerReturnResult_).""",
    """            1. If _done_ is *true*, then""",
    """              1. Let _value_ be ? IteratorValue(_innerReturnResult_).""",
    """              1. Return Completion { [[Type]]: ~return~, [[Value]]: _value_, [[Target]]: ~empty~ }.""",
    """            1. If _generatorKind_ is ~async~, set _received_ to AsyncGeneratorYield(? IteratorValue(_innerReturnResult_)).""",
    """            1. Else, set _received_ to GeneratorYield(_innerReturnResult_).""",
  )
}
