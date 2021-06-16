package kr.ac.kaist.ires.builtin

import kr.ac.kaist.ires.ir._

// builtin model structure
case class Struct(
  typeName: String,
  imap: IMap,
  nmap: NMap
) {
  // conversion to map structure
  def toMap(name: String): Map[Addr, Obj] = {
    var m = Map[Addr, Obj]()
    m += imap.toPair(name, typeName)
    m += nmap.toPair(name)
    m ++= nmap.getDescs(name)
    m
  }
}
