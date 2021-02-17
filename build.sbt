name := "CensusAnalyzer"

version := "0.1"

scalaVersion := "2.12.7"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.8" % Test,
  "com.opencsv" % "opencsv" % "5.1",
  "com.google.code.gson" % "gson" % "2.8.5",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
)


