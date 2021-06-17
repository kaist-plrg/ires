package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.JSON.parse` extends Algo {
  val head = BuiltinHead(parseRef("""JSON.parse"""), List(Param("text", Normal), Param("reviver", Optional)))
  val ids = List(
    "sec-json.parse",
    "sec-json-object",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToString text)
  |  0:let jsonString = [? __x0__]
  |  1:??? "Parse ! StringToCodePoints ( id:{jsonString} ) as a JSON text as specified in ECMA - 404 . Throw a value:{SyntaxError} exception if it is not a valid JSON text as defined in that specification ."
  |  2:let scriptString = (+ (+ "(" jsonString) ");")
  |  3:app __x1__ = (StringToCodePoints scriptString)
  |  3:let script = [! __x1__]
  |  5:??? "Let id:{completion} be the result of evaluating id:{script} . The extended PropertyDefinitionEvaluation semantics defined in link:{sec-__proto__-property-names-in-object-initializers} must not be used during the evaluation ."
  |  6:let unfiltered = completion.Value
  |  13:app __x2__ = (IsCallable reviver)
  |  13:if (= __x2__ true) {
  |    9:app __x3__ = (OrdinaryObjectCreate INTRINSIC_Object_prototype)
  |    9:let root = [! __x3__]
  |    10:let rootName = ""
  |    11:app __x4__ = (CreateDataPropertyOrThrow root rootName unfiltered)
  |    11:[! __x4__]
  |    12:app __x5__ = (InternalizeJSONProperty root rootName reviver)
  |    12:return [? __x5__]
  |  } else return unfiltered
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _jsonString_ be ? ToString(_text_).""",
    """        1. [id="step-json-parse-validate"] Parse ! StringToCodePoints(_jsonString_) as a JSON text as specified in ECMA-404. Throw a *SyntaxError* exception if it is not a valid JSON text as defined in that specification.""",
    """        1. Let _scriptString_ be the string-concatenation of *"("*, _jsonString_, and *");"*.""",
    """        1. Let _script_ be ParseText(! StringToCodePoints(_scriptString_), |Script|).""",
    """        1. Assert: _script_ is a Parse Node.""",
    """        1. Let _completion_ be the result of evaluating _script_. The extended PropertyDefinitionEvaluation semantics defined in <emu-xref href="#sec-__proto__-property-names-in-object-initializers"></emu-xref> must not be used during the evaluation.""",
    """        1. Let _unfiltered_ be _completion_.[[Value]].""",
    """        1. [id="step-json-parse-assert-type"] Assert: _unfiltered_ is either a String, Number, Boolean, Null, or an Object that is defined by either an |ArrayLiteral| or an |ObjectLiteral|.""",
    """        1. If IsCallable(_reviver_) is *true*, then""",
    """          1. Let _root_ be ! OrdinaryObjectCreate(%Object.prototype%).""",
    """          1. Let _rootName_ be the empty String.""",
    """          1. Perform ! CreateDataPropertyOrThrow(_root_, _rootName_, _unfiltered_).""",
    """          1. Return ? InternalizeJSONProperty(_root_, _rootName_, _reviver_).""",
    """        1. Else,""",
    """          1. Return _unfiltered_.""",
  )
}
