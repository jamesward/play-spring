name := "play-spring"

version := "0.0.1-SNAPSHOT"

organization := "com.jamesward"

scalaVersion := "2.10.5"

crossScalaVersions := Seq("2.10.5", "2.11.7")

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play" % "2.4.1",
  "org.springframework" % "spring-context" % "4.1.6.RELEASE",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "com.typesafe.play" %% "play-netty-server" % "2.4.1" % "test"
)
