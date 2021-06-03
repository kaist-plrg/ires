package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::LexicalBinding[0,1].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("LexicalBinding", 0, 1, Rhs(List(NonTerminal("BindingIdentifier", List(""), false), NonTerminal("Initializer", List(""), true)), None), "EarlyErrors", List())
  val ids = List(
    "sec-let-and-const-declarations-static-semantics-early-errors",
    "sec-let-and-const-declarations",
    "sec-declarations-and-the-variable-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""??? "It is a Syntax Error if nt:{Initializer} is not present and IsConstantDeclaration of the nt:{LexicalDeclaration} containing this nt:{LexicalBinding} is value:{true} ."""".stripMargin)
  val code = scala.Array[String](
    """          <li>""",
    """            It is a Syntax Error if |Initializer| is not present and IsConstantDeclaration of the |LexicalDeclaration| containing this |LexicalBinding| is *true*.""",
    """          </li>""",
  )
}
