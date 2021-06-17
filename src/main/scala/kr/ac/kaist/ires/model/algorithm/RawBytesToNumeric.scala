package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::RawBytesToNumeric` extends Algo {
  val head = NormalHead("RawBytesToNumeric", List(Param("type", Normal), Param("rawBytes", Normal), Param("isLittleEndian", Normal)))
  val ids = List(
    "sec-rawbytestonumeric",
    "sec-abstract-operations-for-arraybuffer-objects",
    "sec-arraybuffer-objects",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:??? "Let id:{elementSize} be the Element Size value specified in link:{table-the-typedarray-constructors} for Element Type id:{type} ."
  |  1:??? "If id:{isLittleEndian} is value:{false} , reverse the order of the elements of id:{rawBytes} ."
  |  2:if (= type CONST_Float32) {
  |    3:??? "Let id:{value} be the byte elements of id:{rawBytes} concatenated and interpreted as a little - endian bit string encoding of an IEEE 754 - 2019 binary32 value ."
  |    4:??? "If id:{value} is an IEEE 754 - 2019 binary32 NaN value , return the value:{NaN} Number value ."
  |    5:return value
  |  } else 0:{}
  |  6:if (= type CONST_Float64) {
  |    7:??? "Let id:{value} be the byte elements of id:{rawBytes} concatenated and interpreted as a little - endian bit string encoding of an IEEE 754 - 2019 binary64 value ."
  |    8:??? "If id:{value} is an IEEE 754 - 2019 binary64 NaN value , return the value:{NaN} Number value ."
  |    9:return value
  |  } else 0:{}
  |  12:app __x0__ = (IsUnsignedElementType type)
  |  12:if (= [! __x0__] true) ??? "Let id:{intValue} be the byte elements of id:{rawBytes} concatenated and interpreted as a bit string encoding of an unsigned little - endian binary number ." else ??? "Let id:{intValue} be the byte elements of id:{rawBytes} concatenated and interpreted as a bit string encoding of a binary little - endian two ' s complement number of bit length id:{elementSize} × 8 ."
  |  14:??? "If ! IsBigIntElementType ( id:{type} ) is value:{true} , return the BigInt value that corresponds to id:{intValue} ."
  |  15:??? "Otherwise , return the Number value that corresponds to id:{intValue} ."
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _elementSize_ be the Element Size value specified in <emu-xref href="#table-the-typedarray-constructors"></emu-xref> for Element Type _type_.""",
    """          1. If _isLittleEndian_ is *false*, reverse the order of the elements of _rawBytes_.""",
    """          1. If _type_ is ~Float32~, then""",
    """            1. Let _value_ be the byte elements of _rawBytes_ concatenated and interpreted as a little-endian bit string encoding of an IEEE 754-2019 binary32 value.""",
    """            1. If _value_ is an IEEE 754-2019 binary32 NaN value, return the *NaN* Number value.""",
    """            1. Return the Number value that corresponds to _value_.""",
    """          1. If _type_ is ~Float64~, then""",
    """            1. Let _value_ be the byte elements of _rawBytes_ concatenated and interpreted as a little-endian bit string encoding of an IEEE 754-2019 binary64 value.""",
    """            1. If _value_ is an IEEE 754-2019 binary64 NaN value, return the *NaN* Number value.""",
    """            1. Return the Number value that corresponds to _value_.""",
    """          1. If ! IsUnsignedElementType(_type_) is *true*, then""",
    """            1. Let _intValue_ be the byte elements of _rawBytes_ concatenated and interpreted as a bit string encoding of an unsigned little-endian binary number.""",
    """          1. Else,""",
    """            1. Let _intValue_ be the byte elements of _rawBytes_ concatenated and interpreted as a bit string encoding of a binary little-endian two's complement number of bit length _elementSize_ × 8.""",
    """          1. If ! IsBigIntElementType(_type_) is *true*, return the BigInt value that corresponds to _intValue_.""",
    """          1. Otherwise, return the Number value that corresponds to _intValue_.""",
  )
}
