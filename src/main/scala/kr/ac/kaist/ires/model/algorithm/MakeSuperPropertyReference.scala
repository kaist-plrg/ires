package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::MakeSuperPropertyReference` extends Algo {
  val head = NormalHead("MakeSuperPropertyReference", List(Param("actualThis", Normal), Param("propertyKey", Normal), Param("strict", Normal)))
  val ids = List(
    "sec-makesuperpropertyreference",
    "sec-super-keyword",
    "sec-left-hand-side-expressions",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (GetThisEnvironment)
  |  0:let env = __x0__
  |  1:app __x1__ = (env.HasSuperBinding env)
  |  1:assert (= __x1__ true)
  |  2:app __x2__ = (env.GetSuperBase env)
  |  2:let baseValue = [? __x2__]
  |  3:app __x3__ = (RequireObjectCoercible baseValue)
  |  3:let bv = [? __x3__]
  |  4:return (new ReferenceRecord("Base" -> bv, "ReferencedName" -> propertyKey, "Strict" -> strict, "ThisValue" -> actualThis))
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _env_ be GetThisEnvironment().""",
    """          1. Assert: _env_.HasSuperBinding() is *true*.""",
    """          1. Let _baseValue_ be ? _env_.GetSuperBase().""",
    """          1. Let _bv_ be ? RequireObjectCoercible(_baseValue_).""",
    """          1. Return the Reference Record { [[Base]]: _bv_, [[ReferencedName]]: _propertyKey_, [[Strict]]: _strict_, [[ThisValue]]: _actualThis_ }.""",
    """          1. NOTE: This returns a Super Reference Record.""",
  )
}
