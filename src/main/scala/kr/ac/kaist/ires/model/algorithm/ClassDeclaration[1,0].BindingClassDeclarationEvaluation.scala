package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ClassDeclaration[1,0].BindingClassDeclarationEvaluation` extends Algo {
  val head = SyntaxDirectedHead("ClassDeclaration", 1, 0, Rhs(List(Terminal("class"), NonTerminal("ClassTail", List(""), false)), None), "BindingClassDeclarationEvaluation", List())
  val ids = List(
    "sec-runtime-semantics-bindingclassdeclarationevaluation",
    "sec-class-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ClassTail "ClassDefinitionEvaluation" undefined "default")
  |  0:let value = [? __x0__]
  |  1:value.SourceText = (get-syntax ClassDeclaration)
  |  2:return value
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _value_ be ? ClassDefinitionEvaluation of |ClassTail| with arguments *undefined* and *"default"*.""",
    """        1. Set _value_.[[SourceText]] to the source text matched by |ClassDeclaration|.""",
    """        1. Return _value_.""",
  )
}
