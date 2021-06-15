package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IsStringPrefix` extends Algo {
  val head = NormalHead("IsStringPrefix", List(Param("p", Normal), Param("q", Normal)))
  val ids = List(
    "sec-isstringprefix",
    "sec-testing-and-comparison-operations",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof p) String)
  |  1:assert (= (typeof q) String)
  |  2:??? "If id:{q} can be the string - concatenation of id:{p} and some other String id:{r} , return value:{true} . Otherwise , return value:{false} ."
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: Type(_p_) is String.""",
    """        1. Assert: Type(_q_) is String.""",
    """        1. If _q_ can be the string-concatenation of _p_ and some other String _r_, return *true*. Otherwise, return *false*.""",
  )
}
