package kr.ac.kaist.ires

import kr.ac.kaist.ires.ir._

package object algorithm {
  import Param.Kind._

  // pre-defined parameters
  val THIS_PARAM = "this"
  val ARGS_LIST = "argumentsList"
  val NEW_TARGET = "NewTarget"
  val ENV_PARAM = "envRec"
  val OBJ_PARAM = "O"
  val AWAIT_PARAM = "value"
  val COMP_PARAMS = toParams(Normal)("x", "y")
  val REL_COMP_PARAMS = COMP_PARAMS ++ toParams(Optional)("LeftFirst")

  // conversion string to parameters
  def toParams(kind: Param.Kind)(params: String*): List[Param] =
    params.toList.map(Param(_, kind))

  // pop an instruction at the front
  def popFront(inst: Inst): Inst = inst match {
    case ISeq(hd :: tl) => ISeq(tl)
    case _ => ISeq(Nil)
  }

  // add instructions
  def prepend(p: List[Inst], i: Inst): Inst = add(p, i, true)
  def append(a: List[Inst], i: Inst): Inst = add(a, i, false)
  def add(
    added: List[Inst],
    inst: Inst,
    prepend: Boolean
  ): Inst = added match {
    case Nil => inst
    case _ => inst match {
      case ISeq(list) =>
        ISeq(if (prepend) added ++ list else list ++ added)
      case _ =>
        ISeq(if (prepend) added :+ inst else inst :: added)
    }
  }
}
