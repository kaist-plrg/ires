package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.parser.ESParsers

object Parser extends ESParsers {
  val Script: ESParser[Script] = ???
  val rules: Map[String, ESParser[AST]] = ???
  protected val TERMINAL: Lexer = ???
}
