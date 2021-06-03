package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::HoistableDeclaration[3,0].DeclarationPart` extends Algo {
  val head = SyntaxDirectedHead("HoistableDeclaration", 3, 0, Rhs(List(NonTerminal("AsyncGeneratorDeclaration", List(""), false)), None), "DeclarationPart", List())
  val ids = List(
    "sec-static-semantics-declarationpart",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""return AsyncGeneratorDeclaration""".stripMargin)
  val code = scala.Array[String](
    """        1. Return |AsyncGeneratorDeclaration|.""",
  )
}
