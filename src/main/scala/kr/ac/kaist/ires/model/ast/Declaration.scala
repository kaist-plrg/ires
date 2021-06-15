package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait Declaration extends AST {
  val kind: String = "Declaration"
}
object Declaration extends ASTHelper {
  def apply(v: JsValue): Declaration = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      Declaration0(HoistableDeclaration(x0), params, span)
    case JsSeq(JsInt(1), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      Declaration1(ClassDeclaration(x0), params, span)
    case JsSeq(JsInt(2), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      Declaration2(LexicalDeclaration(x0), params, span)
    case _ => throw InvalidAST
  }
}

case class Declaration0(x0: HoistableDeclaration, parserParams: List[Boolean], span: Span) extends Declaration {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("HoistableDeclaration", x0, Nil).reverse
  val info: ASTInfo = Declaration0
}
object Declaration0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class Declaration1(x0: ClassDeclaration, parserParams: List[Boolean], span: Span) extends Declaration {
  x0.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ClassDeclaration", x0, Nil).reverse
  val info: ASTInfo = Declaration1
}
object Declaration1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "DeclarationPart0" -> `AL::Declaration[1,0].DeclarationPart`,
  )
}

case class Declaration2(x0: LexicalDeclaration, parserParams: List[Boolean], span: Span) extends Declaration {
  x0.parent = Some(this)
  val idx: Int = 2
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("LexicalDeclaration", x0, Nil).reverse
  val info: ASTInfo = Declaration2
}
object Declaration2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "DeclarationPart0" -> `AL::Declaration[2,0].DeclarationPart`,
  )
}
