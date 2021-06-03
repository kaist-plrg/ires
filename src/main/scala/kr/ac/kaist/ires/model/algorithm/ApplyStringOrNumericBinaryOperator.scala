package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ApplyStringOrNumericBinaryOperator` extends Algo {
  val head = NormalHead("ApplyStringOrNumericBinaryOperator", List(Param("lval", Normal), Param("opText", Normal), Param("rval", Normal)))
  val ids = List(
    "sec-applystringornumericbinaryoperator",
    "sec-assignment-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  1:if (= opText "+") {
  |    2:app __x0__ = (ToPrimitive lval)
  |    2:let lprim = [? __x0__]
  |    3:app __x1__ = (ToPrimitive rval)
  |    3:let rprim = [? __x1__]
  |    4:if (|| (= (typeof lprim) String) (= (typeof rprim) String)) {
  |      5:app __x2__ = (ToString lprim)
  |      5:let lstr = [? __x2__]
  |      6:app __x3__ = (ToString rprim)
  |      6:let rstr = [? __x3__]
  |      7:return (+ lstr rstr)
  |    } else 10:{}
  |    8:lval = lprim
  |    9:rval = rprim
  |  } else 10:{}
  |  11:app __x4__ = (ToNumeric lval)
  |  11:let lnum = [? __x4__]
  |  12:app __x5__ = (ToNumeric rval)
  |  12:let rnum = [? __x5__]
  |  13:if (! (= (typeof lnum) (typeof rnum))) throw TypeError else 10:{}
  |  14:let T = (typeof lnum)
  |  15:let m = PRIMITIVE[T]
  |  15:if (= opText "**") let operation = m.exponentiate else if (= opText "*") let operation = m.multiply else if (= opText "/") let operation = m.divide else if (= opText "%") let operation = m.remainder else if (= opText "+") let operation = m.add else if (= opText "-") let operation = m.subtract else if (= opText "<<") let operation = m.leftShift else if (= opText ">>") let operation = m.signedRightShift else if (= opText ">>>") let operation = m.unsignedRightShift else if (= opText "&") let operation = m.bitwiseAND else if (= opText "^") let operation = m.bitwiseXOR else if (= opText "|") let operation = m.bitwiseOR else return 0i
  |  35:app __x6__ = (operation lnum rnum)
  |  35:return [? __x6__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: _opText_ is present in the table in step <emu-xref href="#step-applystringornumericbinaryoperator-operations-table"></emu-xref>.""",
    """        1. If _opText_ is `+`, then""",
    """          1. [id="step-binary-op-toprimitive-lval"] Let _lprim_ be ? ToPrimitive(_lval_).""",
    """          1. [id="step-binary-op-toprimitive-rval"] Let _rprim_ be ? ToPrimitive(_rval_).""",
    """          1. [id="step-binary-op-string-check"] If Type(_lprim_) is String or Type(_rprim_) is String, then""",
    """            1. Let _lstr_ be ? ToString(_lprim_).""",
    """            1. Let _rstr_ be ? ToString(_rprim_).""",
    """            1. Return the string-concatenation of _lstr_ and _rstr_.""",
    """          1. Set _lval_ to _lprim_.""",
    """          1. Set _rval_ to _rprim_.""",
    """        1. NOTE: At this point, it must be a numeric operation.""",
    """        1. Let _lnum_ be ? ToNumeric(_lval_).""",
    """        1. Let _rnum_ be ? ToNumeric(_rval_).""",
    """        1. If Type(_lnum_) is different from Type(_rnum_), throw a *TypeError* exception.""",
    """        1. Let _T_ be Type(_lnum_).""",
    """        1. [id="step-applystringornumericbinaryoperator-operations-table"] Let _operation_ be the abstract operation associated with _opText_ in the following table:""",
    """          <figure>""",
    """            <table class="lightweight-table">""",
    """              <tbody>""",
    """                <tr><th> _opText_       </th><th> _operation_             </th></tr>""",
    """                <tr><td> `**`           </td><td> _T_::exponentiate       </td></tr>""",
    """                <tr><td> `*`            </td><td> _T_::multiply           </td></tr>""",
    """                <tr><td> `/`            </td><td> _T_::divide             </td></tr>""",
    """                <tr><td> `%`            </td><td> _T_::remainder          </td></tr>""",
    """                <tr><td> `+`            </td><td> _T_::add                </td></tr>""",
    """                <tr><td> `-`            </td><td> _T_::subtract           </td></tr>""",
    """                <tr><td> `<<`     </td><td> _T_::leftShift          </td></tr>""",
    """                <tr><td> `>>`     </td><td> _T_::signedRightShift   </td></tr>""",
    """                <tr><td> `>>>` </td><td> _T_::unsignedRightShift </td></tr>""",
    """                <tr><td> `&`        </td><td> _T_::bitwiseAND         </td></tr>""",
    """                <tr><td> `^`            </td><td> _T_::bitwiseXOR         </td></tr>""",
    """                <tr><td> `|`            </td><td> _T_::bitwiseOR          </td></tr>""",
    """              </tbody>""",
    """            </table>""",
    """          </figure>""",
    """        1. Return ? _operation_(_lnum_, _rnum_).""",
  )
}
