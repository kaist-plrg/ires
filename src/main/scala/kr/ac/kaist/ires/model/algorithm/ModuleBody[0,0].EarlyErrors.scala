package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ModuleBody[0,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("ModuleBody", 0, 0, Rhs(List(NonTerminal("ModuleItemList", List(""), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-module-semantics-static-semantics-early-errors",
    "sec-module-semantics",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ModuleItemList "LexicallyDeclaredNames")
  |  0:app __x1__ = (IsDuplicate __x0__)
  |  0:if __x1__ throw SyntaxError else 2:{}
  |  1:access __x2__ = (ModuleItemList "LexicallyDeclaredNames")
  |  1:access __x3__ = (ModuleItemList "VarDeclaredNames")
  |  1:let __x4__ = __x2__
  |  1:let __x5__ = __x3__
  |  1:let __x6__ = 0i
  |  1:let __x7__ = 0i
  |  1:let __x8__ = false
  |  1:while (< __x6__ __x4__.length) {
  |    __x7__ = 0i
  |    while (< __x7__ __x5__.length) if (= __x4__[__x6__] __x5__[__x7__]) __x8__ = true else 2:{}
  |  }
  |  1:if __x8__ throw SyntaxError else 2:{}
  |  2:access __x9__ = (ModuleItemList "ExportedNames")
  |  2:app __x10__ = (IsDuplicate __x9__)
  |  2:if __x10__ throw SyntaxError else 2:{}
  |  3:access __x11__ = (ModuleItemList "ExportedBindings")
  |  3:access __x12__ = (ModuleItemList "VarDeclaredNames")
  |  3:access __x13__ = (ModuleItemList "LexicallyDeclaredNames")
  |  3:let __x14__ = __x11__
  |  3:let __x15__ = __x12__
  |  3:let __x16__ = __x13__
  |  3:let __x17__ = 0i
  |  3:let __x18__ = 0i
  |  3:let __x19__ = false
  |  3:while (< __x17__ __x14__.length) {
  |    __x18__ = 0i
  |    while (< __x18__ __x15__.length) if (= __x14__[__x17__] __x15__[__x18__]) __x19__ = true else 2:{}
  |    __x18__ = 0i
  |    while (< __x18__ __x16__.length) if (= __x14__[__x17__] __x16__[__x18__]) __x19__ = true else 2:{}
  |  }
  |  3:if __x19__ throw SyntaxError else 2:{}
  |  4:if (contains ModuleItemList "super") throw SyntaxError else 2:{}
  |  5:access __x20__ = (ModuleItemList "Contains" "NewTarget")
  |  5:if __x20__ throw SyntaxError else 2:{}
  |  6:access __x21__ = (ModuleItemList "ContainsDuplicateLabels" (new []))
  |  6:if (= __x21__ true) throw SyntaxError else 2:{}
  |  7:access __x22__ = (ModuleItemList "ContainsUndefinedBreakTarget" (new []))
  |  7:if (= __x22__ true) throw SyntaxError else 2:{}
  |  8:access __x23__ = (ModuleItemList "ContainsUndefinedContinueTarget" (new []) (new []))
  |  8:if (= __x23__ true) throw SyntaxError else 2:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """          <li>""",
    """            It is a Syntax Error if the LexicallyDeclaredNames of |ModuleItemList| contains any duplicate entries.""",
    """          </li>""",
    """          <li>""",
    """            It is a Syntax Error if any element of the LexicallyDeclaredNames of |ModuleItemList| also occurs in the VarDeclaredNames of |ModuleItemList|.""",
    """          </li>""",
    """          <li>""",
    """            It is a Syntax Error if the ExportedNames of |ModuleItemList| contains any duplicate entries.""",
    """          </li>""",
    """          <li>""",
    """            It is a Syntax Error if any element of the ExportedBindings of |ModuleItemList| does not also occur in either the VarDeclaredNames of |ModuleItemList|, or the LexicallyDeclaredNames of |ModuleItemList|.""",
    """          </li>""",
    """          <li>""",
    """            It is a Syntax Error if |ModuleItemList| Contains `super`.""",
    """          </li>""",
    """          <li>""",
    """            It is a Syntax Error if |ModuleItemList| Contains |NewTarget|.""",
    """          </li>""",
    """          <li>""",
    """            It is a Syntax Error if ContainsDuplicateLabels of |ModuleItemList| with argument « » is *true*.""",
    """          </li>""",
    """          <li>""",
    """            It is a Syntax Error if ContainsUndefinedBreakTarget of |ModuleItemList| with argument « » is *true*.""",
    """          </li>""",
    """          <li>""",
    """            It is a Syntax Error if ContainsUndefinedContinueTarget of |ModuleItemList| with arguments « » and « » is *true*.""",
    """          </li>""",
  )
}
