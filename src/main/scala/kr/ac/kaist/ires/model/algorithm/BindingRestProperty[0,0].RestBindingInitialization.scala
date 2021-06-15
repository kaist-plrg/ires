package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BindingRestProperty[0,0].RestBindingInitialization` extends Algo {
  val head = SyntaxDirectedHead("BindingRestProperty", 0, 0, Rhs(List(Terminal("..."), NonTerminal("BindingIdentifier", List(""), false)), None), "RestBindingInitialization", List(Param("value", Normal), Param("environment", Normal), Param("excludedNames", Normal)))
  val ids = List(
    "sec-destructuring-binding-patterns-runtime-semantics-restbindinginitialization",
    "sec-destructuring-binding-patterns",
    "sec-declarations-and-the-variable-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (BindingIdentifier "StringValue")
  |  0:app __x1__ = (ResolveBinding __x0__ environment)
  |  0:let lhs = [? __x1__]
  |  1:app __x2__ = (OrdinaryObjectCreate INTRINSIC_Object_prototype)
  |  1:let restObj = [! __x2__]
  |  2:app __x3__ = (CopyDataProperties restObj value excludedNames)
  |  2:[? __x3__]
  |  3:if (= environment undefined) {
  |    app __x4__ = (PutValue lhs restObj)
  |    return __x4__
  |  } else 10:{}
  |  4:app __x5__ = (InitializeReferencedBinding lhs restObj)
  |  4:return __x5__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _lhs_ be ? ResolveBinding(StringValue of |BindingIdentifier|, _environment_).""",
    """          1. Let _restObj_ be ! OrdinaryObjectCreate(%Object.prototype%).""",
    """          1. Perform ? CopyDataProperties(_restObj_, _value_, _excludedNames_).""",
    """          1. If _environment_ is *undefined*, return PutValue(_lhs_, _restObj_).""",
    """          1. Return InitializeReferencedBinding(_lhs_, _restObj_).""",
  )
}
