package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Object.fromEntries` extends Algo {
  val head = BuiltinHead(parseRef("""Object.fromEntries"""), List(Param("iterable", Normal)))
  val ids = List(
    "sec-object.fromentries",
    "sec-properties-of-the-object-constructor",
    "sec-object-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (RequireObjectCoercible iterable)
  |  0:[? __x0__]
  |  1:app __x1__ = (OrdinaryObjectCreate INTRINSIC_Object_prototype)
  |  1:let obj = [! __x1__]
  |  3:let stepsDefine = CreateDataPropertyOnObjectFunctions
  |  4:??? "Let id:{lengthDefine} be the number of non - optional parameters of the function definition in CreateDataPropertyOnObjectFunctions ."
  |  5:app __x2__ = (CreateBuiltinFunction stepsDefine lengthDefine "" (new []))
  |  5:let adder = [! __x2__]
  |  6:app __x3__ = (AddEntriesFromIterable obj iterable adder)
  |  6:return [? __x3__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Perform ? RequireObjectCoercible(_iterable_).""",
    """          1. Let _obj_ be ! OrdinaryObjectCreate(%Object.prototype%).""",
    """          1. Assert: _obj_ is an extensible ordinary object with no own properties.""",
    """          1. Let _stepsDefine_ be the algorithm steps defined in <emu-xref href="#sec-create-data-property-on-object-functions" title></emu-xref>.""",
    """          1. Let _lengthDefine_ be the number of non-optional parameters of the function definition in <emu-xref href="#sec-create-data-property-on-object-functions" title></emu-xref>.""",
    """          1. Let _adder_ be ! CreateBuiltinFunction(_stepsDefine_, _lengthDefine_, *""*, « »).""",
    """          1. Return ? AddEntriesFromIterable(_obj_, _iterable_, _adder_).""",
  )
}
