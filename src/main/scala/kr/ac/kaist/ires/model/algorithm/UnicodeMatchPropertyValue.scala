package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::UnicodeMatchPropertyValue` extends Algo {
  val head = NormalHead("UnicodeMatchPropertyValue", List(Param("p", Normal), Param("v", Normal)))
  val ids = List(
    "sec-runtime-semantics-unicodematchpropertyvalue-p-v",
    "sec-atom",
    "sec-pattern-semantics",
    "sec-regexp-regular-expression-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  2:??? "Let id:{value} be the canonical property value of id:{v} as given in the “ Canonical property value ” column of the corresponding row ."
  |  3:??? "Return the List of Unicode code points of id:{value} ."
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: _p_ is a List of Unicode code points that is identical to a List of Unicode code points that is a canonical, unaliased Unicode property name listed in the “Canonical property name” column of <emu-xref href="#table-nonbinary-unicode-properties"></emu-xref>.""",
    """            1. Assert: _v_ is a List of Unicode code points that is identical to a List of Unicode code points that is a property value or property value alias for Unicode property _p_ listed in the “Property value and aliases” column of <emu-xref href="#table-unicode-general-category-values"></emu-xref> or <emu-xref href="#table-unicode-script-values"></emu-xref>.""",
    """            1. Let _value_ be the canonical property value of _v_ as given in the “Canonical property value” column of the corresponding row.""",
    """            1. Return the List of Unicode code points of _value_.""",
  )
}
