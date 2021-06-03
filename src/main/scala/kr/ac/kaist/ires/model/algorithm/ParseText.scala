package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ParseText` extends Algo {
  val head = NormalHead("ParseText", List(Param("sourceText", Normal), Param("goalSymbol", Normal)))
  val ids = List(
    "sec-parsetext",
    "sec-source-text",
    "sec-ecmascript-language-source-code",
  )
  val rawBody = parseInst("""{
  |  0:??? "Attempt to parse id:{sourceText} using id:{goalSymbol} as the goal symbol , and analyse the parse result for any early error conditions . Parsing and early error detection may be interleaved in an implementation - defined manner ."
  |  1:??? "If the parse succeeded and no early errors were found , return the Parse Node ( an instance of id:{goalSymbol} ) at the root of the parse tree resulting from the parse ."
  |  2:??? "Otherwise , return a List of one or more value:{SyntaxError} objects representing the parsing errors and / or early errors . If more than one parsing error or early error is present , the number and ordering of error objects in the list is implementation - defined , but at least one must be present ."
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Attempt to parse _sourceText_ using _goalSymbol_ as the goal symbol, and analyse the parse result for any early error conditions. Parsing and early error detection may be interleaved in an implementation-defined manner.""",
    """        1. If the parse succeeded and no early errors were found, return the Parse Node (an instance of _goalSymbol_) at the root of the parse tree resulting from the parse.""",
    """        1. Otherwise, return a List of one or more *SyntaxError* objects representing the parsing errors and/or early errors. If more than one parsing error or early error is present, the number and ordering of error objects in the list is implementation-defined, but at least one must be present.""",
  )
}
