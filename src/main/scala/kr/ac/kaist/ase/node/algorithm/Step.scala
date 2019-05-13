package kr.ac.kaist.ase.node.algorithm

import kr.ac.kaist.ase.LINE_SEP
import kr.ac.kaist.ase.parser._
import kr.ac.kaist.ase.util.Appendable

// steps
trait Step extends Appendable {
  val tokens: List[Token]
  def getSteps(init: List[Step]): List[Step] = ((this :: init) /: tokens) {
    case (list, StepList(steps)) => (list /: steps) {
      case (list, s) => s.getSteps(list)
    }
    case (list, _) => list
  }
  def getSteps: List[Step] = getSteps(Nil).reverse
  override def appendTo(sb: StringBuilder, pre: String): StringBuilder = {
    sb.append(pre).append("<step>")
    tokens.foreach(_.appendTo(sb, pre).append(" "))
    sb.append(pre).append("</step>").append(LINE_SEP)
  }
  def shortBeautify: String = {
    val sb = new StringBuilder
    tokens.foreach(_ match {
      case StepList(_) => sb.append("<step-list>...</step-list>")
      case token => token.appendTo(sb).append(" ")
    })
    sb.toString
  }
}
case class RawStep(tokens: List[Token]) extends Step

// parser for steps
trait StepParsers extends StmtParsers { this: AlgorithmParsers =>
  // steps
  lazy val rawStep: Parser[RawStep] = rep1(token) ^^ { RawStep(_) }
  lazy val step: Parser[Step] = tagged("step", stmt) | tagged("step", rawStep)
}