package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ParseScript` extends Algo {
  val head = NormalHead("ParseScript", List(Param("sourceText", Normal), Param("realm", Normal), Param("hostDefined", Normal)))
  val ids = List(
    "sec-parse-script",
    "sec-scripts",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  1:let body = SCRIPT
  |  3:return (new ScriptRecord("Realm" -> realm, "Environment" -> undefined, "ECMAScriptCode" -> body, "HostDefined" -> hostDefined))
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: _sourceText_ is an ECMAScript source text (see clause <emu-xref href="#sec-ecmascript-language-source-code"></emu-xref>).""",
    """        1. Let _body_ be ParseText(_sourceText_, |Script|).""",
    """        1. If _body_ is a List of errors, return _body_.""",
    """        1. Return Script Record { [[Realm]]: _realm_, [[Environment]]: *undefined*, [[ECMAScriptCode]]: _body_, [[HostDefined]]: _hostDefined_ }.""",
  )
}
