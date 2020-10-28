import _root_.sbt._

val zioGrpcVersion = "0.4.0"

addSbtPlugin("com.eed3si9n"                             % "sbt-assembly"     % "0.14.10")
addSbtPlugin("org.jetbrains"                            % "sbt-idea-plugin"  % "3.5.0")
addSbtPlugin("com.earldouglas"                          % "xsbt-web-plugin"  % "4.1.0")
addSbtPlugin("com.thesamet"                             % "sbt-protoc"       % "0.99.34")
libraryDependencies += "com.thesamet.scalapb.zio-grpc" %% "zio-grpc-codegen" % zioGrpcVersion
