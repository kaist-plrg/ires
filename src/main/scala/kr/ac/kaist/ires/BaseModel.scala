package kr.ac.kaist.ires

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.model._
import kr.ac.kaist.ires.model.Model._
import scala.collection.mutable.{ Map => MMap }

object BaseModel {
  lazy val globals: Map[String, Value] = Map(
    CONTEXT -> Null,
    EXECUTION_STACK -> NamedAddr(EXECUTION_STACK),
    GLOBAL -> NamedAddr(GLOBAL),
    HOST_DEFINED -> Undef,
    INTRINSICS -> NamedAddr(INTRINSICS),
    JOB_QUEUE -> NamedAddr(JOB_QUEUE),
    REALM -> NamedAddr(REALM),
    SYMBOL_REGISTRY -> NamedAddr(SYMBOL_REGISTRY),
    "Number" -> Str("Number"),
    "BigInt" -> Str("BigInt"),
    "Undefined" -> Str("Undefined"),
    "Null" -> Str("Null"),
    "String" -> Str("String"),
    "Boolean" -> Str("Boolean"),
    "Symbol" -> Str("Symbol"),
    "Reference" -> Str("Reference"),
    "Object" -> Str("Object"),
  // "RequireObjectCoercible" -> RequireObjectCoercible.func,
  // "EmptyFunction" -> EmptyFunction.func,
  // "EnumerateObjectPropertiesHelper" -> EnumerateObjectPropertiesHelper.func,
  // "FalseFunction" -> FalseFunction.func,
  // "Type" -> Type.func,
  // "ToNumber" -> ToNumber.func,
  // "ToBoolean" -> ToBoolean.func,
  // "ToObject" -> ToObject.func,
  // "ToString" -> ToString.func,
  // "max" -> max.func,
  // "min" -> min.func,
  // "abs" -> abs.func,
  // "floor" -> floor.func,
  // "MulOperation" -> MulOperation.func,
  // "Completion" -> Completion.func,
  // "WrapCompletion" -> WrapCompletion.func,
  // "NormalCompletion" -> NormalCompletion.func,
  // "ThrowCompletion" -> ThrowCompletion.func,
  // "IsDuplicate" -> IsDuplicate.func,
  // "HostEnsureCanCompileStrings" -> HostEnsureCanCompileStrings.func,
  // "HostHasSourceTextAvailable" -> HostHasSourceTextAvailable.func,
  // "HostReportErrors" -> HostReportErrors.func,
  // "HostPromiseRejectionTracker" -> HostPromiseRejectionTracker.func,
  // "RegExpCreate" -> RegExpCreate.func,
  // "GetArgument" -> GetArgument.func,
  // "GetTypeOf" -> GetTypeOf.func,
  // "IsArrayIndex" -> IsArrayIndex.func,
  // "IsAbruptCompletion" -> IsAbruptCompletion.func,
  // "INTRINSIC_ThrowTypeError" -> NamedAddr("GLOBAL.INTRINSIC_ThrowTypeError"),
  // "GLOBAL_executionStack" -> NamedAddr("executionStack"),
  // "GLOBAL_jobQueue" -> NamedAddr("jobQueue"),
  // "GLOBAL_context" -> Null,
  // "GLOBAL" -> NamedAddr("GLOBAL"),
  )

  val rawIntrinsicNames = Set("ThrowTypeError")
  lazy val heap: Map[Addr, Obj] = builtin.Heap.map ++ Map(
    NamedAddr(INTRINSICS) -> IRMap("Intrinsics")(
      for (i <- intrinsics) yield Str(INTRINSIC_PREFIX + i) -> NamedAddr((
        GLOBAL + "." +
        (if (rawIntrinsicNames contains i) INTRINSIC_PREFIX else "") +
        i.replaceAll("_", ".")
      ))
    ),
    NamedAddr(EXECUTION_STACK) -> IRList(),
    NamedAddr(JOB_QUEUE) -> IRList(),
    NamedAddr(SYMBOL_REGISTRY) -> IRList(),
  )
}
