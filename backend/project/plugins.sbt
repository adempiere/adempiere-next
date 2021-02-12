import _root_.sbt._

val zioGrpcVersion = "0.4.2"

addSbtPlugin("ch.epfl.lamp" % "sbt-dotty" % "0.5.2")
addSbtPlugin("ch.epfl.scala" % "sbt-scalafix" % "0.9.25")
addSbtPlugin("ch.epfl.scala" % "sbt-bloop" % "1.4.6")
addSbtPlugin("org.scalameta" % "sbt-mdoc" % "2.2.16")
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.4.2")

addSbtPlugin("com.thesamet" %% "sbt-protoc" % "1.0.0")
libraryDependencies += "com.thesamet.scalapb.zio-grpc" %% "zio-grpc-codegen" % zioGrpcVersion
libraryDependencies += "com.thesamet.scalapb" %% "compilerplugin" % "0.10.10"
