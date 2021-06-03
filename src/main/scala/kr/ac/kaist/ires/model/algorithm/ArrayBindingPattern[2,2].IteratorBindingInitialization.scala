package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArrayBindingPattern[2,2].IteratorBindingInitialization` extends Algo {
  val head = SyntaxDirectedHead("ArrayBindingPattern", 2, 2, Rhs(List(Terminal("["), NonTerminal("BindingElementList", List(""), false), Terminal(","), NonTerminal("Elision", List(""), false), Terminal("]")), None), "IteratorBindingInitialization", List(Param("iteratorRecord", Normal), Param("environment", Normal)))
  val ids = List(
    "sec-runtime-semantics-iteratorbindinginitialization",
    "sec-syntax-directed-operations-miscellaneous",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (BindingElementList "IteratorBindingInitialization" iteratorRecord environment)
  |  0:[? __x0__]
  |  1:access __x1__ = (Elision "IteratorDestructuringAssignmentEvaluation" iteratorRecord)
  |  1:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Perform ? IteratorBindingInitialization for |BindingElementList| with _iteratorRecord_ and _environment_ as arguments.""",
    """        1. Return the result of performing IteratorDestructuringAssignmentEvaluation of |Elision| with _iteratorRecord_ as the argument.""",
  )
}
