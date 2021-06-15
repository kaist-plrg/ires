package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::DeclarativeEnvironmentRecord.GetBindingValue` extends Algo {
  val head = MethodHead("DeclarativeEnvironmentRecord", "GetBindingValue", Param("envRec", Normal), List(Param("N", Normal), Param("S", Normal)))
  val ids = List(
    "sec-declarative-environment-records-getbindingvalue-n-s",
    "sec-declarative-environment-records",
    "sec-the-environment-record-type-hierarchy",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  1:??? "If the binding for id:{N} in id:{envRec} is an uninitialized binding , throw a value:{ReferenceError} exception ."
  |  2:return envRec.SubMap[N].BoundValue
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: _envRec_ has a binding for _N_.""",
    """            1. If the binding for _N_ in _envRec_ is an uninitialized binding, throw a *ReferenceError* exception.""",
    """            1. Return the value currently bound to _N_ in _envRec_.""",
  )
}
