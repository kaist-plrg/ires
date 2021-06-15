package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CreateIntrinsics` extends Algo {
  val head = NormalHead("CreateIntrinsics", List(Param("realmRec", Normal)))
  val ids = List(
    "sec-createintrinsics",
    "sec-code-realms",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:let intrinsics = (new Record("SubMap" -> (new SubMap())))
  |  1:realmRec.Intrinsics = intrinsics
  |  3:app __x0__ = (AddRestrictedFunctionProperties intrinsics.INTRINSIC_Function_prototype realmRec)
  |  3:__x0__
  |  4:return intrinsics
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _intrinsics_ be a new Record.""",
    """        1. Set _realmRec_.[[Intrinsics]] to _intrinsics_.""",
    """        1. Set fields of _intrinsics_ with the values listed in <emu-xref href="#table-well-known-intrinsic-objects"></emu-xref>. The field names are the names listed in column one of the table. The value of each field is a new object value fully and recursively populated with property values as defined by the specification of each object in clauses <emu-xref href="#sec-global-object"></emu-xref> through <emu-xref href="#sec-reflection"></emu-xref>. All object property values are newly created object values. All values that are built-in function objects are created by performing CreateBuiltinFunction(_steps_, _length_, _name_, _slots_, _realmRec_, _prototype_) where _steps_ is the definition of that function provided by this specification, _name_ is the initial value of the function's `name` property, _length_ is the initial value of the function's `length` property, _slots_ is a list of the names, if any, of the function's specified internal slots, and _prototype_ is the specified value of the function's [[Prototype]] internal slot. The creation of the intrinsics and their properties must be ordered to avoid any dependencies upon objects that have not yet been created.""",
    """        1. Perform AddRestrictedFunctionProperties(_intrinsics_.[[%Function.prototype%]], _realmRec_).""",
    """        1. Return _intrinsics_.""",
  )
}
