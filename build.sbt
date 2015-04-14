name := "restapi"

organization := "myspots"

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "io.spray" %% "spray-routing" % "1.3.3",
  "io.spray" %% "spray-can" % "1.3.3",
// would be good to use this, but lacks doc  "com.typesafe.akka" %% "akka-http-experimental" % "1.0-M5",
  "com.typesafe.akka" %% "akka-actor" % "2.3.9",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "io.spray" %% "spray-testkit" % "1.3.3" % "test"
)
