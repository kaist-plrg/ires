package kr.ac.kaist.ase.util

import java.io.{ Reader, File, PrintWriter }
import kr.ac.kaist.ase.error.NoFileError
import kr.ac.kaist.ase.{ LINE_SEP, ASEConfig }
import scala.io.Source

object Useful {
  // file reader
  def fileReader(filename: String): Reader =
    Source.fromFile(filename).bufferedReader

  // indentation
  def indentation(s: StringBuilder, str: String, indent: Int): Unit = {
    str.split(LINE_SEP) match {
      case Array(str, rest @ _*) => {
        s.append(str)
        rest.foreach(rStr => {
          s.append(LINE_SEP)
          for (i <- 0 until indent) { s.append(" ") }
          s.append(rStr)
        })
      }
      case _ =>
    }
  }

  // walk directory
  def walkTree(file: File): Iterable[File] = {
    val children = new Iterable[File] {
      def iterator: Iterator[File] =
        if (file.isDirectory) file.listFiles.iterator
        else Iterator.empty
    }
    Seq(file) ++: children.flatMap(walkTree(_))
  }

  // extention filter
  def extFilter(ext: String): String => Boolean = _.endsWith(s".$ext")

  // file writer
  def getPrintWriter(filename: String): PrintWriter =
    new PrintWriter(new File(filename))

  // read file
  def readFile(filename: String): String =
    Source.fromFile(filename).mkString

  // get first filename
  def getFirstFilename(aseConfig: ASEConfig, job: String): String =
    aseConfig.fileNames.headOption.getOrElse(throw NoFileError(job))
}
