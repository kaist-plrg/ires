package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::HostEnsureCanCompileStrings` extends Algo {
  val head = NormalHead("HostEnsureCanCompileStrings", List(Param("callerRealm", Normal), Param("calleeRealm", Normal)))
  val ids = List()
  val rawBody = parseInst("""return CONST_empty""".stripMargin)
  val code = scala.Array[String]()
}
