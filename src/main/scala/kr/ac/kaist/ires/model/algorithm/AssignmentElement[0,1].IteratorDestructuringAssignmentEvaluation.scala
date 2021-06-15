package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AssignmentElement[0,1].IteratorDestructuringAssignmentEvaluation` extends Algo {
  val head = SyntaxDirectedHead("AssignmentElement", 0, 1, Rhs(List(NonTerminal("DestructuringAssignmentTarget", List(""), false), NonTerminal("Initializer", List(""), true)), None), "IteratorDestructuringAssignmentEvaluation", List(Param("iteratorRecord", Normal)))
  val ids = List(
    "sec-runtime-semantics-iteratordestructuringassignmentevaluation",
    "sec-destructuring-assignment",
    "sec-assignment-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:if (! (|| (is-instance-of DestructuringAssignmentTarget ObjectLiteral) (is-instance-of DestructuringAssignmentTarget ArrayLiteral))) {
  |    1:access __x0__ = (DestructuringAssignmentTarget "Evaluation")
  |    1:let lref = __x0__
  |    2:[? lref]
  |  } else 10:{}
  |  3:if (= iteratorRecord.Done false) {
  |    4:app __x1__ = (IteratorStep iteratorRecord)
  |    4:let next = __x1__
  |    5:app __x2__ = (IsAbruptCompletion next)
  |    5:if __x2__ iteratorRecord.Done = true else 10:{}
  |    6:[? next]
  |    8:if (= next false) iteratorRecord.Done = true else {
  |      9:app __x3__ = (IteratorValue next)
  |      9:let value = __x3__
  |      10:app __x4__ = (IsAbruptCompletion value)
  |      10:if __x4__ iteratorRecord.Done = true else 10:{}
  |      11:[? value]
  |    }
  |  } else 10:{}
  |  12:if (= iteratorRecord.Done true) let value = undefined else 10:{}
  |  19:if (&& (! (= Initializer absent)) (= value undefined)) {
  |    16:let __x5__ = true
  |    16:app __x6__ = (IsAnonymousFunctionDefinition Initializer)
  |    16:__x5__ = (= __x6__ true)
  |    16:if __x5__ {
  |      access __x7__ = (DestructuringAssignmentTarget "IsIdentifierRef")
  |      __x5__ = (= __x7__ true)
  |    } else 10:{}
  |    16:if __x5__ {
  |      15:access __x8__ = (Initializer "NamedEvaluation" lref.ReferencedName)
  |      15:let v = [? __x8__]
  |    } else {
  |      17:access __x9__ = (Initializer "Evaluation")
  |      17:let defaultValue = __x9__
  |      18:app __x10__ = (GetValue defaultValue)
  |      18:let v = [? __x10__]
  |    }
  |  } else let v = value
  |  20:if (|| (is-instance-of DestructuringAssignmentTarget ObjectLiteral) (is-instance-of DestructuringAssignmentTarget ArrayLiteral)) {
  |    21:let nestedAssignmentPattern = (parse-syntax DestructuringAssignmentTarget "AssignmentPattern" (new []))
  |    22:access __x11__ = (nestedAssignmentPattern "DestructuringAssignmentEvaluation" v)
  |    22:return __x11__
  |  } else 10:{}
  |  23:app __x12__ = (PutValue lref v)
  |  23:return [? __x12__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If |DestructuringAssignmentTarget| is neither an |ObjectLiteral| nor an |ArrayLiteral|, then""",
    """            1. Let _lref_ be the result of evaluating |DestructuringAssignmentTarget|.""",
    """            1. ReturnIfAbrupt(_lref_).""",
    """          1. If _iteratorRecord_.[[Done]] is *false*, then""",
    """            1. Let _next_ be IteratorStep(_iteratorRecord_).""",
    """            1. If _next_ is an abrupt completion, set _iteratorRecord_.[[Done]] to *true*.""",
    """            1. ReturnIfAbrupt(_next_).""",
    """            1. If _next_ is *false*, set _iteratorRecord_.[[Done]] to *true*.""",
    """            1. Else,""",
    """              1. Let _value_ be IteratorValue(_next_).""",
    """              1. If _value_ is an abrupt completion, set _iteratorRecord_.[[Done]] to *true*.""",
    """              1. ReturnIfAbrupt(_value_).""",
    """          1. If _iteratorRecord_.[[Done]] is *true*, let _value_ be *undefined*.""",
    """          1. If |Initializer| is present and _value_ is *undefined*, then""",
    """            1. If IsAnonymousFunctionDefinition(|Initializer|) is *true* and IsIdentifierRef of |DestructuringAssignmentTarget| is *true*, then""",
    """              1. Let _v_ be ? NamedEvaluation of |Initializer| with argument _lref_.[[ReferencedName]].""",
    """            1. Else,""",
    """              1. Let _defaultValue_ be the result of evaluating |Initializer|.""",
    """              1. Let _v_ be ? GetValue(_defaultValue_).""",
    """          1. Else, let _v_ be _value_.""",
    """          1. If |DestructuringAssignmentTarget| is an |ObjectLiteral| or an |ArrayLiteral|, then""",
    """            1. Let _nestedAssignmentPattern_ be the |AssignmentPattern| that is covered by |DestructuringAssignmentTarget|.""",
    """            1. Return the result of performing DestructuringAssignmentEvaluation of _nestedAssignmentPattern_ with _v_ as the argument.""",
    """          1. Return ? PutValue(_lref_, _v_).""",
  )
}
