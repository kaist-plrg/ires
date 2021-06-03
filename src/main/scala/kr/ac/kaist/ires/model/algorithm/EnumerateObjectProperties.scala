package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::EnumerateObjectProperties` extends Algo {
  val head = NormalHead("EnumerateObjectProperties", List(Param("O", Normal)))
  val ids = List(
    "sec-enumerate-object-properties",
    "sec-for-in-and-for-of-statements",
    "sec-iteration-statements",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof O) Object)
  |  1:return (new Object())
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: Type(_O_) is Object.""",
    """          1. Return an Iterator object (<emu-xref href="#sec-iterator-interface"></emu-xref>) whose `next` method iterates over all the String-valued keys of enumerable properties of _O_. The iterator object is never directly accessible to ECMAScript code. The mechanics and order of enumerating the properties is not specified but must conform to the rules specified below.""",
  )
}
