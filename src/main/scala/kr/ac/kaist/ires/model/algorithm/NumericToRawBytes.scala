package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::NumericToRawBytes` extends Algo {
  val head = NormalHead("NumericToRawBytes", List(Param("type", Normal), Param("value", Normal), Param("isLittleEndian", Normal)))
  val ids = List()
  val rawBody = parseInst("""return NumList""".stripMargin)
  val code = scala.Array[String]()
}
