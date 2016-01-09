name := "AsyncLogger"
version := "1.0"

scalaVersion := "2.10.5"
scalacOptions := Seq("-feature")
ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = false) }
sources in (Compile, doc) := Seq.empty
publishArtifact in (Compile, packageDoc) := false
parallelExecution in Test := true
fork in Test := false

libraryDependencies ++= {
  val akkaVersion = "2.3.4"
  Seq("com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
    "org.scalatest" %% "scalatest" % "2.2.0")
}