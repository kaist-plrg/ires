package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ExportDeclaration[5,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("ExportDeclaration", 5, 0, Rhs(List(Terminal("export"), Terminal("default"), NonTerminal("ClassDeclaration", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-exports-runtime-semantics-evaluation",
    "sec-exports",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ClassDeclaration "BindingClassDeclarationEvaluation")
  |  0:let value = [? __x0__]
  |  1:access __x1__ = (ClassDeclaration "BoundNames")
  |  1:let className = __x1__[0i]
  |  2:if (= className "*default*") {
  |    3:let env = CONTEXT.LexicalEnvironment
  |    4:app __x2__ = (InitializeBoundName "*default*" value env)
  |    4:[? __x2__]
  |  } else 1:{}
  |  5:return CONST_empty
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _value_ be ? BindingClassDeclarationEvaluation of |ClassDeclaration|.""",
    """          1. Let _className_ be the sole element of BoundNames of |ClassDeclaration|.""",
    """          1. If _className_ is *"\*default\*"*, then""",
    """            1. Let _env_ be the running execution context's LexicalEnvironment.""",
    """            1. Perform ? InitializeBoundName(*"\*default\*"*, _value_, _env_).""",
    """          1. Return NormalCompletion(~empty~).""",
  )
}
