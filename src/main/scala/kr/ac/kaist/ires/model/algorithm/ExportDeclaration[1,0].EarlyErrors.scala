package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ExportDeclaration[1,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("ExportDeclaration", 1, 0, Rhs(List(Terminal("export"), NonTerminal("NamedExports", List(""), false), Terminal(";")), None), "EarlyErrors", List())
  val ids = List(
    "sec-exports-static-semantics-early-errors",
    "sec-exports",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""??? "For each nt:{IdentifierName} id:{n} in ReferencedBindings of nt:{NamedExports} : It is a Syntax Error if StringValue of id:{n} is a nt:{ReservedWord} or if the StringValue of id:{n} is one of : value:{\"implements\"} , value:{\"interface\"} , value:{\"let\"} , value:{\"package\"} , value:{\"private\"} , value:{\"protected\"} , value:{\"public\"} , or value:{\"static\"} ."""".stripMargin)
  val code = scala.Array[String](
    """          <li>""",
    """            For each |IdentifierName| _n_ in ReferencedBindings of |NamedExports|: It is a Syntax Error if StringValue of _n_ is a |ReservedWord| or if the StringValue of _n_ is one of: *"implements"*, *"interface"*, *"let"*, *"package"*, *"private"*, *"protected"*, *"public"*, or *"static"*.""",
    """          </li>""",
  )
}
