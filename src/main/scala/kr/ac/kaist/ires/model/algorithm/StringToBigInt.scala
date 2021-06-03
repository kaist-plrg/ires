package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::StringToBigInt` extends Algo {
  val head = NormalHead("StringToBigInt", List(Param("argument", Normal)))
  val ids = List()
  val rawBody = parseInst("""return (convert argument num2bigint )""".stripMargin)
  val code = scala.Array[String]()
}
