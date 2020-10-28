import sbt.Keys.{ unmanagedJars, unmanagedSourceDirectories }

name := "ADempiere-Aesthetic"

version := "0.0.1"

scalaVersion := "2.13.1"

crossPaths := false

libraryDependencies ++= Seq(
  "javax.servlet"         % "javax.servlet-api"    % "3.0.1" % "provided",
  "com.typesafe"          % "config"               % "1.2.0",
  "com.lihaoyi"          %% "os-lib"               % "0.6.3",
  "io.circe"             %% "circe-core"           % "0.12.3",
  "io.circe"             %% "circe-generic"        % "0.12.3",
  "io.circe"             %% "circe-parser"         % "0.12.3",
  "dev.zio"              %% "zio"                  % "1.0.0-RC18",
  "io.getquill"          %% "quill-jdbc"           % "3.5.0",
  "org.postgresql"        % "postgresql"           % "42.2.8",
  "org.scala-lang"        % "scala-reflect"        % "2.13.1",
  "org.scalactic"        %% "scalactic"            % "3.1.0",
  "org.scalatest"        %% "scalatest"            % "3.1.0" % "test",
  "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion,
  "io.grpc"               % "grpc-netty"           % grpcVersion
)

scalacOptions ++= Seq("-feature", "-unchecked", "-deprecation", "-encoding", "utf8")
javacOptions ++= Seq("-encoding", "UTF-8")

val sourceAdempiere = "../"

unmanagedJars in Compile ++= (file(sourceAdempiere + "/zkwebui/WEB-INF/lib") * "*.jar").classpath
unmanagedJars in Compile ++= (file(sourceAdempiere + "/tools/lib") * "*.jar").classpath
unmanagedJars in Compile ++= (file(sourceAdempiere + "/lib") * "*.jar").classpath
unmanagedJars in Compile ++= (file(sourceAdempiere + "/packages") * "*.jar").classpath
unmanagedJars in Compile ++= (file(sourceAdempiere + "/zkpackages") * "*.jar").classpath

scalaSource in Compile := baseDirectory.value / "src" / "main" / "scala"
scalaSource in Test := baseDirectory.value / "src" / "test" / "scala"

val grpcVersion = "1.31.1"

PB.targets in Compile := Seq(
  scalapb.gen(grpc = true)          -> (sourceManaged in Compile).value,
  scalapb.zio_grpc.ZioCodeGenerator -> (sourceManaged in Compile).value
)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x                             => MergeStrategy.last
}

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = true, includeDependency = false)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x                             => MergeStrategy.last
}

assemblyJarName in assembly := "ADempiere-Aesthetic.jar"
