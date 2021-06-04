package kr.ac.kaist.ires.js

import kr.ac.kaist.ires._
import kr.ac.kaist.ires.model._
import kr.ac.kaist.ires.util.Useful._

class ParseSmallTest extends JSTest {
  val name: String = "jsParseTest"

  // registration
  def init: Unit = for (file <- walkTree(JS_DIR)) {
    val filename = file.getName
    if (jsFilter(filename)) check(filename, {
      val jsName = file.toString
      val ast = parseFile(jsName)
      parseTest(ast)
    })
  }
  init
}
