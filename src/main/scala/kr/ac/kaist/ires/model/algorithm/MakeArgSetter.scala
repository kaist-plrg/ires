package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::MakeArgSetter` extends Algo {
  val head = NormalHead("MakeArgSetter", List(Param("name", Normal), Param("env", Normal)))
  val ids = List()
  val rawBody = parseInst("""return (new BuiltinFunctionObject())""".stripMargin)
  val code = scala.Array[String]()
}
