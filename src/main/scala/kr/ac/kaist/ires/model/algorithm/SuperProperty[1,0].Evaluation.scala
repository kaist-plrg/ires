package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SuperProperty[1,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("SuperProperty", 1, 0, Rhs(List(Terminal("super"), Terminal("."), NonTerminal("IdentifierName", List(""), false)), None), "Evaluation", List())
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
  |  2:access __x2__ = (IdentifierName "StringValue")
  |  2:let propertyKey = __x2__
  |  3:if true let strict = true else let strict = false
  |  4:app __x3__ = (MakeSuperPropertyReference actualThis propertyKey strict)
  |  4:return [? __x3__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _env_ be GetThisEnvironment().""",
    """          1. Let _actualThis_ be ? _env_.GetThisBinding().""",
    """          1. Let _propertyKey_ be StringValue of |IdentifierName|.""",
    """          1. If the code matched by this |SuperProperty| is strict mode code, let _strict_ be *true*; else let _strict_ be *false*.""",
    """          1. Return ? MakeSuperPropertyReference(_actualThis_, _propertyKey_, _strict_).""",
  )
}
