package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArrayBindingPattern[0,3].IteratorBindingInitialization` extends Algo {
  val head = SyntaxDirectedHead("ArrayBindingPattern", 0, 3, Rhs(List(Terminal("["), NonTerminal("Elision", List(""), true), NonTerminal("BindingRestElement", List(""), false), Terminal("]")), None), "IteratorBindingInitialization", List(Param("iteratorRecord", Normal), Param("environment", Normal)))
  val ids = List(
    "sec-runtime-semantics-iteratorbindinginitialization",
    "sec-syntax-directed-operations-miscellaneous",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= Elision absent)) {
  |    1:access __x0__ = (Elision "IteratorDestructuringAssignmentEvaluation" iteratorRecord)
  |    1:[? __x0__]
  |  } else 1:{}
  |  2:access __x1__ = (BindingRestElement "IteratorBindingInitialization" iteratorRecord environment)
  |  2:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If |Elision| is present, then""",
    """          1. Perform ? IteratorDestructuringAssignmentEvaluation of |Elision| with _iteratorRecord_ as the argument.""",
    """        1. Return the result of performing IteratorBindingInitialization for |BindingRestElement| with _iteratorRecord_ and _environment_ as arguments.""",
  )
}
