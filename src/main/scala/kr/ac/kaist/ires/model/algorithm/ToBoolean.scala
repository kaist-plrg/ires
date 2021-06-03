package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ToBoolean` extends Algo {
  val head = NormalHead("ToBoolean", List(Param("argument", Normal)))
  val ids = List(
    "table-toboolean-conversions",
    "sec-toboolean",
    "sec-type-conversion",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  1:if (= (typeof argument) Undefined) return false else 2:{}
  |  3:if (= (typeof argument) Null) return false else 2:{}
  |  5:if (= (typeof argument) Boolean) return argument else 2:{}
  |  7:if (= (typeof argument) Number) if (|| (|| (= argument 0i) (= argument -0.0)) (= argument NaN)) return false else return true else 2:{}
  |  9:if (= (typeof argument) String) if (= argument "") return false else return true else 2:{}
  |  11:if (= (typeof argument) Symbol) return true else 2:{}
  |  13:if (= (typeof argument) BigInt) if (= argument 0i) return false else return true else 2:{}
  |  15:if (= (typeof argument) Object) return true else 2:{}
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
    """              Return *false*.""",
    """            </td>""",
    """          </tr>""",
    """          <tr>""",
    """            <td>""",
    """              Null""",
    """            </td>""",
    """            <td>""",
    """              Return *false*.""",
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
    """              If _argument_ is *+0*<sub>𝔽</sub>, *-0*<sub>𝔽</sub>, or *NaN*, return *false*; otherwise return *true*.""",
    """            </td>""",
    """          </tr>""",
    """          <tr>""",
    """            <td>""",
    """              String""",
    """            </td>""",
    """            <td>""",
    """              If _argument_ is the empty String (its length is 0), return *false*; otherwise return *true*.""",
    """            </td>""",
    """          </tr>""",
    """          <tr>""",
    """            <td>""",
    """              Symbol""",
    """            </td>""",
    """            <td>""",
    """              Return *true*.""",
    """            </td>""",
    """          </tr>""",
    """          <tr>""",
    """            <td>""",
    """              BigInt""",
    """            </td>""",
    """            <td>""",
    """              If _argument_ is *0*<sub>ℤ</sub>, return *false*; otherwise return *true*.""",
    """            </td>""",
    """          </tr>""",
    """          <tr>""",
    """            <td>""",
    """              Object""",
    """            </td>""",
    """            <td>""",
    """              Return *true*.""",
    """              <emu-note>""",
    """                <p>An alternate algorithm related to the [[IsHTMLDDA]] internal slot is mandated in section <emu-xref href="#sec-IsHTMLDDA-internal-slot-to-boolean"></emu-xref>.</p>""",
    """              </emu-note>""",
    """            </td>""",
    """          </tr>""",
    """          </tbody>""",
    """        </table>""",
  )
}
