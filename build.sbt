name := "restapi"

organization := "myspots"

version := "1.0"

scalaVersion := "2.11.6"

val sprayVersion = "1.3.3"

libraryDependencies ++= Seq(

  "io.spray"          %% "spray-routing"  % sprayVersion          ,
  "io.spray"          %% "spray-can"      % sprayVersion          ,
  "io.spray"          %% "spray-json"     % "1.3.1"               ,
  "com.typesafe.akka" %% "akka-actor"     % "2.3.9"               ,

  "org.scalatest"     %% "scalatest"      % "2.2.4"       % "test",
  "io.spray"          %% "spray-testkit"  % sprayVersion  % "test"

)
