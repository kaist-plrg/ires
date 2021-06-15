package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AssignmentExpression[5,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("AssignmentExpression", 5, 0, Rhs(List(NonTerminal("LeftHandSideExpression", List(""), false), NonTerminal("AssignmentOperator", List(""), false), NonTerminal("AssignmentExpression", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-assignment-operators-runtime-semantics-evaluation",
    "sec-assignment-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (LeftHandSideExpression "Evaluation")
  |  0:let lref = __x0__
  |  1:app __x1__ = (GetValue lref)
  |  1:let lval = [? __x1__]
  |  2:access __x2__ = (AssignmentExpression "Evaluation")
  |  2:let rref = __x2__
  |  3:app __x3__ = (GetValue rref)
  |  3:let rval = [? __x3__]
  |  4:let assignmentOpText = (get-syntax AssignmentOperator)
  |  5:??? "Let id:{opText} be the sequence of Unicode code points associated with id:{assignmentOpText} in the following table : in:{} out:{}"
  |  25:app __x4__ = (ApplyStringOrNumericBinaryOperator lval opText rval)
  |  25:let r = __x4__
  |  26:app __x5__ = (PutValue lref r)
  |  26:[? __x5__]
  |  27:return r
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _lref_ be the result of evaluating |LeftHandSideExpression|.""",
    """        1. [id="step-assignmentexpression-evaluation-compound-getvalue"] Let _lval_ be ? GetValue(_lref_).""",
    """        1. Let _rref_ be the result of evaluating |AssignmentExpression|.""",
    """        1. Let _rval_ be ? GetValue(_rref_).""",
    """        1. Let _assignmentOpText_ be the source text matched by |AssignmentOperator|.""",
    """        1. Let _opText_ be the sequence of Unicode code points associated with _assignmentOpText_ in the following table:""",
    """          <figure>""",
    """            <table class="lightweight-table">""",
    """              <tbody>""",
    """                <tr><th> _assignmentOpText_ </th><th> _opText_       </th></tr>""",
    """                <tr><td> `**=`              </td><td> `**`           </td></tr>""",
    """                <tr><td> `*=`               </td><td> `*`            </td></tr>""",
    """                <tr><td> `/=`               </td><td> `/`            </td></tr>""",
    """                <tr><td> `%=`               </td><td> `%`            </td></tr>""",
    """                <tr><td> `+=`               </td><td> `+`            </td></tr>""",
    """                <tr><td> `-=`               </td><td> `-`            </td></tr>""",
    """                <tr><td> `<<=`        </td><td> `<<`     </td></tr>""",
    """                <tr><td> `>>=`        </td><td> `>>`     </td></tr>""",
    """                <tr><td> `>>>=`    </td><td> `>>>` </td></tr>""",
    """                <tr><td> `&=`           </td><td> `&`        </td></tr>""",
    """                <tr><td> `^=`               </td><td> `^`            </td></tr>""",
    """                <tr><td> `|=`               </td><td> `|`            </td></tr>""",
    """              </tbody>""",
    """            </table>""",
    """          </figure>""",
    """        1. Let _r_ be ApplyStringOrNumericBinaryOperator(_lval_, _opText_, _rval_).""",
    """        1. [id="step-assignmentexpression-evaluation-compound-putvalue"] Perform ? PutValue(_lref_, _r_).""",
    """        1. Return _r_.""",
  )
}
