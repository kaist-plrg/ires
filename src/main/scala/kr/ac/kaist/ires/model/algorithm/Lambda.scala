package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Lambda` extends Algo {
  val head = NormalHead("Lambda", List())
  val ids = List()
  val rawBody = parseInst("""return undefined""".stripMargin)
  val code = scala.Array[String]()
}
