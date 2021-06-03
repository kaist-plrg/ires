package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait ImportsList extends AST {
  val kind: String = "ImportsList"
}

case class ImportsList0(x0: ImportSpecifier, parserParams: List[Boolean]) extends ImportsList {
  x0.parent = Some(this)
  val name: String = "ImportsList0"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ImportSpecifier", x0, Nil).reverse
  val info: ASTInfo = ImportsList0
}
object ImportsList0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class ImportsList1(x0: ImportsList, x2: ImportSpecifier, parserParams: List[Boolean]) extends ImportsList {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val name: String = "ImportsList1"
  override def toString: String = {
    s"$x0 , $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("ImportSpecifier", x2, l("ImportsList", x0, Nil)).reverse
  val info: ASTInfo = ImportsList1
}
object ImportsList1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::ImportsList[1,0].BoundNames`,
    "ImportEntriesForModule0" -> `AL::ImportsList[1,0].ImportEntriesForModule`,
  )
}
