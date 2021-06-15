package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::DefaultConstructorFunctions` extends Algo {
  val head = NormalHead("DefaultConstructorFunctions", List())
  val ids = List(
    "sec-default-constructor-functions",
    "sec-runtime-semantics-classdefinitionevaluation",
    "sec-class-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:if (= NewTarget undefined) throw TypeError else 8:{}
  |  1:let F = CONTEXT.Function
  |  7:if (= F.ConstructorKind CONST_derived) {
  |    4:app __x0__ = (F.GetPrototypeOf F)
  |    4:let func = [! __x0__]
  |    5:app __x1__ = (IsConstructor func)
  |    5:if (= __x1__ false) throw TypeError else 8:{}
  |    6:app __x2__ = (Construct func args NewTarget)
  |    6:return [? __x2__]
  |  } else {
  |    9:app __x3__ = (OrdinaryCreateFromConstructor NewTarget "%Object.prototype%")
  |    9:return [? __x3__]
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If NewTarget is *undefined*, throw a *TypeError* exception.""",
    """          1. Let _F_ be the active function object.""",
    """          1. If _F_.[[ConstructorKind]] is ~derived~, then""",
    """            1. NOTE: This branch behaves similarly to `constructor(...args) { super(...args); }`. The most notable distinction is that while the aforementioned ECMAScript source text observably calls the @@iterator method on `%Array.prototype%`, a Default Constructor Function does not.""",
    """            1. Let _func_ be ! _F_.[[GetPrototypeOf]]().""",
    """            1. If IsConstructor(_func_) is *false*, throw a *TypeError* exception.""",
    """            1. Return ? Construct(_func_, _args_, NewTarget).""",
    """          1. Else,""",
    """            1. NOTE: This branch behaves similarly to `constructor() {}`.""",
    """            1. Return ? OrdinaryCreateFromConstructor(NewTarget, *"%Object.prototype%"*).""",
  )
}
