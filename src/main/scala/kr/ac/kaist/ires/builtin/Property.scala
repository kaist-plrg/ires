package kr.ac.kaist.ires.builtin

import kr.ac.kaist.ires.ir._

// properties
sealed trait Property

// data properties
case class DataProperty(
  value: Value,
  writable: Boolean,
  enumerable: Boolean,
  configurable: Boolean
) extends Property

// accessor properties
case class AccessorProperty(
  get: Value,
  set: Value,
  enumerable: Boolean,
  configurable: Boolean
) extends Property
