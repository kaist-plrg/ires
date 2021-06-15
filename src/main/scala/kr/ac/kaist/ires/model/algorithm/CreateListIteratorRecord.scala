package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CreateListIteratorRecord` extends Algo {
  val head = NormalHead("CreateListIteratorRecord", List(Param("list", Normal)))
  val ids = List(
    "sec-createlistiteratorRecord",
    "sec-operations-on-iterator-objects",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:let closure = Lambda
  |  4:app __x0__ = (CreateIteratorFromClosure closure CONST_empty INTRINSIC_IteratorPrototype)
  |  4:let iterator = [! __x0__]
  |  5:return (new Record("Iterator" -> iterator, "NextMethod" -> INTRINSIC_GeneratorFunction_prototype_prototype_next, "Done" -> false))
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _closure_ be a new Abstract Closure with no parameters that captures _list_ and performs the following steps when called:""",
    """          1. For each element _E_ of _list_, do""",
    """            1. Perform ? Yield(_E_).""",
    """          1. Return *undefined*.""",
    """        1. Let _iterator_ be ! CreateIteratorFromClosure(_closure_, ~empty~, %IteratorPrototype%).""",
    """        1. Return Record { [[Iterator]]: _iterator_, [[NextMethod]]: %GeneratorFunction.prototype.prototype.next%, [[Done]]: *false* }.""",
  )
}
