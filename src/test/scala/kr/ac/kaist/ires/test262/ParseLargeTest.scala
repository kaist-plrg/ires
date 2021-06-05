package kr.ac.kaist.ires.test262

import kr.ac.kaist.ires._
import kr.ac.kaist.ires.phase.FilterMeta
import kr.ac.kaist.ires.util.ProgressBar
import kr.ac.kaist.ires.util.Useful._

class ParseLargeTest extends Test262Test {
  val name: String = "test262ParseTest"

  // registration
  val config = FilterMeta.test262configSummary

  val PARSE_TIMEOUT = 1 // second

  // registration
  def init: Unit = check(name, {
    info("!!!")
    val targets = walkTree(TEST262_TEST_DIR)
      .filter(file => jsFilter(file.getName))
    val nf = getPrintWriter("./test262-parse-failed.log")
    var failed = 0
    ProgressBar("test262 parse test", targets).foreach(file => {
      val jsName = file.toString
      getError {
        timeout(parseTest(parseFile(jsName)), PARSE_TIMEOUT)
      }.foreach(e => {
        val testName = jsName.drop(TEST262_TEST_DIR.length + 1)
        failed += 1
        nf.println(s"$testName - ${e.getMessage}")
        nf.flush()
      })
    })
    if (failed > 0) fail(s"$failed tests are failed to be parsed")
    nf.close()
  })
  init
}
