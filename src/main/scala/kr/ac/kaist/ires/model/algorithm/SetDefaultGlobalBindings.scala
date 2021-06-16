package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SetDefaultGlobalBindings` extends Algo {
  val head = NormalHead("SetDefaultGlobalBindings", List(Param("realmRec", Normal)))
  val ids = List(
    "sec-setdefaultglobalbindings",
    "sec-code-realms",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:let global = realmRec.GlobalObject
  |  1:let __keys__ = (map-keys GLOBAL.SubMap)
  |  1:let __i__ = 0i
  |  1:while (< __i__ __keys__.length) {
  |    let name = __keys__[__i__]
  |    let desc = GLOBAL.SubMap[name]
  |    global.SubMap[name] = GLOBAL.SubMap[name]
  |    __i__ = (+ __i__ 1i)
  |  }
  |  1:global.SubMap.globalThis.Value = global
  |  5:return global
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _global_ be _realmRec_.[[GlobalObject]].""",
    """        1. For each property of the Global Object specified in clause <emu-xref href="#sec-global-object"></emu-xref>, do""",
    """          1. Let _name_ be the String value of the property name.""",
    """          1. Let _desc_ be the fully populated data Property Descriptor for the property, containing the specified attributes for the property. For properties listed in <emu-xref href="#sec-function-properties-of-the-global-object"></emu-xref>, <emu-xref href="#sec-constructor-properties-of-the-global-object"></emu-xref>, or <emu-xref href="#sec-other-properties-of-the-global-object"></emu-xref> the value of the [[Value]] attribute is the corresponding intrinsic object from _realmRec_.""",
    """          1. Perform ? DefinePropertyOrThrow(_global_, _name_, _desc_).""",
    """        1. Return _global_.""",
  )
}
