package kr.ac.kaist.ase.generator

import kr.ac.kaist.ase._
import kr.ac.kaist.ase.spec._
import kr.ac.kaist.ase.util.Useful._

object TypeGenerator {
  def apply(version: String, ty: Ty): Unit = {
    val name = ty.name
    ty.methods.foreach(method => MethodGenerator(version, s"$name.$method"))

    val nf = getPrintWriter(s"$MODEL_DIR/type/$name.scala")
    nf.println(s"""package kr.ac.kaist.ase.model""")
    nf.println(s"""""")
    nf.println(s"""import kr.ac.kaist.ase.core._""")
    nf.println(s"""object $name {""")
    nf.println(s"""  val map: CoreMap = CoreMap(""")
    nf.println(s"""    Ty("$name"),""")
    nf.println(s"""    Map(""")
    nf.println(ty.methods.map(method =>
      s"""      (Str("$method") -> $name$method.func)""").mkString("," + LINE_SEP))
    nf.println(s"""    )""")
    nf.println(s"""  )""")
    nf.println(s"""}""")
    nf.close()
  }
}
