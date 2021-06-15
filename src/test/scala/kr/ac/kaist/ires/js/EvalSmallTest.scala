package kr.ac.kaist.ires.js

import kr.ac.kaist.ires.JS_DIR
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.phase._
import kr.ac.kaist.ires.util.Useful._

class EvalSmallTest extends JSTest {
  val name: String = "jsEvalTest"

  // registration
  def init: Unit = for (file <- walkTree(JS_DIR)) {
    val filename = file.getName
    // TODO if (jsFilter(filename)) check(filename, {
    //   val name = removedExt(filename)
    //   val jsName = file.toString

    //   val ast = parseFile(jsName)
    //   val st = Load(ast, jsName)
    //   Interp(st, jsName)

    //   val irName = js2ir(jsName)
    //   val program = Parser.fileToProgram(name)
    //   st.context.insts = program.insts
    //   Interp(st, irName)
    // })
  }
  init
}
