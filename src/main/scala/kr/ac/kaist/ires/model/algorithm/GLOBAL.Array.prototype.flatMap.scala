package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Array.prototype.flatMap` extends Algo {
  val head = BuiltinHead(parseRef("""Array.prototype.flatMap"""), List(Param("mapperFunction", Normal), Param("thisArg", Optional)))
  val ids = List(
    "sec-array.prototype.flatmap",
    "sec-properties-of-the-array-prototype-object",
    "sec-array-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToObject this)
  |  0:let O = [? __x0__]
  |  1:app __x1__ = (LengthOfArrayLike O)
  |  1:let sourceLen = [? __x1__]
  |  2:app __x2__ = (IsCallable mapperFunction)
  |  2:if (= [! __x2__] false) throw TypeError else 2:{}
  |  3:app __x3__ = (ArraySpeciesCreate O 0i)
  |  3:let A = [? __x3__]
  |  4:app __x4__ = (FlattenIntoArray A O sourceLen 0i 1i mapperFunction thisArg)
  |  4:[? __x4__]
  |  5:return A
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? ToObject(*this* value).""",
    """          1. Let _sourceLen_ be ? LengthOfArrayLike(_O_).""",
    """          1. If ! IsCallable(_mapperFunction_) is *false*, throw a *TypeError* exception.""",
    """          1. Let _A_ be ? ArraySpeciesCreate(_O_, 0).""",
    """          1. Perform ? FlattenIntoArray(_A_, _O_, _sourceLen_, 0, 1, _mapperFunction_, _thisArg_).""",
    """          1. Return _A_.""",
  )
}
