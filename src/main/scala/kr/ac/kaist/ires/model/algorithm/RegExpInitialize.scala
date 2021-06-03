package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::RegExpInitialize` extends Algo {
  val head = NormalHead("RegExpInitialize", List(Param("obj", Normal), Param("pattern", Normal), Param("flags", Normal)))
  val ids = List(
    "sec-regexpinitialize",
    "sec-abstract-operations-for-the-regexp-constructor",
    "sec-regexp-constructor",
    "sec-regexp-regular-expression-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  1:if (= pattern undefined) let P = "" else {
  |    app __x0__ = (ToString pattern)
  |    let P = [? __x0__]
  |  }
  |  3:if (= flags undefined) let F = "" else {
  |    app __x1__ = (ToString flags)
  |    let F = [? __x1__]
  |  }
  |  4:??? "If id:{F} contains any code unit other than value:{\"g\"} , value:{\"i\"} , value:{\"m\"} , value:{\"s\"} , value:{\"u\"} , or value:{\"y\"} or if it contains the same code unit more than once , throw a value:{SyntaxError} exception ."
  |  5:if (contains F "u") let u = true else let u = false
  |  9:if (= u true) {
  |    7:app __x2__ = (StringToCodePoints P)
  |    7:let patternText = [! __x2__]
  |    8:let patternCharacters = StrList
  |  } else {
  |    10:??? "Let id:{patternText} be the result of interpreting each of id:{P} ' s 16 - bit elements as a Unicode BMP code point . UTF - 16 decoding is not applied to the elements ."
  |    11:let patternCharacters = StrList
  |  }
  |  12:app __x3__ = (ParsePattern patternText u)
  |  12:let parseResult = __x3__
  |  13:??? "If id:{parseResult} is a non - empty List of value:{SyntaxError} objects , throw a value:{SyntaxError} exception ."
  |  15:obj.OriginalSource = P
  |  16:obj.OriginalFlags = F
  |  17:obj.RegExpMatcher = null
  |  18:app __x4__ = (Set obj "lastIndex" 0i true)
  |  18:[? __x4__]
  |  19:return obj
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. If _pattern_ is *undefined*, let _P_ be the empty String.""",
    """            1. Else, let _P_ be ? ToString(_pattern_).""",
    """            1. If _flags_ is *undefined*, let _F_ be the empty String.""",
    """            1. Else, let _F_ be ? ToString(_flags_).""",
    """            1. If _F_ contains any code unit other than *"g"*, *"i"*, *"m"*, *"s"*, *"u"*, or *"y"* or if it contains the same code unit more than once, throw a *SyntaxError* exception.""",
    """            1. If _F_ contains *"u"*, let _u_ be *true*; else let _u_ be *false*.""",
    """            1. If _u_ is *true*, then""",
    """              1. Let _patternText_ be ! StringToCodePoints(_P_).""",
    """              1. Let _patternCharacters_ be a List whose elements are the code points of _patternText_.""",
    """            1. Else,""",
    """              1. Let _patternText_ be the result of interpreting each of _P_'s 16-bit elements as a Unicode BMP code point. UTF-16 decoding is not applied to the elements.""",
    """              1. Let _patternCharacters_ be a List whose elements are the code unit elements of _P_.""",
    """            1. Let _parseResult_ be ParsePattern(_patternText_, _u_).""",
    """            1. If _parseResult_ is a non-empty List of *SyntaxError* objects, throw a *SyntaxError* exception.""",
    """            1. Assert: _parseResult_ is a Parse Node for |Pattern|.""",
    """            1. Set _obj_.[[OriginalSource]] to _P_.""",
    """            1. Set _obj_.[[OriginalFlags]] to _F_.""",
    """            1. Set _obj_.[[RegExpMatcher]] to the Abstract Closure that evaluates _parseResult_ by applying the semantics provided in <emu-xref href="#sec-pattern-semantics"></emu-xref> using _patternCharacters_ as the pattern's List of |SourceCharacter| values and _F_ as the flag parameters.""",
    """            1. Perform ? Set(_obj_, *"lastIndex"*, *+0*<sub>𝔽</sub>, *true*).""",
    """            1. Return _obj_.""",
  )
}
