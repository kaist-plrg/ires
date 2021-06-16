package kr.ac.kaist.ires.builtin

import kr.ac.kaist.ires.ir._

// builtin model keys
sealed trait Key {
  // conversion to string
  override def toString: String = this match {
    case StrKey(name) => name
    case SymbolKey(name) => s"@$name"
  }

  // conversion to IR value
  def toValue: Value = this match {
    case StrKey(name) => Str(name)
    case SymbolKey(name) => NamedAddr(s"GLOBAL.Symbol.$name")
  }

  // conversion to property string
  def toPropString: String = this match {
    case StrKey(name) => s".$name"
    case SymbolKey(name) => s"[Symbol_$name]"
  }
}
case class StrKey(name: String) extends Key
case class SymbolKey(name: String) extends Key
