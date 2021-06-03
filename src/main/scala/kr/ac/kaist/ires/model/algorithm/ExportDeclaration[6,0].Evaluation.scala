package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ExportDeclaration[6,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("ExportDeclaration", 6, 0, Rhs(List(Terminal("export"), Terminal("default"), NonTerminal("AssignmentExpression", List(""), false), Terminal(";")), None), "Evaluation", List())
  val ids = List(
    "sec-exports-runtime-semantics-evaluation",
    "sec-exports",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  2:app __x0__ = (IsAnonymousFunctionDefinition AssignmentExpression)
  |  2:if (= __x0__ true) {
  |    1:access __x1__ = (AssignmentExpression "NamedEvaluation" "default")
  |    1:let value = [? __x1__]
  |  } else {
  |    3:access __x2__ = (AssignmentExpression "Evaluation")
  |    3:let rhs = __x2__
  |    4:app __x3__ = (GetValue rhs)
  |    4:let value = [? __x3__]
  |  }
  |  5:let env = CONTEXT.LexicalEnvironment
  |  6:app __x4__ = (InitializeBoundName "*default*" value env)
  |  6:[? __x4__]
  |  7:return CONST_empty
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If IsAnonymousFunctionDefinition(|AssignmentExpression|) is *true*, then""",
    """            1. Let _value_ be ? NamedEvaluation of |AssignmentExpression| with argument *"default"*.""",
    """          1. Else,""",
    """            1. Let _rhs_ be the result of evaluating |AssignmentExpression|.""",
    """            1. Let _value_ be ? GetValue(_rhs_).""",
    """          1. Let _env_ be the running execution context's LexicalEnvironment.""",
    """          1. Perform ? InitializeBoundName(*"\*default\*"*, _value_, _env_).""",
    """          1. Return NormalCompletion(~empty~).""",
  )
}
