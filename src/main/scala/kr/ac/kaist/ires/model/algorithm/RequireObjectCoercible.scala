package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::RequireObjectCoercible` extends Algo {
  val head = NormalHead("RequireObjectCoercible", List(Param("argument", Normal)))
  val ids = List(
    "table-requireobjectcoercible-results",
    "sec-requireobjectcoercible",
    "sec-testing-and-comparison-operations",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  1:if (= (typeof argument) Undefined) throw TypeError else 8:{}
  |  3:if (= (typeof argument) Null) throw TypeError else 8:{}
  |  5:if (= (typeof argument) Boolean) return argument else 8:{}
  |  7:if (= (typeof argument) Number) return argument else 8:{}
  |  9:if (= (typeof argument) String) return argument else 8:{}
  |  11:if (= (typeof argument) Symbol) return argument else 8:{}
  |  13:if (= (typeof argument) BigInt) return argument else 8:{}
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
    """              Return _argument_.""",
    """            </td>""",
    """          </tr>""",
    """          <tr>""",
    """            <td>""",
    """              Number""",
    """            </td>""",
    """            <td>""",
    """              Return _argument_.""",
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
    """              Return _argument_.""",
    """            </td>""",
    """          </tr>""",
    """          <tr>""",
    """            <td>""",
    """              BigInt""",
    """            </td>""",
    """            <td>""",
    """              Return _argument_.""",
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
