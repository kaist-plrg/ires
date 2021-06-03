package kr.ac.kaist.ires

import kr.ac.kaist.ires.model.{ AST, Parser => JSParser }

class ParseLargeTest extends Test262Test {
  val name: String = "test262ParseTest"

  // tests for js-parser
  def parseJSTest(ast: AST): Unit = {
    val newAST = JSParser.parse(JSParser.Script(Nil), ast.toString).get
    assert(ast == newAST)
  }

  // do nothing after all tests
  override def afterAll(): Unit = {}

  // registration
  def init: Unit = {
    // for (file <- walkTree(new File(test262all))) {
    //   val filename = file.getName
    //   if (jsFilter(filename)) {
    //     val name = removedExt(filename)
    //     val jsName = file.toString
    //     val jsConfig = aseConfig.copy(fileNames = List(jsName))
    //     val testName = jsName.drop(test262all.length)

    //     lazy val ast = Parse((), jsConfig)
    //     check("Test262AllParse", testName, parseJSTest(ast))
    //   }
    // }
  }

  init
}
