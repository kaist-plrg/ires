package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GetCapabilitiesExecutorFunctions` extends Algo {
  val head = NormalHead("GetCapabilitiesExecutorFunctions", List(Param("resolve", Normal), Param("reject", Normal)))
  val ids = List()
  val rawBody = parseInst("""{
  |  let capability = (new PromiseCapabilityRecord("Resolve" -> undefined, "Reject" -> undefined))
  |  let F = (new OrdinaryObject("Capability" -> capability))
  |  let promiseCapability = F.Capability
  |  if
  |  (! (= promiseCapability.Resolve undefined))
  |  throw TypeError
  |  if
  |  (! (= promiseCapability.Reject undefined))
  |  throw TypeError
  |  promiseCapability.Resolve = resolve
  |  promiseCapability.Reject = reject
  |  return undefined
  |}""".stripMargin)
  val code = scala.Array[String]()
}
