package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ObjectEnvironmentRecord.SetMutableBinding` extends Algo {
  val head = MethodHead("ObjectEnvironmentRecord", "SetMutableBinding", Param("envRec", Normal), List(Param("N", Normal), Param("V", Normal), Param("S", Normal)))
  val ids = List(
    "sec-object-environment-records-setmutablebinding-n-v-s",
    "sec-object-environment-records",
    "sec-the-environment-record-type-hierarchy",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:let bindings = envRec.BindingObject
  |  1:app __x0__ = (HasProperty bindings N)
  |  1:let stillExists = [? __x0__]
  |  2:if (&& (= stillExists false) (= S true)) throw ReferenceError else 0:{}
  |  3:app __x1__ = (Set bindings N V S)
  |  3:return [? __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _bindings_ be the binding object for _envRec_.""",
    """            1. Let _stillExists_ be ? HasProperty(_bindings_, _N_).""",
    """            1. If _stillExists_ is *false* and _S_ is *true*, throw a *ReferenceError* exception.""",
    """            1. Return ? Set(_bindings_, _N_, _V_, _S_).""",
  )
}
