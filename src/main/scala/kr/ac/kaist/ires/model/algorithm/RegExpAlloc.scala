package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::RegExpAlloc` extends Algo {
  val head = NormalHead("RegExpAlloc", List(Param("newTarget", Normal)))
  val ids = List(
    "sec-regexpalloc",
    "sec-abstract-operations-for-the-regexp-constructor",
    "sec-regexp-constructor",
    "sec-regexp-regular-expression-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (OrdinaryCreateFromConstructor newTarget "%RegExp.prototype%" (new ["RegExpMatcher", "OriginalSource", "OriginalFlags"]))
  |  0:let obj = [? __x0__]
  |  1:app __x1__ = (DefinePropertyOrThrow obj "lastIndex" (new PropertyDescriptor("Writable" -> true, "Enumerable" -> false, "Configurable" -> false)))
  |  1:[! __x1__]
  |  2:return obj
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _obj_ be ? OrdinaryCreateFromConstructor(_newTarget_, *"%RegExp.prototype%"*, « [[RegExpMatcher]], [[OriginalSource]], [[OriginalFlags]] »).""",
    """            1. Perform ! DefinePropertyOrThrow(_obj_, *"lastIndex"*, PropertyDescriptor { [[Writable]]: *true*, [[Enumerable]]: *false*, [[Configurable]]: *false* }).""",
    """            1. Return _obj_.""",
  )
}
