package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BindingPattern[1,0].BindingInitialization` extends Algo {
  val head = SyntaxDirectedHead("BindingPattern", 1, 0, Rhs(List(NonTerminal("ArrayBindingPattern", List(""), false)), None), "BindingInitialization", List(Param("value", Normal), Param("environment", Normal)))
  val ids = List(
    "sec-runtime-semantics-bindinginitialization",
    "sec-syntax-directed-operations-miscellaneous",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (GetIterator value)
  |  0:let iteratorRecord = [? __x0__]
  |  1:access __x1__ = (ArrayBindingPattern "IteratorBindingInitialization" iteratorRecord environment)
  |  1:let result = __x1__
  |  2:if (= iteratorRecord.Done false) {
  |    app __x2__ = (IteratorClose iteratorRecord result)
  |    return [? __x2__]
  |  } else 1:{}
  |  3:return result
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _iteratorRecord_ be ? GetIterator(_value_).""",
    """        1. Let _result_ be IteratorBindingInitialization of |ArrayBindingPattern| with arguments _iteratorRecord_ and _environment_.""",
    """        1. If _iteratorRecord_.[[Done]] is *false*, return ? IteratorClose(_iteratorRecord_, _result_).""",
    """        1. Return _result_.""",
  )
}
