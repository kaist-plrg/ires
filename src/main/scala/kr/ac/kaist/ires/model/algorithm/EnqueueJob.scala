package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::EnqueueJob` extends Algo {
  val head = NormalHead("EnqueueJob", List(Param("queueName", Normal), Param("job", Normal), Param("arguments", Normal)))
  val ids = List()
  val rawBody = parseInst("""{
  |  let callerContext = CONTEXT
  |  let callerRealm = callerContext.Realm
  |  let callerScriptOrModule = callerContext.ScriptOrModule
  |  let pending = (new PendingJob("Job" -> job, "Arguments" -> arguments, "Realm" -> callerRealm, "ScriptOrModule" -> callerScriptOrModule, "HostDefined" -> undefined))
  |  append pending -> JOB_QUEUE
  |  return CONST_empty
  |}""".stripMargin)
  val code = scala.Array[String]()
}
