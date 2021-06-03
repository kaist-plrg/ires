package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Function.prototype.toString` extends Algo {
  val head = BuiltinHead(parseRef("""Function.prototype.toString"""), List())
  val ids = List(
    "sec-function.prototype.tostring",
    "sec-properties-of-the-function-prototype-object",
    "sec-function-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:let func = this
  |  1:??? "If Type ( id:{func} ) is Object and id:{func} has a [ [ SourceText ] ] internal slot and id:{func} . [ [ SourceText ] ] is a sequence of Unicode code points and ! HostHasSourceTextAvailable ( id:{func} ) is value:{true} , then in:{} out:{}"
  |  3:??? "If id:{func} is a < emu - xref href = \" # sec - built - in - function - objects \" > built - in function object < / emu - xref > , return an implementation - defined String source code representation of id:{func} . The representation must have the syntax of a nt:{NativeFunction} . Additionally , if id:{func} has an [ [ InitialName ] ] internal slot and id:{func} . [ [ InitialName ] ] is a String , the portion of the returned String that would be matched by nt:{NativeFunctionAccessor?} nt:{PropertyName} must be the value of id:{func} . [ [ InitialName ] ] ."
  |  4:??? "If Type ( id:{func} ) is Object and IsCallable ( id:{func} ) is value:{true} , return an implementation - defined String source code representation of id:{func} . The representation must have the syntax of a nt:{NativeFunction} ."
  |  5:throw TypeError
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _func_ be the *this* value.""",
    """          1. If Type(_func_) is Object and _func_ has a [[SourceText]] internal slot and _func_.[[SourceText]] is a sequence of Unicode code points and ! HostHasSourceTextAvailable(_func_) is *true*, then""",
    """            1. Return ! CodePointsToString(_func_.[[SourceText]]).""",
    """          1. If _func_ is a <emu-xref href="#sec-built-in-function-objects">built-in function object</emu-xref>, return an implementation-defined String source code representation of _func_. The representation must have the syntax of a |NativeFunction|. Additionally, if _func_ has an [[InitialName]] internal slot and _func_.[[InitialName]] is a String, the portion of the returned String that would be matched by |NativeFunctionAccessor?| |PropertyName| must be the value of _func_.[[InitialName]].""",
    """          1. If Type(_func_) is Object and IsCallable(_func_) is *true*, return an implementation-defined String source code representation of _func_. The representation must have the syntax of a |NativeFunction|.""",
    """          1. Throw a *TypeError* exception.""",
  )
}
