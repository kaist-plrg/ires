package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.SyntaxError` extends Algo {
  val head = BuiltinHead(parseRef("""SyntaxError"""), List(Param("message", Normal)))
  val ids = List()
  val rawBody = parseInst("""??? "Need manual modeling"""".stripMargin)
  val code = scala.Array[String]()
}
