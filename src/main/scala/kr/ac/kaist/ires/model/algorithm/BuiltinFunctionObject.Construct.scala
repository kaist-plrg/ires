package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BuiltinFunctionObject.Construct` extends Algo {
  val head = MethodHead("BuiltinFunctionObject", "Construct", Param("F", Normal), List(Param("argumentsList", Normal), Param("newTarget", Normal)))
  val ids = List()
  val rawBody = parseInst("""{
  |  let callerContext = CONTEXT
  |  if (= callerContext null) CONTEXT = null else {}
  |  let calleeContext = (new ExecutionContext())
  |  calleeContext.Function = F
  |  let calleeRealm = F.Realm
  |  calleeContext.Realm = calleeRealm
  |  calleeContext.ScriptOrModule = null
  |  append calleeContext -> EXECUTION_STACK
  |  CONTEXT = EXECUTION_STACK[(- EXECUTION_STACK.length 1i)]
  |  app result = (F.Code undefined argumentsList newTarget)
  |  if (= EXECUTION_STACK[(- EXECUTION_STACK.length 1i)] calleeContext) {
  |    let __x0__ = (- EXECUTION_STACK.length 1i)
  |    (pop EXECUTION_STACK __x0__)
  |  } else {}
  |  CONTEXT = EXECUTION_STACK[(- EXECUTION_STACK.length 1i)]
  |  return result
  |}""".stripMargin)
  val code = scala.Array[String]()
}
