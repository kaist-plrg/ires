package kr.ac.kaist.ires.algorithm

import kr.ac.kaist.ires.ir
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.util.Useful._
import kr.ac.kaist.ires.util.{ InfNum, PInf }
import scala.util.matching.Regex._

import Param._
import Param.Kind._

trait Head {
  // name
  val name: String

  // parameters
  val params: List[Param]

  // name for print
  def printName: String = name

  // conversion to string
  override def toString: String = s"${printName} (${params.mkString(", ")}):"

  // arity
  lazy val arity: (InfNum, InfNum) = {
    val targetParams = this match {
      case syn: SyntaxDirectedHead => syn.withParams
      case m: MethodHead => m.origParams
      case _ => params
    }
    targetParams.foldLeft[(InfNum, InfNum)]((0, 0)) {
      case ((s, e), p) => {
        val (ps, pe) = p.count
        (s + ps, e + pe)
      }
    }
  }
}
object Head {
  // get names and parameters
  val paramPattern = "[^\\s,()\\[\\]]+".r
  val rulePattern = ".*(Statement|Expression)\\s*Rules".r
  val namePattern = "[.:a-zA-Z0-9%\\[\\]@ /`_-]+".r
  val prefixPattern = ".*Semantics:".r
  val withParamPattern = "_\\w+_".r
  val optionalParamPattern = "optional parameter(s?).*".r
  val normPattern = "[\\s|-]".r
  val thisValuePattern = "this\\w+Value".r
  val letEnvRecPattern = "1. Let ([_\\w]+) be the \\w+ Environment Record.*".r
  val letObjPattern = "1. Let ([_\\w]+) be the \\w+ object\\.".r
  val methodDescPattern = """(?:The|When the)[\s\w\[\]]+method (?:of|for)[\s\w-]+,? (_\w+_),? (?:takes (?:no )?arguments?|is called|that was created|which was created).*""".r
}

// built-in algorithm heads
case class BuiltinHead(
  ref: ir.Ref,
  origParams: List[Param]
) extends Head {
  // name from base and fields
  val name: String = ref.beautified

  // fixed parameters for built-in algorithms
  val params: List[Param] =
    List(THIS_PARAM, ARGS_LIST, NEW_TARGET).map(Param(_))
}

// method algorithm heads
case class MethodHead(
  base: String,
  methodName: String,
  receiverParam: Param,
  origParams: List[Param]
) extends Head {
  // name from base and method name
  val name: String = s"$base.$methodName"

  // prepend `this` parameter
  val params: List[Param] = receiverParam :: origParams

  // check if step is let ~ `this` step in internal method algorithms
  def isLetThisStep(step: String): Boolean = (
    Head.letEnvRecPattern.matches(step) ||
    Head.letObjPattern.matches(step)
  )
}

// normal algorithm heads
case class NormalHead(
  name: String,
  params: List[Param]
) extends Head

// syntax-directed algorithm heads
case class SyntaxDirectedHead(
  lhsName: String,
  idx: Int,
  subIdx: Int,
  rhs: Rhs,
  methodName: String,
  withParams: List[Param]
) extends Head {
  // name with index and method name
  val name: String = s"$lhsName$idx$methodName$subIdx"
  override val printName = s"$lhsName[$idx,$subIdx].$methodName"

  // parameter type names and optional information
  val (types, optional) = {
    val names = rhs.getNTs.map(_.name)
    val duplicated = names.filter(p => names.count(_ == p) > 1).toSet
    var counter = Map[String, Int]()
    var optional = Set[String]()
    val types = (THIS_PARAM, lhsName) :: (rhs.getNTs.map {
      case NonTerminal(name, _, opt) =>
        val newName = if (duplicated contains name) {
          val k = counter.getOrElse(name, 0)
          counter += name -> (k + 1)
          s"$name$k"
        } else name
        if (opt) optional += newName
        (newName, name)
    })
    (types, optional)
  }

  // parameters
  val params: List[Param] = types.map {
    case (name, _) =>
      val kind =
        if (optional contains name) Optional
        else Normal
      Param(name, kind)
  } ++ withParams
}
