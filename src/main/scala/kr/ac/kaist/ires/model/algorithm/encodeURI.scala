package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::encodeURI` extends Algo {
  val head = BuiltinHead(parseRef("""encodeURI"""), List(Param("uri", Normal)))
  val ids = List(
    "sec-encodeuri-uri",
    "sec-uri-handling-functions",
    "sec-function-properties-of-the-global-object",
    "sec-global-object",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToString uri)
  |  0:let uriString = [? __x0__]
  |  1:??? "Let id:{unescapedURISet} be a String containing one instance of each code unit valid in nt:{uriReserved} and nt:{uriUnescaped} plus value:{\"#\"} ."
  |  2:app __x1__ = (Encode uriString unescapedURISet)
  |  2:return [? __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _uriString_ be ? ToString(_uri_).""",
    """          1. Let _unescapedURISet_ be a String containing one instance of each code unit valid in |uriReserved| and |uriUnescaped| plus *"#"*.""",
    """          1. Return ? Encode(_uriString_, _unescapedURISet_).""",
  )
}
