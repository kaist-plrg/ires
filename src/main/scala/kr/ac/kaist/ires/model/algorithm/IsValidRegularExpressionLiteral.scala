package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IsValidRegularExpressionLiteral` extends Algo {
  val head = NormalHead("IsValidRegularExpressionLiteral", List(Param("literal", Normal)))
  val ids = List(
    "sec-isvalidregularexpressionliteral",
    "sec-primary-expression-regular-expression-literals",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:assert (is-instance-of literal RegularExpressionLiteral)
  |  1:??? "If FlagText of id:{literal} contains any code points other than code:{g} , code:{i} , code:{m} , code:{s} , code:{u} , or code:{y} , or if it contains the same code point more than once , return value:{false} ."
  |  2:access __x0__ = (literal "BodyText")
  |  2:let patternText = __x0__
  |  3:access __x1__ = (literal "FlagText")
  |  3:if (contains __x1__ ??? "u") let u = true else let u = false
  |  4:if (= u false) {
  |    5:app __x2__ = (CodePointsToString patternText)
  |    5:let stringValue = __x2__
  |    6:??? "Set id:{patternText} to the sequence of code points resulting from interpreting each of the 16 - bit elements of id:{stringValue} as a Unicode BMP code point . UTF - 16 decoding is not applied to the elements ."
  |  } else 8:{}
  |  7:app __x3__ = (ParsePattern patternText u)
  |  7:let parseResult = __x3__
  |  8:??? "If id:{parseResult} is a Parse Node , return value:{true} ; else return value:{false} ."
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: _literal_ is a |RegularExpressionLiteral|.""",
    """          1. If FlagText of _literal_ contains any code points other than `g`, `i`, `m`, `s`, `u`, or `y`, or if it contains the same code point more than once, return *false*.""",
    """          1. Let _patternText_ be BodyText of _literal_.""",
    """          1. If FlagText of _literal_ contains `u`, let _u_ be *true*; else let _u_ be *false*.""",
    """          1. If _u_ is *false*, then""",
    """            1. Let _stringValue_ be CodePointsToString(_patternText_).""",
    """            1. Set _patternText_ to the sequence of code points resulting from interpreting each of the 16-bit elements of _stringValue_ as a Unicode BMP code point. UTF-16 decoding is not applied to the elements.""",
    """          1. Let _parseResult_ be ParsePattern(_patternText_, _u_).""",
    """          1. If _parseResult_ is a Parse Node, return *true*; else return *false*.""",
  )
}
