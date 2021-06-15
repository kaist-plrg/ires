package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ModuleEnvironmentRecord.CreateImportBinding` extends Algo {
  val head = MethodHead("ModuleEnvironmentRecord", "CreateImportBinding", Param("envRec", Normal), List(Param("N", Normal), Param("M", Normal), Param("N2", Normal)))
  val ids = List(
    "sec-createimportbinding",
    "sec-module-environment-records",
    "sec-the-environment-record-type-hierarchy",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  3:??? "Create an immutable indirect binding in id:{envRec} for id:{N} that references id:{M} and id:{N2} as its target binding and record that the binding is initialized ."
  |  4:return CONST_empty
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: _envRec_ does not already have a binding for _N_.""",
    """            1. Assert: _M_ is a Module Record.""",
    """            1. Assert: When _M_.[[Environment]] is instantiated it will have a direct binding for _N2_.""",
    """            1. Create an immutable indirect binding in _envRec_ for _N_ that references _M_ and _N2_ as its target binding and record that the binding is initialized.""",
    """            1. Return NormalCompletion(~empty~).""",
  )
}
