package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.parseFloat` extends Algo {
  val head = BuiltinHead(parseRef("""parseFloat"""), List(Param("string", Normal)))
  val ids = List(
    "sec-parsefloat-string",
    "sec-function-properties-of-the-global-object",
    "sec-global-object",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToString string)
  |  0:let inputString = [? __x0__]
  |  1:app __x1__ = (TrimString inputString CONST_start)
  |  1:let trimmedString = [! __x1__]
  |  2:??? "If neither id:{trimmedString} nor any prefix of id:{trimmedString} satisfies the syntax of a nt:{StrDecimalLiteral} ( see link:{sec-tonumber-applied-to-the-string-type} ) , return value:{NaN} ."
  |  3:??? "Let id:{numberString} be the longest prefix of id:{trimmedString} , which might be id:{trimmedString} itself , that satisfies the syntax of a nt:{StrDecimalLiteral} ."
  |  4:access __x2__ = (numberString "MV")
  |  4:let mathFloat = __x2__
  |  5:if (== mathFloat 0i) {
  |    6:??? "If the first code unit of id:{trimmedString} is the code unit 0x002D ( HYPHEN - MINUS ) , return value:{-0} ."
  |    7:return 0i
  |  } else 71:{}
  |  8:return mathFloat
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _inputString_ be ? ToString(_string_).""",
    """        1. Let _trimmedString_ be ! TrimString(_inputString_, ~start~).""",
    """        1. If neither _trimmedString_ nor any prefix of _trimmedString_ satisfies the syntax of a |StrDecimalLiteral| (see <emu-xref href="#sec-tonumber-applied-to-the-string-type"></emu-xref>), return *NaN*.""",
    """        1. Let _numberString_ be the longest prefix of _trimmedString_, which might be _trimmedString_ itself, that satisfies the syntax of a |StrDecimalLiteral|.""",
    """        1. Let _mathFloat_ be MV of _numberString_.""",
    """        1. If _mathFloat_ = 0, then""",
    """          1. If the first code unit of _trimmedString_ is the code unit 0x002D (HYPHEN-MINUS), return *-0*<sub>𝔽</sub>.""",
    """          1. Return *+0*<sub>𝔽</sub>.""",
    """        1. Return 𝔽(_mathFloat_).""",
  )
}
