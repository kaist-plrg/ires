package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.util.{ InfNum, PInf }

case class Param(name: String, kind: Param.Kind = Param.Kind.Normal) {
  import Param.Kind._

  def toOptional: Param = Param(name, Optional)

  // count arity
  lazy val count: (InfNum, InfNum) = kind match {
    case Normal => (1, 1)
    case Optional => (0, 1)
    case Variadic => (0, PInf)
  }

  // conversion to string
  override def toString: String = {
    kind match {
      case Normal => name
      case Optional => name + "?"
      case Variadic => "..." + name
    }
  }

  // conversion to Scala code
  def toScala: String = s"""Param("$name", $kind)"""
}
object Param {
  type Kind = Kind.Value
  object Kind extends Enumeration {
    val Normal, Optional, Variadic = Value
  }

  def toParams(
    param: String,
    kind: Param.Kind = Param.Kind.Normal
  ): List[Param] = toParams(List(param), kind)
  def toParams(
    params: List[String],
    kind: Param.Kind
  ): List[Param] = params.map(Param(_, kind))

  // pre-defined parameters
  val THIS_PARAM = "this"
  val ARGS_LIST = "argumentsList"
  val NEW_TARGET = "NewTarget"
  val ENV_PARAM = "envRec"
  val OBJ_PARAM = "O"
  val AWAIT_PARAM = "value"
  val COMP_PARAMS = toParams(List("x", "y"), Param.Kind.Normal)
  val REL_COMP_PARAMS = COMP_PARAMS ++ toParams("LeftFirst", Param.Kind.Optional)
}
