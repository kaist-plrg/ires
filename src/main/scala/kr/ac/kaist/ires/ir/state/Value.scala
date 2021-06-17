package kr.ac.kaist.ires.ir

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.util.Useful._
import scala.collection.mutable.{ Map => MMap }

// IR Values
sealed trait Value extends IRNode {
  // escape completion
  def escaped(st: State): Value = this match {
    case addr: Addr => completionType(st) match {
      case CompletionType.NoCompl => this
      case CompletionType.Normal => st(addr, Str("Value"))
      case _ => error(s"unchecked abrupt completion: ${addr}")
    }
    case _ => this
  }

  // check completion
  def isCompletion(st: State): Boolean = completionType(st) match {
    case CompletionType.NoCompl => false
    case _ => true
  }

  // check abrupt completion
  def isAbruptCompletion(st: State): Boolean = completionType(st) match {
    case CompletionType.NoCompl => false
    case CompletionType.Normal => false
    case _ => true
  }

  // completion type
  def completionType(st: State): CompletionType = this match {
    case (addr: Addr) => st(addr) match {
      case m @ IRMap(Ty("Completion"), _, _) => m(Str("Type")) match {
        case NamedAddr("CONST_normal") => CompletionType.Normal
        case NamedAddr("CONST_break") => CompletionType.Break
        case NamedAddr("CONST_continue") => CompletionType.Continue
        case NamedAddr("CONST_return") => CompletionType.Return
        case NamedAddr("CONST_throw") => CompletionType.Throw
        case _ => error(s"invalid completion record: ${m.beautified}")
      }
      case _ => CompletionType.NoCompl
    }
    case _ => CompletionType.NoCompl
  }

  // wrap completion
  def wrapCompletion(
    st: State,
    ty: CompletionType = CompletionType.Normal
  ): Value = completionType(st) match {
    case CompletionType.NoCompl => st.allocMap(Ty("Completion"), Map(
      Str("Value") -> this,
      Str("Type") -> NamedAddr("CONST_normal"),
      Str("Target") -> NamedAddr("CONST_empty"),
    ))
    case _ => this
  }
}

// IR Addresses
sealed trait Addr extends Value
case class NamedAddr(name: String) extends Addr
case class DynamicAddr(long: Long) extends Addr

// IR Functions
case class Func(algo: Algo) extends Value

// IR Continuations
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
