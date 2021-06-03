package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SetFunctionName` extends Algo {
  val head = NormalHead("SetFunctionName", List(Param("F", Normal), Param("name", Normal), Param("prefix", Optional)))
  val ids = List(
    "sec-setfunctionname",
    "sec-ecmascript-function-objects",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  1:assert (|| (= (typeof name) Symbol) (= (typeof name) String))
  |  3:if (= (typeof name) Symbol) {
  |    4:let description = name.Description
  |    6:if (= description undefined) name = "" else name = (+ (+ "[" description) "]")
  |  } else 2:{}
  |  7:if (! (= F.InitialName absent)) F.InitialName = name else 2:{}
  |  9:if (! (= prefix absent)) {
  |    10:name = (+ (+ prefix " ") name)
  |    11:if (! (= F.InitialName absent)) if AnyBool F.InitialName = name else 2:{} else 2:{}
  |  } else 2:{}
  |  13:app __x0__ = (DefinePropertyOrThrow F "name" (new PropertyDescriptor("Value" -> name, "Writable" -> false, "Enumerable" -> false, "Configurable" -> true)))
  |  13:return [! __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: _F_ is an extensible object that does not have a *"name"* own property.""",
    """        1. Assert: Type(_name_) is either Symbol or String.""",
    """        1. Assert: If _prefix_ is present, then Type(_prefix_) is String.""",
    """        1. If Type(_name_) is Symbol, then""",
    """          1. Let _description_ be _name_'s [[Description]] value.""",
    """          1. If _description_ is *undefined*, set _name_ to the empty String.""",
    """          1. Else, set _name_ to the string-concatenation of *"["*, _description_, and *"]"*.""",
    """        1. If _F_ has an [[InitialName]] internal slot, then""",
    """          1. Set _F_.[[InitialName]] to _name_.""",
    """        1. If _prefix_ is present, then""",
    """          1. Set _name_ to the string-concatenation of _prefix_, the code unit 0x0020 (SPACE), and _name_.""",
    """          1. If _F_ has an [[InitialName]] internal slot, then""",
    """            1. Optionally, set _F_.[[InitialName]] to _name_.""",
    """        1. Return ! DefinePropertyOrThrow(_F_, *"name"*, PropertyDescriptor { [[Value]]: _name_, [[Writable]]: *false*, [[Enumerable]]: *false*, [[Configurable]]: *true* }).""",
  )
}
