package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BindingElisionElement[0,1].IteratorBindingInitialization` extends Algo {
  val head = SyntaxDirectedHead("BindingElisionElement", 0, 1, Rhs(List(NonTerminal("Elision", List(""), false), NonTerminal("BindingElement", List(""), false)), None), "IteratorBindingInitialization", List(Param("iteratorRecord", Normal), Param("environment", Normal)))
  val ids = List(
    "sec-runtime-semantics-iteratorbindinginitialization",
    "sec-syntax-directed-operations-miscellaneous",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (Elision "IteratorDestructuringAssignmentEvaluation" iteratorRecord)
  |  0:[? __x0__]
  |  1:access __x1__ = (BindingElement "IteratorBindingInitialization" iteratorRecord environment)
  |  1:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Perform ? IteratorDestructuringAssignmentEvaluation of |Elision| with _iteratorRecord_ as the argument.""",
    """        1. Return the result of performing IteratorBindingInitialization of |BindingElement| with _iteratorRecord_ and _environment_ as the arguments.""",
  )
}
