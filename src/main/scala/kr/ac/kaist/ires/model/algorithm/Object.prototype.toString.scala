package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Object.prototype.toString` extends Algo {
  val head = BuiltinHead(parseRef("""Object.prototype.toString"""), List())
  val ids = List(
    "sec-object.prototype.tostring",
    "sec-properties-of-the-object-prototype-object",
    "sec-object-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:if (= this undefined) return "[object Undefined]" else 2:{}
  |  1:if (= this null) return "[object Null]" else 2:{}
  |  2:app __x0__ = (ToObject this)
  |  2:let O = [! __x0__]
  |  3:app __x1__ = (IsArray O)
  |  3:let isArray = [? __x1__]
  |  13:if (= isArray true) let builtinTag = "Array" else if (! (= O.ParameterMap absent)) let builtinTag = "Arguments" else if (! (= O.Call absent)) let builtinTag = "Function" else if (! (= O.ErrorData absent)) let builtinTag = "Error" else if (! (= O.BooleanData absent)) let builtinTag = "Boolean" else if (! (= O.NumberData absent)) let builtinTag = "Number" else if (! (= O.StringData absent)) let builtinTag = "String" else if (! (= O.DateValue absent)) let builtinTag = "Date" else if (! (= O.RegExpMatcher absent)) let builtinTag = "RegExp" else let builtinTag = "Object"
  |  14:app __x2__ = (Get O SYMBOL_toStringTag)
  |  14:let tag = [? __x2__]
  |  15:if (! (= (typeof tag) String)) tag = builtinTag else 2:{}
  |  16:return (+ (+ "[object " tag) "]")
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If the *this* value is *undefined*, return *"[object Undefined]"*.""",
    """          1. If the *this* value is *null*, return *"[object Null]"*.""",
    """          1. Let _O_ be ! ToObject(*this* value).""",
    """          1. Let _isArray_ be ? IsArray(_O_).""",
    """          1. If _isArray_ is *true*, let _builtinTag_ be *"Array"*.""",
    """          1. Else if _O_ has a [[ParameterMap]] internal slot, let _builtinTag_ be *"Arguments"*.""",
    """          1. Else if _O_ has a [[Call]] internal method, let _builtinTag_ be *"Function"*.""",
    """          1. Else if _O_ has an [[ErrorData]] internal slot, let _builtinTag_ be *"Error"*.""",
    """          1. Else if _O_ has a [[BooleanData]] internal slot, let _builtinTag_ be *"Boolean"*.""",
    """          1. Else if _O_ has a [[NumberData]] internal slot, let _builtinTag_ be *"Number"*.""",
    """          1. Else if _O_ has a [[StringData]] internal slot, let _builtinTag_ be *"String"*.""",
    """          1. Else if _O_ has a [[DateValue]] internal slot, let _builtinTag_ be *"Date"*.""",
    """          1. Else if _O_ has a [[RegExpMatcher]] internal slot, let _builtinTag_ be *"RegExp"*.""",
    """          1. Else, let _builtinTag_ be *"Object"*.""",
    """          1. Let _tag_ be ? Get(_O_, @@toStringTag).""",
    """          1. If Type(_tag_) is not String, set _tag_ to _builtinTag_.""",
    """          1. Return the string-concatenation of *"[object "*, _tag_, and *"]"*.""",
  )
}
