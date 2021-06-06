package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait ModuleBody extends AST {
  val kind: String = "ModuleBody"
}

case class ModuleBody0(x0: ModuleItemList, parserParams: List[Boolean]) extends ModuleBody {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ModuleItemList", x0, Nil).reverse
  val info: ASTInfo = ModuleBody0
}
object ModuleBody0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "Evaluation0" -> `AL::ModuleBody[0,0].Evaluation`,
    "EarlyErrors0" -> `AL::ModuleBody[0,0].EarlyErrors`,
  )
}
