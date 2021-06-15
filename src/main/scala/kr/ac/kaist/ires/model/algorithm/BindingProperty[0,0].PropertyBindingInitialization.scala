package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BindingProperty[0,0].PropertyBindingInitialization` extends Algo {
  val head = SyntaxDirectedHead("BindingProperty", 0, 0, Rhs(List(NonTerminal("SingleNameBinding", List(""), false)), None), "PropertyBindingInitialization", List(Param("value", Normal), Param("environment", Normal)))
  val ids = List(
    "sec-destructuring-binding-patterns-runtime-semantics-propertybindinginitialization",
    "sec-destructuring-binding-patterns",
    "sec-declarations-and-the-variable-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __snb__ = (SingleNameBinding "BoundNames")
  |  0:let name = __snb__[0i]
  |  1:access __x0__ = (SingleNameBinding "KeyedBindingInitialization" value environment name)
  |  1:[? __x0__]
  |  2:return (new [name])
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _name_ be the string that is the only element of BoundNames of |SingleNameBinding|.""",
    """          1. Perform ? KeyedBindingInitialization for |SingleNameBinding| using _value_, _environment_, and _name_ as the arguments.""",
    """          1. Return a List whose sole element is _name_.""",
  )
}
