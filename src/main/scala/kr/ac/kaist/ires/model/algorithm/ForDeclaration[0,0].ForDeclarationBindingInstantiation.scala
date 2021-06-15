package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ForDeclaration[0,0].ForDeclarationBindingInstantiation` extends Algo {
  val head = SyntaxDirectedHead("ForDeclaration", 0, 0, Rhs(List(NonTerminal("LetOrConst", List(""), false), NonTerminal("ForBinding", List(""), false)), None), "ForDeclarationBindingInstantiation", List(Param("environment", Normal)))
  val ids = List(
    "sec-runtime-semantics-fordeclarationbindinginstantiation",
    "sec-for-in-and-for-of-statements",
    "sec-iteration-statements",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:assert (is-instance-of environment DeclarativeEnvironmentRecord)
  |  1:access __x0__ = (ForBinding "BoundNames")
  |  1:let __x1__ = __x0__
  |  1:let __x2__ = 0i
  |  1:while (< __x2__ __x1__.length) {
  |    let name = __x1__[__x2__]
  |    4:access __x3__ = (LetOrConst "IsConstantDeclaration")
  |    4:if (= __x3__ true) {
  |      3:app __x4__ = (environment.CreateImmutableBinding environment name true)
  |      3:[! __x4__]
  |    } else {
  |      5:app __x5__ = (environment.CreateMutableBinding environment name false)
  |      5:[! __x5__]
  |    }
  |    __x2__ = (+ __x2__ 1i)
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: _environment_ is a declarative Environment Record.""",
    """          1. For each element _name_ of the BoundNames of |ForBinding|, do""",
    """            1. If IsConstantDeclaration of |LetOrConst| is *true*, then""",
    """              1. Perform ! _environment_.CreateImmutableBinding(_name_, *true*).""",
    """            1. Else,""",
    """              1. Perform ! _environment_.CreateMutableBinding(_name_, *false*).""",
  )
}
