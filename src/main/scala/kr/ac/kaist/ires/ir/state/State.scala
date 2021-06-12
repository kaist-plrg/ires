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
  // getters
  def apply(x: Id): Value = ???
  def apply(refV: RefValue): Value = ???
  def apply(addr: Addr, key: Value): Value = ??? // heap(addr)
  def apply(addr: Addr): Obj = ??? // heap(addr)
  // def interp(refV: RefValue): Value = st => refV match {
  //   case RefValueId(id) => st(
  //     (st.context.locals.getOrElse(id, st.globals.getOrElse(id, Absent)), st)
  //   case RefValueProp(addr, value) =>
  //     (st.heap(addr, value), st)
  //   case RefValueString(str, value) => value match {
  //     case Str("length") => (INum(str.length), st)
  //     case INum(k) => (Str(str(k.toInt).toString), st)
  //     case Num(k) => (Str(str(k.toInt).toString), st)
  //     case v => error(s"wrong access of string reference: $str.$value")
  //   }
  // }

  // setters
  def update(refV: RefValue, value: Value): Unit = ???
  // refV match {
  //   case RefValueId(id) => updated(id, value)
  //   case RefValueProp(addr, key) => updated(addr, key, value)
  //   case _ => error(s"illegal reference update: $refV = $value")
  // }
  def update(id: Id, value: Value): Unit = ???
  //   if (id.name.startsWith("GLOBAL_")) copy(globals = globals + (id -> value))
  //   else copy(context = context.define(id, value))
  def update(addr: Addr, key: Value, value: Value): Unit = ???
  //   copy(heap = heap.updated(addr, key, value))

  // deletes
  def delete(refV: RefValue): Unit = ???
  // refV match {
  //   case RefValueId(id) =>
  //     copy(context = context.deleted(id))
  //   case RefValueProp(addr, prop) =>
  //     copy(heap = heap.deleted(addr, prop))
  //   case _ => error(s"illegal reference delete: delete $refV")
  // }

  // append an element to a list
  def append(addr: Addr, value: Value): Unit = ???
  // {
  //   copy(heap = heap.append(addr, value))
  // }

  // prepend an element to a list
  def prepend(addr: Addr, value: Value): Unit = ???
  // {
  //   copy(heap = heap.prepend(addr, value))
  // }

  // pop an element from a list
  def pop(addr: Addr, idx: Value): Value = ???
  // {
  //   val (value, newHeap) = heap.pop(addr, idx)
  //   (value, copy(heap = newHeap))
  // }

  // copy objects
  def copyObj(addr: Addr): Addr = ???
  // {
  //   val (newAddr, newHeap) = heap.copyObj(addr)
  //   (newAddr, copy(heap = newHeap))
  // }

  // keys of map
  def keys(addr: Addr): Addr = ???
  // {
  //   val (newAddr, newHeap) = heap.keys(addr)
  //   (newAddr, copy(heap = newHeap))
  // }

  // map allocations
  def allocMap(ty: Ty): Addr = ??? // allocMap(ty, Map())
  def allocMap(ty: Ty, map: Map[Value, Value]): Addr = ???
  // {
  //   val (newAddr, newHeap) = heap.allocMap(ty, map)
  //   (newAddr, copy(heap = newHeap))
  // }

  // list allocations
  def allocList(list: List[Value]): Addr = ???
  // {
  //   val (newAddr, newHeap) = heap.allocList(list)
  //   (newAddr, copy(heap = newHeap))
  // }

  // symbol allocations
  def allocSymbol(desc: Value): Addr = ???
  // {
  //   val (newAddr, newHeap) = heap.allocSymbol(desc)
  //   (newAddr, copy(heap = newHeap))
  // }

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
