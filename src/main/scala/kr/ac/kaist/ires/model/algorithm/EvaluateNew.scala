package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::EvaluateNew` extends Algo {
  val head = NormalHead("EvaluateNew", List(Param("constructExpr", Normal), Param("arguments", Normal)))
  val ids = List(
    "sec-evaluatenew",
    "sec-new-operator-runtime-semantics-evaluation",
    "sec-new-operator",
    "sec-left-hand-side-expressions",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:assert (|| (is-instance-of constructExpr NewExpression) (is-instance-of constructExpr MemberExpression))
  |  1:assert (|| (= arguments CONST_empty) (is-instance-of arguments Arguments))
  |  2:access __x0__ = (constructExpr "Evaluation")
  |  2:let ref = __x0__
  |  3:app __x1__ = (GetValue ref)
  |  3:let constructor = [? __x1__]
  |  5:if (= arguments CONST_empty) let argList = (new []) else {
  |    6:access __x2__ = (arguments "ArgumentListEvaluation")
  |    6:let argList = [? __x2__]
  |  }
  |  7:app __x3__ = (IsConstructor constructor)
  |  7:if (= __x3__ false) throw TypeError else 8:{}
  |  8:app __x4__ = (Construct constructor argList)
  |  8:return [? __x4__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: _constructExpr_ is either a |NewExpression| or a |MemberExpression|.""",
    """            1. Assert: _arguments_ is either ~empty~ or an |Arguments|.""",
    """            1. Let _ref_ be the result of evaluating _constructExpr_.""",
    """            1. Let _constructor_ be ? GetValue(_ref_).""",
    """            1. If _arguments_ is ~empty~, let _argList_ be a new empty List.""",
    """            1. Else,""",
    """              1. Let _argList_ be ? ArgumentListEvaluation of _arguments_.""",
    """            1. If IsConstructor(_constructor_) is *false*, throw a *TypeError* exception.""",
    """            1. Return ? Construct(_constructor_, _argList_).""",
  )
}
