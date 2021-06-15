package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SuperProperty[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("SuperProperty", 0, 0, Rhs(List(Terminal("super"), Terminal("["), NonTerminal("Expression", List(""), false), Terminal("]")), None), "Evaluation", List())
  val ids = List(
    "sec-super-keyword-runtime-semantics-evaluation",
    "sec-super-keyword",
    "sec-left-hand-side-expressions",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (GetThisEnvironment)
  |  0:let env = __x0__
  |  1:app __x1__ = (env.GetThisBinding env)
  |  1:let actualThis = [? __x1__]
  |  2:access __x2__ = (Expression "Evaluation")
  |  2:let propertyNameReference = __x2__
  |  3:app __x3__ = (GetValue propertyNameReference)
  |  3:let propertyNameValue = [? __x3__]
  |  4:app __x4__ = (ToPropertyKey propertyNameValue)
  |  4:let propertyKey = [? __x4__]
  |  5:if true let strict = true else let strict = false
  |  6:app __x5__ = (MakeSuperPropertyReference actualThis propertyKey strict)
  |  6:return [? __x5__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _env_ be GetThisEnvironment().""",
    """          1. Let _actualThis_ be ? _env_.GetThisBinding().""",
    """          1. Let _propertyNameReference_ be the result of evaluating |Expression|.""",
    """          1. Let _propertyNameValue_ be ? GetValue(_propertyNameReference_).""",
    """          1. Let _propertyKey_ be ? ToPropertyKey(_propertyNameValue_).""",
    """          1. If the code matched by this |SuperProperty| is strict mode code, let _strict_ be *true*; else let _strict_ be *false*.""",
    """          1. Return ? MakeSuperPropertyReference(_actualThis_, _propertyKey_, _strict_).""",
  )
}
