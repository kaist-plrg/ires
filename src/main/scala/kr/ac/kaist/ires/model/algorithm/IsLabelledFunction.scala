package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IsLabelledFunction` extends Algo {
  val head = NormalHead("IsLabelledFunction", List(Param("stmt", Normal)))
  val ids = List(
    "sec-islabelledfunction",
    "sec-labelled-statements",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:if (! (is-instance-of stmt LabelledStatement)) return false else 0:{}
  |  1:access __x0__ = (stmt "LabelledItem")
  |  1:let item = __x0__
  |  2:if (is-instance-of item LabelledItem1) {
  |    access FunctionDeclaration = (item "FunctionDeclaration")
  |    return true
  |  } else 0:{}
  |  3:access __x1__ = (item "Statement")
  |  3:let subStmt = __x1__
  |  4:app __x2__ = (IsLabelledFunction subStmt)
  |  4:return __x2__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If _stmt_ is not a |LabelledStatement|, return *false*.""",
    """        1. Let _item_ be the |LabelledItem| of _stmt_.""",
    """        1. If _item_ is <emu-grammar>LabelledItem : FunctionDeclaration</emu-grammar> , return *true*.""",
    """        1. Let _subStmt_ be the |Statement| of _item_.""",
    """        1. Return IsLabelledFunction(_subStmt_).""",
  )
}
