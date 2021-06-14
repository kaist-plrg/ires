package kr.ac.kaist.ires.ir

import kr.ac.kaist.ires.util.Useful._
import scala.collection.mutable.{ Map => MMap }

// IR Objects
sealed trait Obj extends IRNode {
  // types
  def ty: Ty

  // copy of object
  def copy: Obj
}

// IR symbols
case class IRSymbol(desc: Value) extends Obj {
  val ty: Ty = Ty("Symbol")

  // getters
  def apply(key: Value): Value = key match {
    case Str("Description") => desc
    case v => error(s"an invalid symbol field access: $v")
  }

  // copy of object
  def copy: IRSymbol = IRSymbol(desc)
}

// IR maps
case class IRMap(
  ty: Ty,
  props: MMap[Value, (Value, Long)] = MMap(),
  var size: Long = 0L
) extends Obj {
  // get pairs
  def pairs: Map[Value, Value] = props.foldLeft(Map[Value, Value]()) {
    case (m, (k, (v, _))) => m + (k -> v)
  }

  // getters
  def apply(prop: Value): Value = props.get(prop).fold[Value](Absent)(_._1)

  // setters
  def update(prop: Value, value: Value): Unit = {
    props += prop -> (value, props.get(prop).fold(size)(_._2))
    size += 1
  }

  // deletes
  def delete(prop: Value): Unit = props -= prop

  // copy of object
  def copy: IRMap = {
    val newProps = MMap[Value, (Value, Long)]()
    newProps ++= props
    IRMap(ty, newProps, size)
  }
}

// IR lists
case class IRList(var values: Vector[Value]) extends Obj {
  // types
  def ty: Ty = Ty("List")

  // getters
  def apply(key: Value): Value = key match {
    case INum(long) =>
      val idx = long.toInt
      if (0 <= idx && idx < values.length) values(idx)
      else Absent
    case Str("length") => INum(values.length)
    case v => error(s"invalid key: $v")
  }

  // appends
  def append(value: Value): Unit = values :+= value

  // prepends
  def prepend(value: Value): Unit = values +:= value

  // pops
  def pop(idx: Value): Value = idx match {
    case INum(long) => {
      val k = long.toInt
      if (k < 0 || k >= values.length) error(s"Out of range: $k of $this")
      val v = values(k)
      values = values.slice(0, k) ++ values.slice(k + 1, values.length)
      v
    }
    case v => error(s"not an integer index: $this[$v]")
  }

  // copy of object
  def copy: IRList = IRList(values)
}

// IR not supported objects
case class IRNotSupported(tyname: String, desc: String) extends Obj {
  val ty: Ty = Ty(tyname)

  // copy of object
  def copy: IRNotSupported = IRNotSupported(tyname, desc)
}
