package kr.ac.kaist

import java.io.File

package object ires {
  // Line seperator
  val LINE_SEP = System.getProperty("line.separator")

  // Base project directory root
  val BASE_DIR = System.getenv("IRES_HOME")

  // Tests directory root
  val TEST_DIR = s"$BASE_DIR/tests"
  val IR_DIR = s"$TEST_DIR/ir"
  val JS_DIR = s"$TEST_DIR/js"
  val TEST262_DIR = s"$TEST_DIR/test262"
  val TEST262_TEST_DIR = s"$TEST262_DIR/test"

  // Source directory root
  val SRC_DIR = s"$BASE_DIR/src/main"

  // Scala source directory root
  val SCALA_DIR = s"$SRC_DIR/scala/kr/ac/kaist/ires"

  // Model directory root
  val MODEL_DIR = s"$SCALA_DIR/model"

  // Current directory root
  val CUR_DIR = System.getProperty("user.dir")

  // timeout
  val TIMEOUT = 10

  // Debugging mode
  var DEBUG: Boolean = false

  // Logging mode
  var LOG: Boolean = false

  // Test mode
  var TEST_MODE: Boolean = false
}
