package kr.ac.kaist.ires.phase

import kr.ac.kaist.ires.{ LINE_SEP, IRESConfig }
import kr.ac.kaist.ires.model.{ Parser => JSParser, _ }
import kr.ac.kaist.ires.model.Model.RESULT
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.util.Useful._
import scala.io.Source

// Load phase
case object Load extends PhaseObj[Script, LoadConfig, State] {
  val name = "load"
  val help = "read script object and using global, convert to State object."

  def apply(
    script: Script,
    iresConfig: IRESConfig,
    config: LoadConfig
  ): State = this(script, getFirstFilename(iresConfig, "load"))

  def apply(script: Script): State = this(script, "unknown")

  def apply(
    script: Script,
    filename: String
  ): State = script match {
    case Script0(Some(body), _, _) => Model.initState(
      inst = Parser.parseInst(s"app $RESULT = (RunJobs)"),
      body = body,
      filename = filename,
    )
    case _ => State()
  }

  def defaultConfig: LoadConfig = LoadConfig()
  val options: List[PhaseOption[LoadConfig]] = List()
}

// Parse phase config
case class LoadConfig() extends Config
