package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ImportDeclaration extends AST {
  val kind: String = "ImportDeclaration"
}
object ImportDeclaration extends ASTHelper {
  def apply(v: JsValue): ImportDeclaration = v match {
    case JsSeq(JsInt(0), JsSeq(x1, x2), JsBoolSeq(params), JsSpan(span)) =>
      ImportDeclaration0(ImportClause(x1), FromClause(x2), params)
    case JsSeq(JsInt(1), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      ImportDeclaration1(ModuleSpecifier(x1), params)
    case _ => throw InvalidAST
  }
}

case class ImportDeclaration0(x1: ImportClause, x2: FromClause, parserParams: List[Boolean]) extends ImportDeclaration {
  x1.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"import $x1 $x2 ;"
  }
  val k: Int = d(x2, d(x1, 0))
  val fullList: List[(String, Value)] = l("FromClause", x2, l("ImportClause", x1, Nil)).reverse
  val info: ASTInfo = ImportDeclaration0
}
object ImportDeclaration0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::ImportDeclaration[0,0].BoundNames`,
    "ModuleRequests0" -> `AL::ImportDeclaration[0,0].ModuleRequests`,
    "ImportEntries0" -> `AL::ImportDeclaration[0,0].ImportEntries`,
  )
}

case class ImportDeclaration1(x1: ModuleSpecifier, parserParams: List[Boolean]) extends ImportDeclaration {
  x1.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"import $x1 ;"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("ModuleSpecifier", x1, Nil).reverse
  val info: ASTInfo = ImportDeclaration1
}
object ImportDeclaration1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::ImportDeclaration[1,0].BoundNames`,
    "ImportEntries0" -> `AL::ImportDeclaration[1,0].ImportEntries`,
  )
}
