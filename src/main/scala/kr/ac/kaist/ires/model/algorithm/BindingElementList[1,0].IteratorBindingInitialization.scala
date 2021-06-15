package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BindingElementList[1,0].IteratorBindingInitialization` extends Algo {
  val head = SyntaxDirectedHead("BindingElementList", 1, 0, Rhs(List(NonTerminal("BindingElementList", List(""), false), Terminal(","), NonTerminal("BindingElisionElement", List(""), false)), None), "IteratorBindingInitialization", List(Param("iteratorRecord", Normal), Param("environment", Normal)))
  val ids = List(
    "sec-runtime-semantics-iteratorbindinginitialization",
    "sec-syntax-directed-operations-miscellaneous",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (BindingElementList "IteratorBindingInitialization" iteratorRecord environment)
  |  0:[? __x0__]
  |  1:access __x1__ = (BindingElisionElement "IteratorBindingInitialization" iteratorRecord environment)
  |  1:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Perform ? IteratorBindingInitialization for |BindingElementList| with _iteratorRecord_ and _environment_ as arguments.""",
    """        1. Return the result of performing IteratorBindingInitialization for |BindingElisionElement| using _iteratorRecord_ and _environment_ as arguments.""",
  )
}
