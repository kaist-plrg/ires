package kr.ac.kaist.ires.ir

import kr.ac.kaist.ires.util.Useful._
import scala.collection.mutable.{ Map => MMap }

// IR States
case class State(
  var context: Context = Context(),
  var ctxtStack: List[Context] = Nil,
  val globals: MMap[Id, Value] = MMap(),
  val heap: Heap = Heap()
) extends IRNode {
  // return id and its value
  def retId: Id = context.retId
  def retVal: Value = this(retId)

  // get local variable maps
  def locals: MMap[Id, Value] = context.locals

  // getters
  def apply(refV: RefValue): Value = refV match {
    case RefValueId(x) => this(x)
    case RefValueProp(addr, value) => this(addr, value)
    case RefValueString(str, value) => value match {
      case Str("length") => INum(str.length)
      case INum(k) => Str(str(k.toInt).toString)
      case Num(k) => Str(str(k.toInt).toString)
      case v => error(s"wrong access of string reference: ${refV.beautified}")
    }
  }
  def apply(x: Id): Value =
    locals.getOrElse(x, globals.getOrElse(x, Absent))
  def apply(addr: Addr, key: Value): Value = heap(addr, key)
  def apply(addr: Addr): Obj = heap(addr)

  // setters
  def update(refV: RefValue, value: Value): Unit = refV match {
    case RefValueId(x) => update(x, value)
    case RefValueProp(addr, key) => update(addr, key, value)
    case _ => error(s"illegal reference update: ${refV.beautified} = ${value.beautified}")
  }
  def update(x: Id, value: Value): Unit =
    if (locals contains x) locals += x -> value
    else if (globals contains x) globals += x -> value
    else error(s"illegal variable update: ${x.beautified} = ${value.beautified}")
  def update(addr: Addr, key: Value, value: Value): Unit =
    heap.update(addr, key, value)

  // delete a key from a map
  def delete(refV: RefValue): Unit = refV match {
    case RefValueId(x) => context.locals -= x;
    case RefValueProp(addr, prop) => heap.delete(addr, prop)
    case _ => error(s"illegal reference delete: delete ${refV.beautified}")
  }

  // object operators
  def append(addr: Addr, value: Value): Unit = heap.append(addr, value)
  def prepend(addr: Addr, value: Value): Unit = heap.prepend(addr, value)
  def pop(addr: Addr, idx: Value): Value = heap.pop(addr, idx)
  def copyObj(addr: Addr): Addr = heap.copyObj(addr)
  def keys(addr: Addr): Addr = heap.keys(addr)
  def allocMap(ty: Ty, map: Map[Value, Value] = Map()): Addr = heap.allocMap(ty, map)
  def allocList(list: List[Value]): Addr = heap.allocList(list)
  def allocSymbol(desc: Value): Addr = heap.allocSymbol(desc)

  // get string for a given address
  def getString(addr: Addr): String = ???
  // {
  //   addr.beautified
  //   st.heap(addr).beautified
  // }
}
object State {
  def apply(program: Program): State = State(
    context = Context(insts = program.insts)
  )
}
