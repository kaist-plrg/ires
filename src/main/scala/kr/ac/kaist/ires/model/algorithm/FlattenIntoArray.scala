package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FlattenIntoArray` extends Algo {
  val head = NormalHead("FlattenIntoArray", List(Param("target", Normal), Param("source", Normal), Param("sourceLen", Normal), Param("start", Normal), Param("depth", Normal), Param("mapperFunction", Optional), Param("thisArg", Optional)))
  val ids = List(
    "sec-flattenintoarray",
    "sec-array.prototype.flat",
    "sec-properties-of-the-array-prototype-object",
    "sec-array-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof target) Object)
  |  1:assert (= (typeof source) Object)
  |  3:let targetIndex = start
  |  4:let sourceIndex = 0i
  |  5:while (< sourceIndex sourceLen) {
  |    6:app __x0__ = (ToString sourceIndex)
  |    6:let P = [! __x0__]
  |    7:app __x1__ = (HasProperty source P)
  |    7:let exists = [? __x1__]
  |    8:if (= exists true) {
  |      9:app __x2__ = (Get source P)
  |      9:let element = [? __x2__]
  |      10:if (! (= mapperFunction absent)) {
  |        11:app __x3__ = (Call mapperFunction thisArg (new [element, sourceIndex, source]))
  |        11:element = [? __x3__]
  |      } else 2:{}
  |      12:let shouldFlatten = false
  |      13:if (< 0i depth) {
  |        14:app __x4__ = (IsArray element)
  |        14:shouldFlatten = [? __x4__]
  |      } else 2:{}
  |      20:if (= shouldFlatten true) {
  |        17:if (= depth Infinity) let newDepth = Infinity else let newDepth = (- depth 1i)
  |        18:app __x5__ = (LengthOfArrayLike element)
  |        18:let elementLen = [? __x5__]
  |        19:app __x6__ = (FlattenIntoArray target element elementLen targetIndex newDepth)
  |        19:targetIndex = [? __x6__]
  |      } else {
  |        21:if (! (< targetIndex (- (** 2.0 53i) 1i))) throw TypeError else 2:{}
  |        22:app __x7__ = (ToString targetIndex)
  |        22:app __x8__ = (CreateDataPropertyOrThrow target [! __x7__] element)
  |        22:[? __x8__]
  |        23:targetIndex = (+ targetIndex 1i)
  |      }
  |    } else 2:{}
  |    24:sourceIndex = (+ sourceIndex 1i)
  |  }
  |  25:return targetIndex
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: Type(_target_) is Object.""",
    """            1. Assert: Type(_source_) is Object.""",
    """            1. Assert: If _mapperFunction_ is present, then ! IsCallable(_mapperFunction_) is *true*, _thisArg_ is present, and _depth_ is 1.""",
    """            1. Let _targetIndex_ be _start_.""",
    """            1. Let _sourceIndex_ be *+0*<sub>𝔽</sub>.""",
    """            1. Repeat, while ℝ(_sourceIndex_) < _sourceLen_,""",
    """              1. Let _P_ be ! ToString(_sourceIndex_).""",
    """              1. Let _exists_ be ? HasProperty(_source_, _P_).""",
    """              1. If _exists_ is *true*, then""",
    """                1. Let _element_ be ? Get(_source_, _P_).""",
    """                1. If _mapperFunction_ is present, then""",
    """                  1. Set _element_ to ? Call(_mapperFunction_, _thisArg_, « _element_, _sourceIndex_, _source_ »).""",
    """                1. Let _shouldFlatten_ be *false*.""",
    """                1. If _depth_ > 0, then""",
    """                  1. Set _shouldFlatten_ to ? IsArray(_element_).""",
    """                1. If _shouldFlatten_ is *true*, then""",
    """                  1. If _depth_ is +∞, let _newDepth_ be +∞.""",
    """                  1. Else, let _newDepth_ be _depth_ - 1.""",
    """                  1. Let _elementLen_ be ? LengthOfArrayLike(_element_).""",
    """                  1. Set _targetIndex_ to ? FlattenIntoArray(_target_, _element_, _elementLen_, _targetIndex_, _newDepth_).""",
    """                1. Else,""",
    """                  1. If _targetIndex_ ≥ 2<sup>53</sup> - 1, throw a *TypeError* exception.""",
    """                  1. Perform ? CreateDataPropertyOrThrow(_target_, ! ToString(𝔽(_targetIndex_)), _element_).""",
    """                  1. Set _targetIndex_ to _targetIndex_ + 1.""",
    """              1. Set _sourceIndex_ to _sourceIndex_ + *1*<sub>𝔽</sub>.""",
    """            1. Return _targetIndex_.""",
  )
}
