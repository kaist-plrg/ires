package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ReturnStatement[1,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("ReturnStatement", 1, 0, Rhs(List(Terminal("return"), NonTerminal("Expression", List(""), false), Terminal(";")), None), "Evaluation", List())
  val ids = List(
    "sec-return-statement-runtime-semantics-evaluation",
    "sec-return-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (Expression "Evaluation")
  |  0:let exprRef = __x0__
  |  1:app __x1__ = (GetValue exprRef)
  |  1:let exprValue = [? __x1__]
  |  2:app __x2__ = (GetGeneratorKind)
  |  2:if (= [! __x2__] CONST_async) {
  |    app __x3__ = (Await exprValue)
  |    exprValue = [? __x3__]
  |  } else 2:{}
  |  3:return (new Completion("Type" -> CONST_return, "Value" -> exprValue, "Target" -> CONST_empty))
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _exprRef_ be the result of evaluating |Expression|.""",
    """        1. Let _exprValue_ be ? GetValue(_exprRef_).""",
    """        1. If ! GetGeneratorKind() is ~async~, set _exprValue_ to ? Await(_exprValue_).""",
    """        1. Return Completion { [[Type]]: ~return~, [[Value]]: _exprValue_, [[Target]]: ~empty~ }.""",
  )
}
