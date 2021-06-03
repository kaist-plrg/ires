package kr.ac.kaist.ires.ir

import kr.ac.kaist.ires._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.util.Useful._

class ParseTinyTest extends IRTest {
  val name: String = "irParseTest"

  // tests for IR parser
  def parseTest(program: Program): Unit = {
    val newProgram = Parser.parseProgram(program.beautified)
    assert(program == newProgram)
  }

  // registration
  def init: Unit = for (file <- walkTree(IR_DIR)) {
    val filename = file.getName
    if (irFilter(filename)) check(filename, {
      val name = file.toString
      val program = Parser.fileToProgram(name)
      parseTest(program)
    })
  }
  init
}
