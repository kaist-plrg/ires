package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.encodeURIComponent` extends Algo {
  val head = BuiltinHead(parseRef("""encodeURIComponent"""), List(Param("uriComponent", Normal)))
  val ids = List(
    "sec-encodeuricomponent-uricomponent",
    "sec-uri-handling-functions",
    "sec-function-properties-of-the-global-object",
    "sec-global-object",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToString uriComponent)
  |  0:let componentString = [? __x0__]
  |  1:??? "Let id:{unescapedURIComponentSet} be a String containing one instance of each code unit valid in nt:{uriUnescaped} ."
  |  2:app __x1__ = (Encode componentString unescapedURIComponentSet)
  |  2:return [? __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _componentString_ be ? ToString(_uriComponent_).""",
    """          1. Let _unescapedURIComponentSet_ be a String containing one instance of each code unit valid in |uriUnescaped|.""",
    """          1. Return ? Encode(_componentString_, _unescapedURIComponentSet_).""",
  )
}
