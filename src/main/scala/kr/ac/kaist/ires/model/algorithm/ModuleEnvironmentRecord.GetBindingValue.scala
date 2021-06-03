package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ModuleEnvironmentRecord.GetBindingValue` extends Algo {
  val head = MethodHead("ModuleEnvironmentRecord", "GetBindingValue", Param("envRec", Normal), List(Param("N", Normal), Param("S", Normal)))
  val ids = List(
    "sec-module-environment-records-getbindingvalue-n-s",
    "sec-module-environment-records",
    "sec-the-environment-record-type-hierarchy",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:assert (= S true)
  |  2:??? "If the binding for id:{N} is an indirect binding , then in:{} out:{}"
  |  7:??? "If the binding for id:{N} in id:{envRec} is an uninitialized binding , throw a value:{ReferenceError} exception ."
  |  8:return envRec.SubMap[N].BoundValue
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: _S_ is *true*.""",
    """            1. Assert: _envRec_ has a binding for _N_.""",
    """            1. If the binding for _N_ is an indirect binding, then""",
    """              1. Let _M_ and _N2_ be the indirection values provided when this binding for _N_ was created.""",
    """              1. Let _targetEnv_ be _M_.[[Environment]].""",
    """              1. If _targetEnv_ is *undefined*, throw a *ReferenceError* exception.""",
    """              1. Return ? _targetEnv_.GetBindingValue(_N2_, *true*).""",
    """            1. If the binding for _N_ in _envRec_ is an uninitialized binding, throw a *ReferenceError* exception.""",
    """            1. Return the value currently bound to _N_ in _envRec_.""",
  )
}
