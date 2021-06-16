package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ScriptEvaluationJob` extends Algo {
  val head = NormalHead("ScriptEvaluationJob", List(Param("sourceText", Normal), Param("hostDefined", Normal)))
  val ids = List()
  val rawBody = parseInst("""{
  |  let realm = REALM
  |  app script = (ParseScript sourceText realm hostDefined)
  |  let body = script.ScriptBody
  |  if (! (= body absent)) {
  |    app __x1__ = (ScriptEvaluation script.ScriptBody)
  |    [? __x1__]
  |  } else {}
  |  return undefined
  |}""".stripMargin)
  val code = scala.Array[String]()
}
