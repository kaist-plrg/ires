package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GetTemplateObject` extends Algo {
  val head = NormalHead("GetTemplateObject", List(Param("templateLiteral", Normal)))
  val ids = List(
    "sec-gettemplateobject",
    "sec-template-literals",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:let realm = REALM
  |  1:let templateRegistry = realm.TemplateMap
  |  2:let __x0__ = templateRegistry
  |  2:let __x1__ = 0i
  |  2:while (< __x1__ __x0__.length) {
  |    let e = __x0__[__x1__]
  |    3:??? "If id:{e} . [ [ Site ] ] is the same Parse Node as id:{templateLiteral} , then in:{} out:{}"
  |    __x1__ = (+ __x1__ 1i)
  |  }
  |  5:access __x2__ = (templateLiteral "TemplateStrings" true)
  |  5:let rawStrings = __x2__
  |  6:access __x3__ = (templateLiteral "TemplateStrings" false)
  |  6:let cookedStrings = __x3__
  |  7:let count = cookedStrings.length
  |  8:assert (! (< (- (** 2.0 32i) 1i) count))
  |  9:app __x4__ = (ArrayCreate count)
  |  9:let template = [! __x4__]
  |  10:app __x5__ = (ArrayCreate count)
  |  10:let rawObj = [! __x5__]
  |  11:let index = 0i
  |  12:while (< index count) {
  |    13:app __x6__ = (ToString index)
  |    13:let prop = [! __x6__]
  |    14:let cookedValue = cookedStrings[index]
  |    15:app __x7__ = (DefinePropertyOrThrow template prop (new PropertyDescriptor("Value" -> cookedValue, "Writable" -> false, "Enumerable" -> true, "Configurable" -> false)))
  |    15:[! __x7__]
  |    16:let rawValue = rawStrings[index]
  |    17:app __x8__ = (DefinePropertyOrThrow rawObj prop (new PropertyDescriptor("Value" -> rawValue, "Writable" -> false, "Enumerable" -> true, "Configurable" -> false)))
  |    17:[! __x8__]
  |    18:index = (+ index 1i)
  |  }
  |  19:app __x9__ = (SetIntegrityLevel rawObj CONST_frozen)
  |  19:[! __x9__]
  |  20:app __x10__ = (DefinePropertyOrThrow template "raw" (new PropertyDescriptor("Value" -> rawObj, "Writable" -> false, "Enumerable" -> false, "Configurable" -> false)))
  |  20:[! __x10__]
  |  21:app __x11__ = (SetIntegrityLevel template CONST_frozen)
  |  21:[! __x11__]
  |  22:append (new Record("Site" -> templateLiteral, "Array" -> template)) -> templateRegistry
  |  23:return template
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _realm_ be the current Realm Record.""",
    """          1. Let _templateRegistry_ be _realm_.[[TemplateMap]].""",
    """          1. For each element _e_ of _templateRegistry_, do""",
    """            1. If _e_.[[Site]] is the same Parse Node as _templateLiteral_, then""",
    """              1. Return _e_.[[Array]].""",
    """          1. Let _rawStrings_ be TemplateStrings of _templateLiteral_ with argument *true*.""",
    """          1. Let _cookedStrings_ be TemplateStrings of _templateLiteral_ with argument *false*.""",
    """          1. Let _count_ be the number of elements in the List _cookedStrings_.""",
    """          1. Assert: _count_ ≤ 2<sup>32</sup> - 1.""",
    """          1. Let _template_ be ! ArrayCreate(_count_).""",
    """          1. Let _rawObj_ be ! ArrayCreate(_count_).""",
    """          1. Let _index_ be 0.""",
    """          1. Repeat, while _index_ < _count_,""",
    """            1. Let _prop_ be ! ToString(𝔽(_index_)).""",
    """            1. Let _cookedValue_ be _cookedStrings_[_index_].""",
    """            1. Perform ! DefinePropertyOrThrow(_template_, _prop_, PropertyDescriptor { [[Value]]: _cookedValue_, [[Writable]]: *false*, [[Enumerable]]: *true*, [[Configurable]]: *false* }).""",
    """            1. Let _rawValue_ be the String value _rawStrings_[_index_].""",
    """            1. Perform ! DefinePropertyOrThrow(_rawObj_, _prop_, PropertyDescriptor { [[Value]]: _rawValue_, [[Writable]]: *false*, [[Enumerable]]: *true*, [[Configurable]]: *false* }).""",
    """            1. Set _index_ to _index_ + 1.""",
    """          1. Perform ! SetIntegrityLevel(_rawObj_, ~frozen~).""",
    """          1. Perform ! DefinePropertyOrThrow(_template_, *"raw"*, PropertyDescriptor { [[Value]]: _rawObj_, [[Writable]]: *false*, [[Enumerable]]: *false*, [[Configurable]]: *false* }).""",
    """          1. Perform ! SetIntegrityLevel(_template_, ~frozen~).""",
    """          1. Append the Record { [[Site]]: _templateLiteral_, [[Array]]: _template_ } to _templateRegistry_.""",
    """          1. Return _template_.""",
  )
}
