package kr.ac.kaist.ires.ir

import scala.collection.mutable.{ Map => MMap }

case class Context(
  val retId: Id = Id("__RETURN__"),
  val name: String = "__TOP_LEVEL__",
  var insts: List[Inst] = Nil,
  val locals: MMap[Id, Value] = MMap()
) extends IRNode {
  def copied: Context = {
    val newLocals = MMap[Id, Value]()
    newLocals ++= locals
    copy(locals = newLocals)
  }
}
