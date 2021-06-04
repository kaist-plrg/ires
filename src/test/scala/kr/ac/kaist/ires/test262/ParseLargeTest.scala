package kr.ac.kaist.ires.test262

import java.util.concurrent.atomic.AtomicInteger
import kr.ac.kaist.ires._
import kr.ac.kaist.ires.phase.FilterMeta
import kr.ac.kaist.ires.util.Useful._

class ParseLargeTest extends Test262Test {
  val name: String = "test262ParseTest"

  // registration
  val config = FilterMeta.test262configSummary

  // registration
  def init: Unit = check(name, {
    val failed = (for {
      file <- walkTree(TEST262_TEST_DIR)
      if jsFilter(file.getName)
      jsName = file.toString
      _ = passMsg(jsName)
      if optional(parseTest(parseFile(jsName))).isEmpty
      testName = jsName.drop(TEST262_TEST_DIR.length + 1)
    } yield {
      failMsg(testName)
      testName
    }).toList

    if (!failed.isEmpty) {
      fail(s"${failed.length} tests are failed to be parsed")
    }
  })
  init
}
