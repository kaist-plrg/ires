package kr.ac.kaist.ires.ir

// IR References
sealed trait Ref extends IRNode
case class RefId(id: Id) extends Ref
case class RefProp(ref: Ref, expr: Expr) extends Ref
