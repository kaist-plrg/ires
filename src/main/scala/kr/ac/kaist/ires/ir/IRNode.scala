package kr.ac.kaist.ires.ir

import kr.ac.kaist.ires.util.Useful._

trait IRNode {
  def beautified: String = beautified()
  def beautified(
    detail: Boolean = true,
    index: Boolean = false,
    asite: Boolean = false
  ): String = {
    val beautifier = IRNode.getBeautifier((detail, index, asite))
    import beautifier._
    beautify(this)
  }
}
object IRNode {
  private var btfMap: Map[(Boolean, Boolean, Boolean), Beautifier] = Map()
  private def getBeautifier(key: (Boolean, Boolean, Boolean)): Beautifier = {
    btfMap.get(key) match {
      case Some(b) => b
      case None =>
        val b = new Beautifier(key._1, key._2, key._3)
        btfMap += (key -> b)
        b
    }
  }
}
