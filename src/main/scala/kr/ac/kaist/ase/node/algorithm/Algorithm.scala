package kr.ac.kaist.ase.node.algorithm

import kr.ac.kaist.ase.LINE_SEP
import kr.ac.kaist.ase.parser._
import scala.util.parsing.combinator._

// algorithms
case class Algorithm(steps: List[Step]) {
  def getSteps(init: List[Step]): List[Step] = (init /: steps) {
    case (list, step) => step.getSteps(list)
  }
}

// parsers
trait AlgorithmParsers extends RegexParsers
    with StepParsers
    with StmtParsers
    with CondParsers
    with ExprParsers
    with TokenParsers {
  lazy val algorithm: Parser[Algorithm] = stepList ^^ {
    case sl => Algorithm(sl.steps)
  }
}
object Algorithm extends AlgorithmParsers with ParseTo[Algorithm] {
  lazy val rule = algorithm
}
