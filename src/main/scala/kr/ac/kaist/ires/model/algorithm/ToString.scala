package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ToString` extends Algo {
  val head = NormalHead("ToString", List(Param("argument", Normal)))
  val ids = List(
    "table-tostring-conversions",
    "sec-tostring",
    "sec-type-conversion",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  1:if (= (typeof argument) Undefined) return "undefined" else 8:{}
  |  3:if (= (typeof argument) Null) return "null" else 8:{}
  |  6:if (= (typeof argument) Boolean) {
  |    4:if (= argument true) return "true" else 8:{}
  |    5:if (= argument false) return "false" else 8:{}
  |  } else 8:{}
  |  8:if (= (typeof argument) Number) {
  |    7:app __x0__ = (PRIMITIVE[Number].toString argument)
  |    7:return [! __x0__]
  |  } else 8:{}
  |  10:if (= (typeof argument) String) return argument else 8:{}
  |  12:if (= (typeof argument) Symbol) throw TypeError else 8:{}
  |  14:if (= (typeof argument) BigInt) {
  |    13:app __x1__ = (PRIMITIVE[BigInt].toString argument)
  |    13:return [! __x1__]
  |  } else 8:{}
  |  17:if (= (typeof argument) Object) {
  |    15:app __x2__ = (ToPrimitive argument CONST_string)
  |    15:let primValue = [? __x2__]
  |    16:app __x3__ = (ToString primValue)
  |    16:return [? __x3__]
  |  } else 8:{}
  |  18:assert false
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
    """              Return *"undefined"*.""",
    """            </td>""",
    """          </tr>""",
    """          <tr>""",
    """            <td>""",
    """              Null""",
    """            </td>""",
    """            <td>""",
    """              Return *"null"*.""",
    """            </td>""",
    """          </tr>""",
    """          <tr>""",
    """            <td>""",
    """              Boolean""",
    """            </td>""",
    """            <td>""",
    """              <p>If _argument_ is *true*, return *"true"*.</p>""",
    """              <p>If _argument_ is *false*, return *"false"*.</p>""",
    """            </td>""",
    """          </tr>""",
    """          <tr>""",
    """            <td>""",
    """              Number""",
    """            </td>""",
    """            <td>""",
    """              Return ! Number::toString(_argument_).""",
    """            </td>""",
    """          </tr>""",
    """          <tr>""",
    """            <td>""",
    """              String""",
    """            </td>""",
    """            <td>""",
    """              Return _argument_.""",
    """            </td>""",
    """          </tr>""",
    """          <tr>""",
    """            <td>""",
    """              Symbol""",
    """            </td>""",
    """            <td>""",
    """              Throw a *TypeError* exception.""",
    """            </td>""",
    """          </tr>""",
    """          <tr>""",
    """            <td>""",
    """              BigInt""",
    """            </td>""",
    """            <td>""",
    """              Return ! BigInt::toString(_argument_).""",
    """            </td>""",
    """          </tr>""",
    """          <tr>""",
    """            <td>""",
    """              Object""",
    """            </td>""",
    """            <td>""",
    """              <p>Apply the following steps:</p>""",
    """              <emu-alg>""",
    """                1. Let _primValue_ be ? ToPrimitive(_argument_, ~string~).""",
    """                1. Return ? ToString(_primValue_).""",
    """              </emu-alg>""",
    """            </td>""",
    """          </tr>""",
    """          </tbody>""",
    """        </table>""",
  )
}
