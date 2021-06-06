package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait GeneratorBody extends AST {
  val kind: String = "GeneratorBody"
}

case class GeneratorBody0(x0: FunctionBody, parserParams: List[Boolean]) extends GeneratorBody {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("FunctionBody", x0, Nil).reverse
  val info: ASTInfo = GeneratorBody0
}
object GeneratorBody0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "EvaluateBody0" -> `AL::GeneratorBody[0,0].EvaluateBody`,
    "EvaluateGeneratorBody0" -> `AL::GeneratorBody[0,0].EvaluateGeneratorBody`,
  )
}
