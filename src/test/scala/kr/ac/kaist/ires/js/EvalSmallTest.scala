package kr.ac.kaist.ires

import kr.ac.kaist.ires.util.Useful._

class BasicSmallTest extends JSTest {
  val name: String = "jsEvalTest"

  // registration
  def init: Unit = for (file <- walkTree(JS_DIR)) {
    // TODO
    // val filename = file.getName
    // if (jsFilter(filename)) {
    //   lazy val name = removedExt(filename)
    //   lazy val jsName = file.toString
    //   lazy val jsConfig = aseConfig.copy(fileNames = List(jsName))

    //   lazy val ast = Parse((), jsConfig)
    //   check("JSParse", name, parseJSTest(ast))

    //   lazy val st = IREval(Load(ast, jsConfig), jsConfig)
    //   check("JSEval", name, evalJSTest(st))

    //   lazy val irName = js2ir(jsName)
    //   lazy val irConfig = aseConfig.copy(fileNames = List(irName))

    //   lazy val pgm = IRParse((), irConfig)
    //   lazy val irSt = IREval(st.copy(context = st.context.copy(insts = pgm.insts)), irConfig)
    //   check("JSCheck", name, {
    //     parseIRTest(pgm)
    //     evalIRTest(irSt)
    //   })
    // }
  }
  init
}
