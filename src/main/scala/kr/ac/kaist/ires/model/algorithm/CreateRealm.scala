package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CreateRealm` extends Algo {
  val head = NormalHead("CreateRealm", List())
  val ids = List(
    "sec-createrealm",
    "sec-code-realms",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:let realmRec = (new RealmRecord("SubMap" -> (new SubMap())))
  |  1:app __x0__ = (CreateIntrinsics realmRec)
  |  1:__x0__
  |  2:realmRec.GlobalObject = undefined
  |  3:realmRec.GlobalEnv = undefined
  |  4:realmRec.TemplateMap = (new [])
  |  5:return realmRec
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _realmRec_ be a new Realm Record.""",
    """        1. Perform CreateIntrinsics(_realmRec_).""",
    """        1. Set _realmRec_.[[GlobalObject]] to *undefined*.""",
    """        1. Set _realmRec_.[[GlobalEnv]] to *undefined*.""",
    """        1. Set _realmRec_.[[TemplateMap]] to a new empty List.""",
    """        1. Return _realmRec_.""",
  )
}
