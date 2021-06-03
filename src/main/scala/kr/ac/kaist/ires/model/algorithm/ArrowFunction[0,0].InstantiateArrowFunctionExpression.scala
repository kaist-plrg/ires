package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArrowFunction[0,0].InstantiateArrowFunctionExpression` extends Algo {
  val head = SyntaxDirectedHead("ArrowFunction", 0, 0, Rhs(List(NonTerminal("ArrowParameters", List(""), false), Terminal("=>"), NonTerminal("ConciseBody", List(""), false)), None), "InstantiateArrowFunctionExpression", List(Param("name", Optional)))
  val ids = List(
    "sec-runtime-semantics-instantiatearrowfunctionexpression",
    "sec-arrow-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:if (= name absent) name = "" else 0:{}
  |  1:access __x0__ = (CONTEXT "LexicalEnvironment")
  |  1:let scope = __x0__
  |  2:let sourceText = (get-syntax ArrowFunction)
  |  3:access __x1__ = (ArrowParameters "CoveredFormalsList")
  |  3:let parameters = __x1__
  |  4:app __x2__ = (OrdinaryFunctionCreate INTRINSIC_Function_prototype sourceText parameters ConciseBody CONST_lexicalDASHthis scope)
  |  4:let closure = __x2__
  |  5:app __x3__ = (SetFunctionName closure name)
  |  5:__x3__
  |  6:return closure
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If _name_ is not present, set _name_ to *""*.""",
    """        1. Let _scope_ be the LexicalEnvironment of the running execution context.""",
    """        1. Let _sourceText_ be the source text matched by |ArrowFunction|.""",
    """        1. Let _parameters_ be CoveredFormalsList of |ArrowParameters|.""",
    """        1. [id="step-arrowfunction-evaluation-functioncreate"] Let _closure_ be OrdinaryFunctionCreate(%Function.prototype%, _sourceText_, _parameters_, |ConciseBody|, ~lexical-this~, _scope_).""",
    """        1. Perform SetFunctionName(_closure_, _name_).""",
    """        1. Return _closure_.""",
  )
}
