package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::NormalCompletion` extends Algo {
  val head = NormalHead("NormalCompletion", List(Param("argument", Normal)))
  val ids = List()
  val rawBody = parseInst("""return argument""".stripMargin)
  val code = scala.Array[String]()
}
