package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::TypedArraySpeciesCreate` extends Algo {
  val head = NormalHead("TypedArraySpeciesCreate", List(Param("exemplar", Normal), Param("argumentList", Normal)))
  val ids = List(
    "typedarray-species-create",
    "sec-abstract-operations-for-typedarray-objects",
    "sec-typedarray-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  1:??? "Let id:{defaultConstructor} be the intrinsic object listed in column one of link:{unhandled: table-the-typedarray-constructors} for id:{exemplar} . [ [ TypedArrayName ] ] ."
  |  2:app __x0__ = (SpeciesConstructor exemplar defaultConstructor)
  |  2:let constructor = [? __x0__]
  |  3:app __x1__ = (TypedArrayCreate constructor argumentList)
  |  3:let result = [? __x1__]
  |  5:if (! (== result.ContentType exemplar.ContentType)) throw TypeError else 4:{}
  |  6:return result
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: _exemplar_ is an Object that has [[TypedArrayName]] and [[ContentType]] internal slots.""",
    """          1. Let _defaultConstructor_ be the intrinsic object listed in column one of <emu-xref href="#table-the-typedarray-constructors"></emu-xref> for _exemplar_.[[TypedArrayName]].""",
    """          1. Let _constructor_ be ? SpeciesConstructor(_exemplar_, _defaultConstructor_).""",
    """          1. Let _result_ be ? TypedArrayCreate(_constructor_, _argumentList_).""",
    """          1. Assert: _result_ has [[TypedArrayName]] and [[ContentType]] internal slots.""",
    """          1. If _result_.[[ContentType]] ≠ _exemplar_.[[ContentType]], throw a *TypeError* exception.""",
    """          1. Return _result_.""",
  )
}
