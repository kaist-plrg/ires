package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::decodeURI` extends Algo {
  val head = BuiltinHead(parseRef("""decodeURI"""), List(Param("encodedURI", Normal)))
  val ids = List(
    "sec-decodeuri-encodeduri",
    "sec-uri-handling-functions",
    "sec-function-properties-of-the-global-object",
    "sec-global-object",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToString encodedURI)
  |  0:let uriString = [? __x0__]
  |  1:??? "Let id:{reservedURISet} be a String containing one instance of each code unit valid in nt:{uriReserved} plus value:{\"#\"} ."
  |  2:app __x1__ = (Decode uriString reservedURISet)
  |  2:return [? __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _uriString_ be ? ToString(_encodedURI_).""",
    """          1. Let _reservedURISet_ be a String containing one instance of each code unit valid in |uriReserved| plus *"#"*.""",
    """          1. Return ? Decode(_uriString_, _reservedURISet_).""",
  )
}
