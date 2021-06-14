package kr.ac.kaist.ires.ir

import kr.ac.kaist.ires._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.util.Useful._

class EvalSmallTest extends IRTest {
  val name: String = "irEvalTest"

  // registration
  def init: Unit = for (file <- walkTree(IR_DIR)) {
    val filename = file.getName
    if (irFilter(filename)) check(filename, {
      val name = file.toString
      val program = Parser.fileToProgram(name)
      val st = State(program)
      Interp(st)
    })
  }
  init
}
