package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FormalParameters[4,0].IteratorBindingInitialization` extends Algo {
  val head = SyntaxDirectedHead("FormalParameters", 4, 0, Rhs(List(NonTerminal("FormalParameterList", List(""), false), Terminal(","), NonTerminal("FunctionRestParameter", List(""), false)), None), "IteratorBindingInitialization", List(Param("iteratorRecord", Normal), Param("environment", Normal)))
  val ids = List(
    "sec-runtime-semantics-iteratorbindinginitialization",
    "sec-syntax-directed-operations-miscellaneous",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (FormalParameterList "IteratorBindingInitialization" iteratorRecord environment)
  |  0:[? __x0__]
  |  1:access __x1__ = (FunctionRestParameter "IteratorBindingInitialization" iteratorRecord environment)
  |  1:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Perform ? IteratorBindingInitialization for |FormalParameterList| using _iteratorRecord_ and _environment_ as the arguments.""",
    """        1. Return the result of performing IteratorBindingInitialization for |FunctionRestParameter| using _iteratorRecord_ and _environment_ as the arguments.""",
  )
}
