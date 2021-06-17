package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::RunJobs` extends Algo {
  val head = NormalHead("RunJobs", List())
  val ids = List()
  val rawBody = parseInst("""{
  |  app __x0__ = (InitializeHostDefinedRealm)
  |  [? __x0__]
  |  app __x1__ = (EnqueueJob "ScriptJobs" ScriptEvaluationJob (new [SCRIPT_BODY, HOST_DEFINED]))
  |  while true {
  |    if (= EXECUTION_STACK[(- EXECUTION_STACK.length 1i)] CONTEXT) {
  |      let __x2__ = (- EXECUTION_STACK.length 1i)
  |      (pop EXECUTION_STACK __x2__)
  |      if (= EXECUTION_STACK.length 0i) CONTEXT = null else CONTEXT = EXECUTION_STACK[(- EXECUTION_STACK.length 1i)]
  |    } else CONTEXT = null
  |    if (= JOB_QUEUE.length 0.0) return undefined else {}
  |    let nextQueue = JOB_QUEUE
  |    let nextPending = (pop nextQueue 0i)
  |    let newContext = (new ExecutionContext("SubMap" -> (new SubMap())))
  |    newContext.Function = null
  |    newContext.Realm = nextPending.Realm
  |    newContext.ScriptOrModule = nextPending.ScriptOrModule
  |    append newContext -> EXECUTION_STACK
  |    CONTEXT = EXECUTION_STACK[(- EXECUTION_STACK.length 1i)]
  |    let job = nextPending.Job
  |    let args = nextPending.Arguments
  |    if (= args.length 2i) app __x3__ = (job args[0i] args[1i]) else app __x3__ = (job args[0i] args[1i] args[2i])
  |    let result = __x3__
  |    app __x4__ = (IsAbruptCompletion result)
  |    if __x4__ return result else {}
  |  }
  |}""".stripMargin)
  val code = scala.Array[String]()
}
