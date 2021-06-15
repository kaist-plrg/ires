package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::DeclarativeEnvironmentRecord.SetMutableBinding` extends Algo {
  val head = MethodHead("DeclarativeEnvironmentRecord", "SetMutableBinding", Param("envRec", Normal), List(Param("N", Normal), Param("V", Normal), Param("S", Normal)))
  val ids = List(
    "sec-declarative-environment-records-setmutablebinding-n-v-s",
    "sec-declarative-environment-records",
    "sec-the-environment-record-type-hierarchy",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:??? "If id:{envRec} does not have a binding for id:{N} , then in:{} out:{}"
  |  5:??? "If the binding for id:{N} in id:{envRec} is a strict binding , set id:{S} to value:{true} ."
  |  6:??? "If the binding for id:{N} in id:{envRec} has not yet been initialized , throw a value:{ReferenceError} exception ."
  |  7:??? "Else if the binding for id:{N} in id:{envRec} is a mutable binding , change its bound value to id:{V} ."
  |  8:??? "Else , in:{} out:{}"
  |  11:return CONST_empty
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. [id="step-setmutablebinding-missing-binding"] If _envRec_ does not have a binding for _N_, then""",
    """              1. If _S_ is *true*, throw a *ReferenceError* exception.""",
    """              1. Perform _envRec_.CreateMutableBinding(_N_, *true*).""",
    """              1. Perform _envRec_.InitializeBinding(_N_, _V_).""",
    """              1. Return NormalCompletion(~empty~).""",
    """            1. If the binding for _N_ in _envRec_ is a strict binding, set _S_ to *true*.""",
    """            1. If the binding for _N_ in _envRec_ has not yet been initialized, throw a *ReferenceError* exception.""",
    """            1. Else if the binding for _N_ in _envRec_ is a mutable binding, change its bound value to _V_.""",
    """            1. Else,""",
    """              1. Assert: This is an attempt to change the value of an immutable binding.""",
    """              1. If _S_ is *true*, throw a *TypeError* exception.""",
    """            1. Return NormalCompletion(~empty~).""",
  )
}
