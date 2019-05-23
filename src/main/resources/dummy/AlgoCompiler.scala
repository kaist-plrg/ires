package kr.ac.kaist.ase.model

import kr.ac.kaist.ase.core._
import kr.ac.kaist.ase.core.Parser._
import kr.ac.kaist.ase.parser.TokenParsers
import kr.ac.kaist.ase.algorithm.Algorithm

object AlgoCompiler extends TokenParsers {
  def apply(algo: Algorithm): Func = Func(
    params = algo.params.map(Id(_)),
    body = ISeq(parseAll(rep(stmt), (algo.toTokenList)).get)
  )

  val ITODO = IExpr(ENotYetImpl)
  val ETODO = ENotYetImpl

  def addStmt = ("add" ~> expr <~ "at the back of") ~ (expr <~ ("."?) <~ next) ^^ { case e1 ~ e2 => ITODO }
  def letStmt = ("let" ~> id <~ "be") ~ (expr <~ ("."?) <~ next) ^^ { case e1 ~ e2 => ITODO }
  def assertStmt = "assert :" <~ step ^^ { case _ => ITODO }
  def returnStmt = "return" ~> expr <~ ("."?) <~ next ^^ { case e1 => ITODO }
  def repeatStmt = "repeat ," ~> in ~> rep(stmt) <~ out <~ next ^^ { case s => ISeq(s) }
  def performStmt = "perform" ~> expr <~ ("."?) <~ next ^^ { case e1 => ITODO }
  lazy val stmt: Parser[Inst] =
    addStmt |
      letStmt |
      assertStmt |
      returnStmt |
      repeatStmt |
      performStmt |
      step ^^^ ITODO

  def evaluationExpr = ("the result of evaluating" ~> word) ^^ { case _ => ERef(RefId(Id("eval"))) }
  def stringValueExpr = ("StringValue of" ~ word) ^^ { case _ => ETODO }
  def nameCallExpr = ((word <~ "(") ~ (repsep(expr, ",") ~ ")")) ^^ { case s ~ s1 => ERef(RefId(Id("nce"))) }
  def fieldCallExpr = (id <~ ".") ~ (field <~ "(") ~ (repsep(expr, ",") ~ ")") ^^ { case _ => ETODO }
  def questionExpr = ("?" ~> expr) ^^ { case _ => ERef(RefId(Id("qexpr"))) }
  def lhsExpr = lhs ^^ { case s => ERef(RefId(Id(s))) }
  def recordExpr = "a new" ~> word <~ "record" ^^ { case _ => ETODO }
  def emptyRecordExpr = "a new record" ^^ { case _ => ETODO }
  def executionContextExpr = "a new execution context" ^^ { case _ => ERef(RefId(Id("nec"))) }
  def executionContextStackExpr = "the execution context stack" ^^ { case _ => ERef(RefId(Id("rcs"))) }
  def runningExecutionContextExpr = "the running execution context" ^^ { case _ => ERef(RefId(Id("rec"))) }
  def emptyListExpr = "a new empty list" ^^ { case _ => ETODO }
  def emptyList1Expr = "«" <~ "»" ^^ { case _ => ETODO }
  def fieldListExpr = "«" ~> repsep(expr, ",") <~ "»" ^^ { case _ => ETODO }
  def filledRecordExpr = (word <~ "{") ~ (repsep(fieldInit, ",") <~ "}") ^^ { case r1 ~ fs => ERef(RefId(Id("fre"))) }
  def valueExpr = value ^^ { case s => ERef(RefId(Id(s))) }
  def jobQueueExpr = "the job queue named by" ~> id ^^ { case s => ERef(RefId(Id(s))) }
  lazy val expr: Parser[Expr] = evaluationExpr |
    stringValueExpr |
    nameCallExpr |
    fieldCallExpr |
    questionExpr |
    lhsExpr |
    recordExpr |
    emptyRecordExpr |
    executionContextExpr |
    executionContextStackExpr |
    runningExecutionContextExpr |
    emptyListExpr |
    emptyList1Expr |
    fieldListExpr |
    filledRecordExpr |
    valueExpr |
    jobQueueExpr

  def specialField = "[" ~> "[" ~> word <~ "]" <~ "]" ^^ { case s => s"""[[$s]]""" }
  def symbolField = "[" ~> "[" ~> symbol <~ "]" <~ "]" ^^ { case s => s"""[[$s]]""" }
  def normalField = word ^^ { case s => s }
  lazy val field = specialField |
    symbolField |
    normalField

  lazy val fieldInit = (field <~ ":") ~ expr ^^ { case s ~ s2 => s"""$s : $s2""" }

  lazy val symbol = "%" ~ word ~ "%" ^^ { case s => s"""%$s%""" }

  def propLhs = (id <~ ("." | ("'" <~ "s"))) ~ field ^^ { case s ~ i => s"""$s.$i""" }
  def idLhs = id
  lazy val lhs = propLhs | idLhs
}
