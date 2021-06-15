package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ClassDeclaration[0,0].BindingClassDeclarationEvaluation` extends Algo {
  val head = SyntaxDirectedHead("ClassDeclaration", 0, 0, Rhs(List(Terminal("class"), NonTerminal("BindingIdentifier", List(""), false), NonTerminal("ClassTail", List(""), false)), None), "BindingClassDeclarationEvaluation", List())
  val ids = List(
    "sec-runtime-semantics-bindingclassdeclarationevaluation",
    "sec-class-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (BindingIdentifier "StringValue")
  |  0:let className = __x0__
  |  1:access __x1__ = (ClassTail "ClassDefinitionEvaluation" className className)
  |  1:let value = [? __x1__]
  |  2:value.SourceText = (get-syntax ClassDeclaration)
  |  3:let env = CONTEXT.LexicalEnvironment
  |  4:app __x2__ = (InitializeBoundName className value env)
  |  4:[? __x2__]
  |  5:return value
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _className_ be StringValue of |BindingIdentifier|.""",
    """        1. Let _value_ be ? ClassDefinitionEvaluation of |ClassTail| with arguments _className_ and _className_.""",
    """        1. Set _value_.[[SourceText]] to the source text matched by |ClassDeclaration|.""",
    """        1. Let _env_ be the running execution context's LexicalEnvironment.""",
    """        1. Perform ? InitializeBoundName(_className_, _value_, _env_).""",
    """        1. Return _value_.""",
  )
}
