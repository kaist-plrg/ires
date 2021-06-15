package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArrayBindingPattern[2,3].IteratorBindingInitialization` extends Algo {
  val head = SyntaxDirectedHead("ArrayBindingPattern", 2, 3, Rhs(List(Terminal("["), NonTerminal("BindingElementList", List(""), false), Terminal(","), NonTerminal("Elision", List(""), true), NonTerminal("BindingRestElement", List(""), false), Terminal("]")), None), "IteratorBindingInitialization", List(Param("iteratorRecord", Normal), Param("environment", Normal)))
  val ids = List(
    "sec-runtime-semantics-iteratorbindinginitialization",
    "sec-syntax-directed-operations-miscellaneous",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (BindingElementList "IteratorBindingInitialization" iteratorRecord environment)
  |  0:[? __x0__]
  |  1:if (! (= Elision absent)) {
  |    2:access __x1__ = (Elision "IteratorDestructuringAssignmentEvaluation" iteratorRecord)
  |    2:[? __x1__]
  |  } else 1:{}
  |  3:access __x2__ = (BindingRestElement "IteratorBindingInitialization" iteratorRecord environment)
  |  3:return __x2__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Perform ? IteratorBindingInitialization for |BindingElementList| with _iteratorRecord_ and _environment_ as arguments.""",
    """        1. If |Elision| is present, then""",
    """          1. Perform ? IteratorDestructuringAssignmentEvaluation of |Elision| with _iteratorRecord_ as the argument.""",
    """        1. Return the result of performing IteratorBindingInitialization for |BindingRestElement| with _iteratorRecord_ and _environment_ as arguments.""",
  )
}
