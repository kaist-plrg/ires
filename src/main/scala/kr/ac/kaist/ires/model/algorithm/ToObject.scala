package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ToObject` extends Algo {
  val head = NormalHead("ToObject", List(Param("argument", Normal)))
  val ids = List(
    "table-toobject-conversions",
    "sec-toobject",
    "sec-type-conversion",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  1:if (= (typeof argument) Undefined) throw TypeError else 8:{}
  |  3:if (= (typeof argument) Null) throw TypeError else 8:{}
  |  5:if (= (typeof argument) Boolean) {
  |    4:let obj = (new OrdinaryObject("Prototype" -> INTRINSIC_Boolean_prototype, "BooleanData" -> argument))
  |    4:return obj
  |  } else 8:{}
  |  7:if (= (typeof argument) Number) {
  |    6:let obj = (new OrdinaryObject("Prototype" -> INTRINSIC_Number_prototype, "NumberData" -> argument))
  |    6:return obj
  |  } else 8:{}
  |  9:if (= (typeof argument) String) {
  |    8:let obj = (new StringExoticObject("Prototype" -> INTRINSIC_String_prototype, "StringData" -> argument))
  |    8:return obj
  |  } else 8:{}
  |  11:if (= (typeof argument) Symbol) {
  |    10:let obj = (new OrdinaryObject("Prototype" -> INTRINSIC_Symbol_prototype, "SymbolData" -> argument))
  |    10:return obj
  |  } else 8:{}
  |  13:if (= (typeof argument) BigInt) {
  |    12:let obj = (new OrdinaryObject("Prototype" -> INTRINSIC_BigInt_prototype, "BigIntData" -> argument))
  |    12:return obj
  |  } else 8:{}
  |  15:if (= (typeof argument) Object) return argument else 8:{}
  |  16:assert false
  |}""".stripMargin)
  val code = scala.Array[String](
    """        <table>""",
    """          <tbody>""",
    """          <tr>""",
    """            <th>""",
    """              Argument Type""",
    """            </th>""",
    """            <th>""",
    """              Result""",
    """            </th>""",
    """          </tr>""",
    """          <tr>""",
    """            <td>""",
    """              Undefined""",
    """            </td>""",
    """            <td>""",
    """              Throw a *TypeError* exception.""",
    """            </td>""",
    """          </tr>""",
    """          <tr>""",
    """            <td>""",
    """              Null""",
    """            </td>""",
    """            <td>""",
    """              Throw a *TypeError* exception.""",
    """            </td>""",
    """          </tr>""",
    """          <tr>""",
    """            <td>""",
    """              Boolean""",
    """            </td>""",
    """            <td>""",
    """              Return a new Boolean object whose [[BooleanData]] internal slot is set to _argument_. See <emu-xref href="#sec-boolean-objects"></emu-xref> for a description of Boolean objects.""",
    """            </td>""",
    """          </tr>""",
    """          <tr>""",
    """            <td>""",
    """              Number""",
    """            </td>""",
    """            <td>""",
    """              Return a new Number object whose [[NumberData]] internal slot is set to _argument_. See <emu-xref href="#sec-number-objects"></emu-xref> for a description of Number objects.""",
    """            </td>""",
    """          </tr>""",
    """          <tr>""",
    """            <td>""",
    """              String""",
    """            </td>""",
    """            <td>""",
    """              Return a new String object whose [[StringData]] internal slot is set to _argument_. See <emu-xref href="#sec-string-objects"></emu-xref> for a description of String objects.""",
    """            </td>""",
    """          </tr>""",
    """          <tr>""",
    """            <td>""",
    """              Symbol""",
    """            </td>""",
    """            <td>""",
    """              Return a new Symbol object whose [[SymbolData]] internal slot is set to _argument_. See <emu-xref href="#sec-symbol-objects"></emu-xref> for a description of Symbol objects.""",
    """            </td>""",
    """          </tr>""",
    """          <tr>""",
    """            <td>""",
    """              BigInt""",
    """            </td>""",
    """            <td>""",
    """              Return a new BigInt object whose [[BigIntData]] internal slot is set to _argument_. See <emu-xref href="#sec-bigint-objects"></emu-xref> for a description of BigInt objects.""",
    """            </td>""",
    """          </tr>""",
    """          <tr>""",
    """            <td>""",
    """              Object""",
    """            </td>""",
    """            <td>""",
    """              Return _argument_.""",
    """            </td>""",
    """          </tr>""",
    """          </tbody>""",
    """        </table>""",
  )
}
