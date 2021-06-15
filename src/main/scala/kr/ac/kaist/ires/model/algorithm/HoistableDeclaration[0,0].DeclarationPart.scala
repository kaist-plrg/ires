package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::HoistableDeclaration[0,0].DeclarationPart` extends Algo {
  val head = SyntaxDirectedHead("HoistableDeclaration", 0, 0, Rhs(List(NonTerminal("FunctionDeclaration", List(""), false)), None), "DeclarationPart", List())
  val ids = List(
    "sec-static-semantics-declarationpart",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""return FunctionDeclaration""".stripMargin)
  val code = scala.Array[String](
    """        1. Return |FunctionDeclaration|.""",
  )
}
