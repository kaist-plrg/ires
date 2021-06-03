package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BindingElement[1,1].KeyedBindingInitialization` extends Algo {
  val head = SyntaxDirectedHead("BindingElement", 1, 1, Rhs(List(NonTerminal("BindingPattern", List(""), false), NonTerminal("Initializer", List(""), true)), None), "KeyedBindingInitialization", List(Param("value", Normal), Param("environment", Normal), Param("propertyName", Normal)))
  val ids = List(
    "sec-runtime-semantics-keyedbindinginitialization",
    "sec-destructuring-binding-patterns",
    "sec-declarations-and-the-variable-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (GetV value propertyName)
  |  0:let v = [? __x0__]
  |  1:if (&& (! (= Initializer absent)) (= v undefined)) {
  |    2:access __x1__ = (Initializer "Evaluation")
  |    2:let defaultValue = __x1__
  |    3:app __x2__ = (GetValue defaultValue)
  |    3:v = [? __x2__]
  |  } else 10:{}
  |  4:access __x3__ = (BindingPattern "BindingInitialization" v environment)
  |  4:return __x3__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _v_ be ? GetV(_value_, _propertyName_).""",
    """          1. If |Initializer| is present and _v_ is *undefined*, then""",
    """            1. Let _defaultValue_ be the result of evaluating |Initializer|.""",
    """            1. Set _v_ to ? GetValue(_defaultValue_).""",
    """          1. Return the result of performing BindingInitialization for |BindingPattern| passing _v_ and _environment_ as arguments.""",
  )
}
