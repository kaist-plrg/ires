package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SymbolDescriptiveString` extends Algo {
  val head = NormalHead("SymbolDescriptiveString", List(Param("sym", Normal)))
  val ids = List(
    "sec-symboldescriptivestring",
    "sec-symbol.prototype.tostring",
    "sec-properties-of-the-symbol-prototype-object",
    "sec-symbol-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof sym) Symbol)
  |  1:let desc = sym.Description
  |  2:if (= desc undefined) desc = "" else 3:{}
  |  3:assert (= (typeof desc) String)
  |  4:return (+ (+ "Symbol(" desc) ")")
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: Type(_sym_) is Symbol.""",
    """            1. Let _desc_ be _sym_'s [[Description]] value.""",
    """            1. If _desc_ is *undefined*, set _desc_ to the empty String.""",
    """            1. Assert: Type(_desc_) is String.""",
    """            1. Return the string-concatenation of *"Symbol("*, _desc_, and *")"*.""",
  )
}
