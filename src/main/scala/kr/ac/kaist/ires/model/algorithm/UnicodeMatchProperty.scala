package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::UnicodeMatchProperty` extends Algo {
  val head = NormalHead("UnicodeMatchProperty", List(Param("p", Normal)))
  val ids = List(
    "sec-runtime-semantics-unicodematchproperty-p",
    "sec-atom",
    "sec-pattern-semantics",
    "sec-regexp-regular-expression-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  1:??? "Let id:{c} be the canonical property name of id:{p} as given in the “ Canonical property name ” column of the corresponding row ."
  |  2:??? "Return the List of Unicode code points of id:{c} ."
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: _p_ is a List of Unicode code points that is identical to a List of Unicode code points that is a Unicode <emu-not-ref>property name</emu-not-ref> or property alias listed in the “<emu-not-ref>Property name</emu-not-ref> and aliases” column of <emu-xref href="#table-nonbinary-unicode-properties"></emu-xref> or <emu-xref href="#table-binary-unicode-properties"></emu-xref>.""",
    """            1. Let _c_ be the canonical <emu-not-ref>property name</emu-not-ref> of _p_ as given in the “Canonical <emu-not-ref>property name</emu-not-ref>” column of the corresponding row.""",
    """            1. Return the List of Unicode code points of _c_.""",
  )
}
