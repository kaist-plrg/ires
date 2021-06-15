package kr.ac.kaist.ires.ir

import kr.ac.kaist.ires.ast._
import scala.collection.mutable.{ Map => MMap }

// IR Values
sealed trait Value extends IRNode {
  // escape completions
  def escaped(st: State): Value = this match {
    case (addr: Addr) => st(addr) match {
      case m @ IRMap(Ty("Completion"), _, _) => m(Str("Value"))
      case _ => this
    }
    case _ => this
  }
}

// IR Addresses
sealed trait Addr extends Value
case class NamedAddr(name: String) extends Addr
case class DynamicAddr(long: Long) extends Addr

// IR Functions
case class Func(name: String, params: List[Id], varparam: Option[Id], body: Inst) extends Value

case class Cont(params: List[Id], body: Inst, context: Context, ctxtStack: List[Context]) extends Value
// IR Constants
sealed trait Const extends Value
case class Num(double: Double) extends Const {
  override def equals(that: Any): Boolean = that match {
    case that: Num => doubleEquals(this.double, that.double)
    case _ => false
  }
}
case class ASTVal(ast: AST) extends Value
case class ASTMethod(func: Func, env: MMap[Id, Value]) extends Value
case class INum(long: Long) extends Const
case class BigINum(b: BigInt) extends Const
case class Str(str: String) extends Const
case class Bool(bool: Boolean) extends Const
case object Undef extends Const
case object Null extends Const
case object Absent extends Const
