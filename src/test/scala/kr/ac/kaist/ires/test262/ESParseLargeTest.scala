package kr.ac.kaist.ires.test262

import kr.ac.kaist.ires._
import kr.ac.kaist.ires.phase.FilterMeta
import kr.ac.kaist.ires.util._
import kr.ac.kaist.ires.util.Useful._

class ESParseLargeTest extends Test262Test {
  val name: String = "test262ESParseTest"

  // registration
  val config = FilterMeta.test262configSummary

  val PARSE_TIMEOUT = 100 // second

  // registration
  def init: Unit = check(name, {
    val targets = config.normal.map(_.name)
    val nf = getPrintWriter("./test262-esparse-failed.log")
    var failed = 0
    ProgressBar("test262 esparse test", targets).foreach(name => {
      val jsName = s"$TEST262_TEST_DIR/$name"
      getError {
        timeout(esparseFile(jsName), PARSE_TIMEOUT)
      }.foreach(e => {
        failed += 1
        nf.println(s"$name: ${e.getMessage}")
        nf.flush()
      })
    })
    if (failed > 0) fail(s"$failed tests are failed to be parsed")
    nf.close()
  })
  init
}
