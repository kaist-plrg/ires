package kr.ac.kaist.ires.ir

import kr.ac.kaist.ires.util.Useful._
import kr.ac.kaist.ires.error.NotSupported
// XXX import kr.ac.kaist.ires.model.Model.tyMap
import scala.collection.mutable.{ Map => MMap }

// IR Heaps
case class Heap(map: MMap[Addr, Obj] = MMap()) extends IRNode {
  // heap size
  def size: Int = map.size

  // // getters
  // def get(addr: Addr): Option[Obj] = map.get(addr)
  // def apply(addr: Addr): Obj = map.getOrElse(addr, error(s"unknown address1: ${addr.beautified}"))
  // def apply(addr: Addr, key: Value): Value = this(addr) match {
  //   case (s: IRSymbol) => s(key)
  //   case (m: IRMap) => m(key)
  //   case (l: IRList) => l(key)
  //   case IRNotSupported(_, msg) => throw NotSupported(msg)
  // }

  // // setters
  // def updated(addr: Addr, prop: Value, value: Value): Heap = this(addr) match {
  //   case (m: IRMap) => copy(map = map + (addr -> m.updated(prop, value)))
  //   case v => error(s"not a map: $v")
  // }

  // // deletes
  // def deleted(addr: Addr, prop: Value): Heap = this(addr) match {
  //   case (m: IRMap) => copy(map = map + (addr -> m.deleted(prop)))
  //   case v => error(s"not a map: $v")
  // }

  // // appends
  // def append(addr: Addr, value: Value): Heap = this(addr) match {
  //   case (l: IRList) => copy(map = map + (addr -> l.append(value)))
  //   case v => error(s"not a list: $v")
  // }

  // // prepends
  // def prepend(addr: Addr, value: Value): Heap = this(addr) match {
  //   case (l: IRList) => copy(map = map + (addr -> l.prepend(value)))
  //   case v => error(s"not a list: $v")
  // }

  // // pops
  // def pop(addr: Addr, idx: Value): (Value, Heap) = this(addr) match {
  //   case (l: IRList) =>
  //     val (value, newList) = l.pop(idx)
  //     (value, copy(map = map + (addr -> newList)))
  //   case v => error(s"not a list: $v")
  // }

  // // copy objects
  // def copyObj(addr: Addr): (Addr, Heap) = {
  //   val newAddr = DynamicAddr(size)
  //   val newMap = map + (newAddr -> apply(addr))
  //   val newSize = size + 1
  //   (newAddr, Heap(newMap, newSize))
  // }

  // // keys of map
  // def keys(addr: Addr): (Addr, Heap) = this(addr) match {
  //   case (m: IRMap) =>
  //     val newAddr = DynamicAddr(size)
  //     val newL = m.props.toList.sortBy(_._2._2).map(_._1).toVector
  //     val newMap = map + (newAddr -> IRList(newL))
  //     val newSize = size + 1
  //     (newAddr, Heap(newMap, newSize))
  //   case v => error(s"not a map: $v")
  // }
  // // map allocations
  // def allocMap(
  //   ty: Ty,
  //   m: Map[Value, Value] = Map()
  // ): (Addr, Heap) = {
  //   val newAddr = DynamicAddr(size)
  //   // XXX val newM = tyMap.getOrElse(ty.name, Map()) ++ m
  //   val newM = m
  //   val newIRMap = newM.foldLeft(IRMap(ty, Map())) {
  //     case (m, (k, v)) => m.updated(k, v)
  //   }
  //   val newMap = map + (newAddr -> newIRMap)
  //   val newSize = size + 1
  //   (newAddr, Heap(newMap, newSize))
  // }

  // // list allocations
  // def allocList(list: List[Value]): (Addr, Heap) = {
  //   val newAddr = DynamicAddr(size)
  //   val newList = map + (newAddr -> IRList(list.toVector))
  //   val newSize = size + 1
  //   (newAddr, Heap(newList, newSize))
  // }

  // // symbol allocations
  // def allocSymbol(desc: Value): (Addr, Heap) = {
  //   val newAddr = DynamicAddr(size)
  //   val newList = map + (newAddr -> IRSymbol(desc))
  //   val newSize = size + 1
  //   (newAddr, Heap(newList, newSize))
  // }
}
