package kr.ac.kaist.ires.js

import kr.ac.kaist.ires.IRESTest
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.model.{ AST, Parser => JSParser, Script }
import kr.ac.kaist.ires.util.Useful._
import spray.json._

trait JSTest extends IRESTest {
  val category: String = "js"

  // parse js codes
  def parse(str: String): Script =
    JSParser.parse(JSParser.Script(Nil), str).get
  def parseFile(filename: String): Script =
    JSParser.parse(JSParser.Script(Nil), fileReader(filename)).get
  def esparseFile(filename: String): Script =
    Script(executeCmd(s"bin/esparse $filename").parseJson)

  // tests for js parser
  def parseTest(ast: AST): Unit = {
    val newAST = parse(ast.toString)
    assert(ast == newAST)
  }

  // tests for js interpreter
  def evalTest(st: State): Unit = ???
  // st.context.locals.get(st.context.retId) match {
  //   case Some(addr: Addr) => st.heap(addr, Str("Type")) match {
  //     case (addr: Addr) =>
  //       assert(addr == st.globals.getOrElse(Id("CONST_normal"), Absent))
  //     case v => fail(s"invalid completion type: $v")
  //   }
  //   case Some(v) => fail(s"return not an address: $v")
  //   case None => fail("no return value")
  // }

  // conversion extension from .js to .ir
  val js2ir = changeExt("js", "ir")
}
