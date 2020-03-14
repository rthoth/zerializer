
ThisBuild / organization := "com.gh.rthoth"
ThisBuild / scalaVersion := "2.13.1"
ThisBuild / version := "1.0.0-SNAPSHOT"

ThisBuild / crossScalaVersions := List("2.13.1", "2.12.10")

lazy val root = (project in file("."))
  .settings(
    name := "zerializer",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.1.1" % "test"
    )
  )
  .enablePlugins(BoilerplatePlugin)