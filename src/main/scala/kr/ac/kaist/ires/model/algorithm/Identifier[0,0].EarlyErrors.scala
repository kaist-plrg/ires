package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Identifier[0,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("Identifier", 0, 0, Rhs(List(ButNot(NonTerminal("IdentifierName", List(""), false), List(NonTerminal("ReservedWord", List(""), false)))), None), "EarlyErrors", List())
  val ids = List(
    "sec-identifiers-static-semantics-early-errors",
    "sec-identifiers",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:??? "It is a Syntax Error if this phrase is contained in strict mode code and the StringValue of nt:{IdentifierName} is : value:{\"implements\"} , value:{\"interface\"} , value:{\"let\"} , value:{\"package\"} , value:{\"private\"} , value:{\"protected\"} , value:{\"public\"} , value:{\"static\"} , or value:{\"yield\"} ."
  |  1:let __x0__ = true
  |  1:let __x1__ = false
  |  1:if (= absent (parse-syntax this "Module" (new []))) __x1__ = true else 2:{}
  |  1:__x0__ = __x1__
  |  1:if __x0__ {
  |    access __x2__ = (IdentifierName "StringValue")
  |    __x0__ = (= __x2__ "await")
  |  } else 2:{}
  |  1:if __x0__ throw SyntaxError else 2:{}
  |  2:??? "It is a Syntax Error if StringValue of nt:{IdentifierName} is the same String value as the StringValue of any nt:{ReservedWord} except for code:{yield} or code:{await} ."
  |}""".stripMargin)
  val code = scala.Array[String](
    """        <li>""",
    """          It is a Syntax Error if this phrase is contained in strict mode code and the StringValue of |IdentifierName| is: *"implements"*, *"interface"*, *"let"*, *"package"*, *"private"*, *"protected"*, *"public"*, *"static"*, or *"yield"*.""",
    """        </li>""",
    """        <li>""",
    """          It is a Syntax Error if the goal symbol of the syntactic grammar is |Module| and the StringValue of |IdentifierName| is *"await"*.""",
    """        </li>""",
    """        <li>""",
    """          It is a Syntax Error if StringValue of |IdentifierName| is the same String value as the StringValue of any |ReservedWord| except for `yield` or `await`.""",
    """        </li>""",
  )
}
