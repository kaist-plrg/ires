package kr.ac.kaist.ires.phase

import kr.ac.kaist.ires.{ LINE_SEP, IRESConfig }
import kr.ac.kaist.ires.model._
import kr.ac.kaist.ires.util._
import kr.ac.kaist.ires.util.Useful._
import scala.io.Source
import spray.json._

// Parse phase
case object Parse extends PhaseObj[Unit, ParseConfig, Script] {
  val name = "parse"
  val help = "Parses AST files."

  def apply(
    unit: Unit,
    iresConfig: IRESConfig,
    config: ParseConfig
  ): Script = {
    val filename = getFirstFilename(iresConfig, "parse")
    val ast =
      if (config.esparse) Script(executeCmd(s"bin/esparse $filename").parseJson)
      else Parser.parse(Parser.Script(Nil), fileReader(filename)).get
    config.jsonFile match {
      case Some(name) =>
        val nf = getPrintWriter(name)
        nf.println(ast.toJson.prettyPrint)
        nf.close()
      case None =>
    }
    ast
  }

  def defaultConfig: ParseConfig = ParseConfig()
  val options: List[PhaseOption[ParseConfig]] = List(
    ("json", StrOption((c, s) => c.jsonFile = Some(s)),
      "dump JSON of AST tree into a file."),
    ("esparse", BoolOption(c => c.esparse = true),
      "use `esparse` instead of the generated parser."),
  )
}

// Parse phase config
case class ParseConfig(
  var jsonFile: Option[String] = None,
  var esparse: Boolean = false
) extends Config
