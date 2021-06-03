package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ForDeclaration[0,0].ForDeclarationBindingInitialization` extends Algo {
  val head = SyntaxDirectedHead("ForDeclaration", 0, 0, Rhs(List(NonTerminal("LetOrConst", List(""), false), NonTerminal("ForBinding", List(""), false)), None), "ForDeclarationBindingInitialization", List(Param("value", Normal), Param("environment", Normal)))
  val ids = List(
    "sec-runtime-semantics-fordeclarationbindinginitialization",
    "sec-for-in-and-for-of-statements",
    "sec-iteration-statements",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ForBinding "BindingInitialization" value environment)
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return the result of performing BindingInitialization for |ForBinding| passing _value_ and _environment_ as the arguments.""",
  )
}
