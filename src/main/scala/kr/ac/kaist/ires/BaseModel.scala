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
    PRIMITIVE -> NamedAddr(PRIMITIVE),
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
  )

  lazy val heap: Map[Addr, Obj] = builtin.Heap.map ++ Map(
    NamedAddr(INTRINSICS) -> IRMap("Intrinsics")(
      for (i <- intrinsics) yield Str(INTRINSIC_PREFIX + i) -> intrinsicToAddr(i)
    ),
    NamedAddr(EXECUTION_STACK) -> IRList(),
    NamedAddr(JOB_QUEUE) -> IRList(),
    NamedAddr(PRIMITIVE) -> IRMap("Record")(List(
      Str("Number") -> NamedAddr(PRIMITIVE + ".Number"),
      Str("BigInt") -> NamedAddr(PRIMITIVE + ".BigInt"),
    )),
    NamedAddr(PRIMITIVE + ".Number") -> IRMap("Record")(List(
      Str("unit") -> Num(1),
      Str("unaryMinus") -> Func(`AL::Number::unaryMinus`),
      Str("bitwiseNOT") -> Func(`AL::Number::bitwiseNOT`),
      Str("exponentiate") -> Func(`AL::Number::exponentiate`),
      Str("multiply") -> Func(`AL::Number::multiply`),
      Str("divide") -> Func(`AL::Number::divide`),
      Str("remainder") -> Func(`AL::Number::remainder`),
      Str("add") -> Func(`AL::Number::add`),
      Str("subtract") -> Func(`AL::Number::subtract`),
      Str("leftShift") -> Func(`AL::Number::leftShift`),
      Str("signedRightShift") -> Func(`AL::Number::signedRightShift`),
      Str("unsignedRightShift") -> Func(`AL::Number::unsignedRightShift`),
      Str("lessThan") -> Func(`AL::Number::lessThan`),
      Str("equal") -> Func(`AL::Number::equal`),
      Str("sameValue") -> Func(`AL::Number::sameValue`),
      Str("sameValueZero") -> Func(`AL::Number::sameValueZero`),
      Str("bitwiseAND") -> Func(`AL::Number::bitwiseAND`),
      Str("bitwiseXOR") -> Func(`AL::Number::bitwiseXOR`),
      Str("bitwiseOR") -> Func(`AL::Number::bitwiseOR`),
      Str("toString") -> Func(`AL::Number::toString`),
    )),
    NamedAddr(PRIMITIVE + ".BigInt") -> IRMap("Record")(List(
      Str("unit") -> BigINum(1),
      Str("unaryMinus") -> Func(`AL::BigInt::unaryMinus`),
      Str("bitwiseNOT") -> Func(`AL::BigInt::bitwiseNOT`),
      Str("exponentiate") -> Func(`AL::BigInt::exponentiate`),
      Str("multiply") -> Func(`AL::BigInt::multiply`),
      Str("divide") -> Func(`AL::BigInt::divide`),
      Str("remainder") -> Func(`AL::BigInt::remainder`),
      Str("add") -> Func(`AL::BigInt::add`),
      Str("subtract") -> Func(`AL::BigInt::subtract`),
      Str("leftShift") -> Func(`AL::BigInt::leftShift`),
      Str("signedRightShift") -> Func(`AL::BigInt::signedRightShift`),
      Str("unsignedRightShift") -> Func(`AL::BigInt::unsignedRightShift`),
      Str("lessThan") -> Func(`AL::BigInt::lessThan`),
      Str("equal") -> Func(`AL::BigInt::equal`),
      Str("sameValue") -> Func(`AL::BigInt::sameValue`),
      Str("sameValueZero") -> Func(`AL::BigInt::sameValueZero`),
      Str("bitwiseAND") -> Func(`AL::BigInt::bitwiseAND`),
      Str("bitwiseXOR") -> Func(`AL::BigInt::bitwiseXOR`),
      Str("bitwiseOR") -> Func(`AL::BigInt::bitwiseOR`),
      Str("toString") -> Func(`AL::BigInt::toString`),
    )),
    NamedAddr(SYMBOL_REGISTRY) -> IRList(),
  )
}
