package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BuiltinFunctionObject.Construct` extends Algo {
  val head = MethodHead("BuiltinFunctionObject", "Construct", Param("F", Normal), List(Param("argumentsList", Normal), Param("newTarget", Normal)))
  val ids = List(
    "sec-built-in-function-objects-construct-argumentslist-newtarget",
    "sec-built-in-function-objects",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""??? "Let id:{result} be the Completion Record that is the result of evaluating id:{F} in a manner that conforms to the specification of id:{F} . The value:{this} value is uninitialized , id:{argumentsList} provides the named parameters , and id:{newTarget} provides the NewTarget value ."""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _result_ be the Completion Record that is the result of evaluating _F_ in a manner that conforms to the specification of _F_. The *this* value is uninitialized, _argumentsList_ provides the named parameters, and _newTarget_ provides the NewTarget value.""",
  )
}
