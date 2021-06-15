package kr.ac.kaist.ires.ast

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.util.{ Span, Pos }
import spray.json._

trait ASTInfo {
  val maxK: Int
  val semMap: Map[String, Algo]
}
