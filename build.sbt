name := "generate-indices"

organization := "com.kolich"

version := "0.1"

scalaVersion := "2.9.2"

crossPaths := false

seq(com.github.retronym.SbtOneJar.oneJarSettings: _*)

libraryDependencies += "commons-lang" % "commons-lang" % "2.6"

mainClass in oneJar := Some("GenerateIndices")
