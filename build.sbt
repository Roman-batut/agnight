val scala3Version = "3.3.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "AgNight",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test,
    libraryDependencies += "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.4",
    libraryDependencies += "com.softwaremill.sttp.client4" %% "core" % "4.0.0-M1",
    libraryDependencies += "com.lihaoyi" %% "upickle" % "3.1.3",
    libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "2.32.0"
  )