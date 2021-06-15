package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::RegularExpressionLiteral[0,0].BodyText` extends Algo {
  val head = SyntaxDirectedHead("RegularExpressionLiteral", 0, 0, Rhs(List(Terminal("/"), NonTerminal("RegularExpressionBody", List(""), false), Terminal("/"), NonTerminal("RegularExpressionFlags", List(""), false)), None), "BodyText", List())
  val ids = List(
    "sec-static-semantics-bodytext",
    "sec-literals-regular-expression-literals",
    "sec-ecmascript-language-lexical-grammar-literals",
    "sec-ecmascript-language-lexical-grammar",
  )
  val rawBody = parseInst("""??? "Return the source text that was recognized as nt:{RegularExpressionBody} ."""".stripMargin)
  val code = scala.Array[String](
    """          1. Return the source text that was recognized as |RegularExpressionBody|.""",
  )
}
