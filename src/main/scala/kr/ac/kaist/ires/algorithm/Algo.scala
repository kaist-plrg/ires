package kr.ac.kaist.ires.algorithm

import kr.ac.kaist.ires.ir._

trait Algo {
  // head
  val head: Head

  // section ids
  val ids: List[String]

  // raw body instruction
  val rawBody: Inst

  // get body with post processing
  def getBody: Inst = head match {
    case (head: SyntaxDirectedHead) if !head.rhsNames.contains(head.lhsName) =>
      val prefix = Parser.parseInsts(s"let ${head.lhsName} = this")
      prepend(prefix, rawBody)
    case (head: MethodHead) if head.isLetThisStep(code) =>
      popFront(rawBody)
    case (builtin: BuiltinHead) =>
      import Param.Kind._
      val prefix = builtin.origParams.zipWithIndex.map {
        case (Param(name, Variadic), i) =>
          Parser.parseInst(s"let $name = $ARGS_LIST")
        case (Param(name, _), i) =>
          Parser.parseInst(s"app $name = (GetArgument $ARGS_LIST ${i}i)")
      }
      prepend(prefix, rawBody)
    // handle abstract relational comparison
    case (head: NormalHead) if head.name == "AbstractRelationalComparison" =>
      val inst = Parser.parseInst("if (= LeftFirst absent) { LeftFirst = true } else { }")
      prepend(List(inst), rawBody)
    case _ => rawBody
  }

  // get code
  val code: Iterable[String]

  // name
  def name: String = head.name

  // normal check
  def isNormal: Boolean = head match {
    case _: NormalHead => true
    case _ => false
  }
}
object Algo {
  def unapply(algo: Algo): Option[(Head, Inst)] = Some((algo.head, algo.getBody))
}
