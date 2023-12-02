ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "TestProjectScala"
  )

scalacOptions ++= Seq("-unchecked","-deprecation","-Xcheckint","-encoding", "UTF-8")


fork := true

