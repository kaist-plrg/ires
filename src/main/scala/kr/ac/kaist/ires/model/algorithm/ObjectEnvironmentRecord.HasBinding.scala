package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ObjectEnvironmentRecord.HasBinding` extends Algo {
  val head = MethodHead("ObjectEnvironmentRecord", "HasBinding", Param("envRec", Normal), List(Param("N", Normal)))
  val ids = List(
    "sec-object-environment-records-hasbinding-n",
    "sec-object-environment-records",
    "sec-the-environment-record-type-hierarchy",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:let bindings = envRec.BindingObject
  |  1:app __x0__ = (HasProperty bindings N)
  |  1:let foundBinding = [? __x0__]
  |  2:if (= foundBinding false) return false else 0:{}
  |  3:if (= envRec.withEnvironment false) return true else 0:{}
  |  4:app __x1__ = (Get bindings SYMBOL_unscopables)
  |  4:let unscopables = [? __x1__]
  |  5:if (= (typeof unscopables) Object) {
  |    6:app __x2__ = (Get unscopables N)
  |    6:app __x3__ = (ToBoolean [? __x2__])
  |    6:let blocked = [! __x3__]
  |    7:if (= blocked true) return false else 0:{}
  |  } else 0:{}
  |  8:return true
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _bindings_ be the binding object for _envRec_.""",
    """            1. Let _foundBinding_ be ? HasProperty(_bindings_, _N_).""",
    """            1. If _foundBinding_ is *false*, return *false*.""",
    """            1. If the _withEnvironment_ flag of _envRec_ is *false*, return *true*.""",
    """            1. Let _unscopables_ be ? Get(_bindings_, @@unscopables).""",
    """            1. If Type(_unscopables_) is Object, then""",
    """              1. Let _blocked_ be ! ToBoolean(? Get(_unscopables_, _N_)).""",
    """              1. If _blocked_ is *true*, return *false*.""",
    """            1. Return *true*.""",
  )
}
