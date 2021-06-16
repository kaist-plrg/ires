package kr.ac.kaist.ires.ir

import kr.ac.kaist.ires.IRESTest
import kr.ac.kaist.ires.ir._

trait IRTest extends IRESTest {
  def category: String = "ir"

  // parse IR codes
  def irParse(str: String): Program = Parser.parseProgram(str)
  def irParseFile(filename: String): Program = Parser.fileToProgram(filename)

  // eval IR codes
  def irEval(st: State): State = Interp(st)
  def irEval(str: String): State = Interp(State(irParse(str)))
  def irEvalFile(filename: String): State =
    Interp(State(irParseFile(filename)), filename)

  // tests for IR parser
  def irParseTest(program: Program): Program = {
    val newProgram = irParse(program.beautified)
    assert(program == newProgram)
    program
  }
  def irParseTest(str: String): Program = irParseTest(irParse(str))
  def irParseTestFile(filename: String): Program = irParseTest(irParseFile(filename))
}
