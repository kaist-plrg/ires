import scalariform.formatter.preferences._
import sbtassembly.AssemblyPlugin.defaultUniversalScript

ThisBuild / version       := "1.0"
ThisBuild / scalaVersion  := "2.13.1"
ThisBuild / organization  := "kr.ac.kaist.ires"
ThisBuild / scalacOptions := Seq(
  "-deprecation", "-feature", "-language:postfixOps",
  "-language:implicitConversions", "-language:existentials",
  "-language:reflectiveCalls"
)
ThisBuild / javacOptions ++= Seq(
  "-encoding", "UTF-8"
)

// automatic reload
Global / onChangedBuildSource := ReloadOnSourceChanges

// size
lazy val tinyTest = taskKey[Unit]("Launch tiny tests (maybe milliseconds)")
lazy val smallTest = taskKey[Unit]("Launch small tests (maybe seconds)")
lazy val middleTest = taskKey[Unit]("Launch middle tests (maybe minutes)")
lazy val largeTest = taskKey[Unit]("Launch large tests (may hours)")

// ir
lazy val irTest = taskKey[Unit]("Launch ir tests")
lazy val irParseTest = taskKey[Unit]("Launch parse ir tests (small)")
lazy val irEvalTest = taskKey[Unit]("Launch eval ir tests (small)")
lazy val irBeautifierTest = taskKey[Unit]("Launch beautifier ir tests (small)")

// js
lazy val jsTest = taskKey[Unit]("Launch js tests")
lazy val jsEvalTest = taskKey[Unit]("Launch eval js tests (small)")

// test262
lazy val test262Test = taskKey[Unit]("Launch test262 tests")
lazy val test262ParseTest = taskKey[Unit]("Launch parse test262 tests (large)")
lazy val test262EvalTest = taskKey[Unit]("Launch eval test262 tests (large)")

lazy val ires = (project in file("."))
  .settings(
    name := "IRES",
    libraryDependencies ++= Seq(
      "io.spray" %% "spray-json" % "1.3.5",
      "org.scala-lang" % "scala-reflect" % scalaVersion.value,
      "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2",
      "org.scalatest" %% "scalatest" % "3.0.8" % "test",
      "org.jline" % "jline" % "3.13.3"
    ),
    test in assembly := {},
    testOptions in Test += Tests.Argument("-fDG", baseDirectory.value + "/tests/detail"),
    retrieveManaged := true,
    scalariformPreferences := scalariformPreferences.value
      .setPreference(DanglingCloseParenthesis, Force)
      .setPreference(DoubleIndentConstructorArguments, false),
    parallelExecution in Test := true,
    assemblyOutputPath in assembly := file("bin/ires"),
    assemblyOption in assembly := (assemblyOption in assembly).value
      .copy(prependShellScript = Some(defaultUniversalScript(shebang = false))),
    // basic tests
    test := (testOnly in Test).toTask(List(
      "*.ir.*Test",
      "*.js.*Test"
    ).mkString(" ", " ", "")).value,
    // size
    tinyTest := (testOnly in Test).toTask(" *TinyTest").value,
    smallTest := (testOnly in Test).toTask(" *SmallTest").value,
    middleTest := (testOnly in Test).toTask(" *MiddleTest").value,
    largeTest := (testOnly in Test).toTask(" *LargeTest").value,
    // ir
    irTest := (testOnly in Test).toTask(" *.ir.*Test").value,
    irParseTest := (testOnly in Test).toTask(" *.ir.Parse*Test").value,
    irEvalTest := (testOnly in Test).toTask(" *.ir.Eval*Test").value,
		irBeautifierTest := (testOnly in Test).toTask(" *.ir.Beautifier*Test").value,
    // js
    jsTest := (testOnly in Test).toTask(" *.js.*Test").value,
    jsEvalTest := (testOnly in Test).toTask(" *.js.Eval*Test").value,
    // test262
    test262Test := (testOnly in Test).toTask(" *.test262.*Test").value,
    test262ParseTest := (testOnly in Test).toTask(" *.test262.Parse*Test").value,
    test262EvalTest := (testOnly in Test).toTask(" *.test262.Eval*Test").value
  )
