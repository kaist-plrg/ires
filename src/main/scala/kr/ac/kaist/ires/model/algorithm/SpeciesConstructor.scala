package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SpeciesConstructor` extends Algo {
  val head = NormalHead("SpeciesConstructor", List(Param("O", Normal), Param("defaultConstructor", Normal)))
  val ids = List(
    "sec-speciesconstructor",
    "sec-operations-on-objects",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof O) Object)
  |  1:app __x0__ = (Get O "constructor")
  |  1:let C = [? __x0__]
  |  2:if (= C undefined) return defaultConstructor else 0:{}
  |  3:if (! (= (typeof C) Object)) throw TypeError else 0:{}
  |  4:app __x1__ = (Get C SYMBOL_species)
  |  4:let S = [? __x1__]
  |  5:if (|| (= S undefined) (= S null)) return defaultConstructor else 0:{}
  |  6:app __x2__ = (IsConstructor S)
  |  6:if (= __x2__ true) return S else 0:{}
  |  7:throw TypeError
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: Type(_O_) is Object.""",
    """        1. Let _C_ be ? Get(_O_, *"constructor"*).""",
    """        1. If _C_ is *undefined*, return _defaultConstructor_.""",
    """        1. If Type(_C_) is not Object, throw a *TypeError* exception.""",
    """        1. Let _S_ be ? Get(_C_, @@species).""",
    """        1. If _S_ is either *undefined* or *null*, return _defaultConstructor_.""",
    """        1. If IsConstructor(_S_) is *true*, return _S_.""",
    """        1. Throw a *TypeError* exception.""",
  )
}
