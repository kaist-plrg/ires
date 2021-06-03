package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ScriptBody[0,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("ScriptBody", 0, 0, Rhs(List(NonTerminal("StatementList", List(""), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-scripts-static-semantics-early-errors",
    "sec-scripts",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:??? "It is a Syntax Error if nt:{StatementList} Contains code:{super} unless the source code containing code:{super} is eval code that is being processed by a direct eval . Additional early error rules for code:{super} within direct eval are defined in PerformEval ."
  |  1:??? "It is a Syntax Error if nt:{StatementList} Contains nt:{NewTarget} unless the source code containing nt:{NewTarget} is eval code that is being processed by a direct eval . Additional early error rules for nt:{NewTarget} in direct eval are defined in PerformEval ."
  |  2:access __x0__ = (StatementList "ContainsDuplicateLabels" (new []))
  |  2:if (= __x0__ true) throw SyntaxError else 2:{}
  |  3:access __x1__ = (StatementList "ContainsUndefinedBreakTarget" (new []))
  |  3:if (= __x1__ true) throw SyntaxError else 2:{}
  |  4:access __x2__ = (StatementList "ContainsUndefinedContinueTarget" (new []) (new []))
  |  4:if (= __x2__ true) throw SyntaxError else 2:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """        <li>""",
    """          It is a Syntax Error if |StatementList| Contains `super` unless the source code containing `super` is eval code that is being processed by a direct eval. Additional early error rules for `super` within direct eval are defined in <emu-xref href="#sec-performeval"></emu-xref>.""",
    """        </li>""",
    """        <li>""",
    """          It is a Syntax Error if |StatementList| Contains |NewTarget| unless the source code containing |NewTarget| is eval code that is being processed by a direct eval. Additional early error rules for |NewTarget| in direct eval are defined in <emu-xref href="#sec-performeval"></emu-xref>.""",
    """        </li>""",
    """        <li>""",
    """          It is a Syntax Error if ContainsDuplicateLabels of |StatementList| with argument « » is *true*.""",
    """        </li>""",
    """        <li>""",
    """          It is a Syntax Error if ContainsUndefinedBreakTarget of |StatementList| with argument « » is *true*.""",
    """        </li>""",
    """        <li>""",
    """          It is a Syntax Error if ContainsUndefinedContinueTarget of |StatementList| with arguments « » and « » is *true*.""",
    """        </li>""",
  )
}
