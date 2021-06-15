package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::DeclarativeEnvironmentRecord.CreateImmutableBinding` extends Algo {
  val head = MethodHead("DeclarativeEnvironmentRecord", "CreateImmutableBinding", Param("envRec", Normal), List(Param("N", Normal), Param("S", Normal)))
  val ids = List(
    "sec-declarative-environment-records-createimmutablebinding-n-s",
    "sec-declarative-environment-records",
    "sec-the-environment-record-type-hierarchy",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  1:envRec.SubMap[N] = absent
  |  2:return CONST_empty
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: _envRec_ does not already have a binding for _N_.""",
    """            1. Create an immutable binding in _envRec_ for _N_ and record that it is uninitialized. If _S_ is *true*, record that the newly created binding is a strict binding.""",
    """            1. Return NormalCompletion(~empty~).""",
  )
}
