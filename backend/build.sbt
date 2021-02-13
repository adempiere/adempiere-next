/**
  * Copyright (C) 2003-2021, e-Evolution Consultants S.A. , http://www.e-evolution.com
  * This program is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  * You should have received a copy of the GNU General Public License
  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
  * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
  * Created by victor.perez@e-evolution.com , www.e-evolution.com.
  * Modified by emeris.hernandez@e-evolution.com , www.e-evolution.com on 19/10/17.
  */

name := "ADempiere-Aesthetic"

val scala2Version = "2.13.4"
//val scala3Version = "3.0.0-M3"
val zioVersion = "1.0.4-2"
val quillVersion = "3.6.0"
val pgJdbcVersion = "42.2.18"
val grpcVersion = "1.35.0"
val sourceAdempiere = "/Users/e-Evolution/Develop/ADempiere/serviceManagement"

lazy val root = project
  .in(file("."))
  .settings(
    name := "ADempiere-Aesthetic",
    version := "0.1.0",
    scalaVersion := scala2Version,
    crossScalaVersions := Nil
  )
  .aggregate(dictionary)

lazy val kernel = (project in file("org.eevolution.kernel"))
  .settings(
    fork in Test := true,
    javaOptions in Test ++= Seq(
      "-DPropertyFile=/Users/e-Evolution/AdempierePG.properties"
    )
  )
  .settings(
    name := "kernel",
    scalaVersion := scala2Version,
    //crossScalaVersions := Seq(scala3Version, scala2Version),
    /*unmanagedSourceDirectories in Compile ++= {
      val sourceDir = (sourceDirectory in Compile).value
      CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((3, _))  => Seq(sourceDir / "scala-3")
        case Some((2,13))  => Seq(sourceDir / "scala-3")
        case Some((2,_))  => Seq(sourceDir / "scala-2.13", sourceDir / "scala-2.13+")
        case _             => Seq()
      }
    },*/
    scalacOptions ++= {
      if (isDotty.value)
        Seq(
          "-Ytasty-reader",
          "-encoding",
          "UTF-8",
          "-feature",
          "-unchecked",
          "-language:implicitConversions"
          // "-Xfatal-warnings" will be added after the migration
        )
      else
        Seq(
          "-encoding",
          "UTF-8",
          "-feature",
          "-deprecation",
          "-language:implicitConversions",
          "-Xfatal-warnings"
          //"-Wunused:imports,privates,locals",
          //"-Wvalue-discard"
        )
    },
    libraryDependencies ++= {
      CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((3, _)) =>
          Seq(("dev.zio" %% "zio" % zioVersion).withDottyCompat(scala2Version))
        case Some((2, 13)) =>
          Seq(
            "dev.zio" %% "zio" % zioVersion,
            "dev.zio" %% "zio-test" % zioVersion % Test,
            "dev.zio" %% "zio-test-sbt" % zioVersion % Test,
            "dev.zio" %% "zio-test-magnolia" % zioVersion % Test, // optional
            "dev.zio" %% "zio-test-junit" % zioVersion % Test,
            "org.scala-lang" % "scala-reflect" % scala2Version,
            "io.getquill" %% "quill-jdbc" % quillVersion,
            "org.postgresql" % "postgresql" % pgJdbcVersion
          )
        case _ =>
          Seq(
            "dev.zio" %% "zio" % zioVersion,
            "dev.zio" %% "zio-test" % zioVersion % Test,
            "dev.zio" %% "zio-test-sbt" % zioVersion % Test,
            "dev.zio" %% "zio-test-magnolia" % zioVersion % Test, // optional
            "dev.zio" %% "zio-test-junit" % zioVersion % Test,
            "org.scala-lang" % "scala-reflect" % scala2Version,
            "io.getquill" %% "quill-jdbc" % quillVersion,
            "org.postgresql" %% "postgresql" % pgJdbcVersion
          )
      }
    },
    unmanagedBase := baseDirectory.value.getParentFile / "lib",
    testFrameworks := Seq(new TestFramework("zio.test.sbt.ZTestFramework"))
  )

lazy val dictionary = (project in file("org.eevolution.dictionary"))
  .settings(
    fork in Test := true,
    javaOptions in Test ++= Seq(
      "-DPropertyFile=/Users/e-Evolution/AdempierePG.properties"
    )
  )
  .settings(
    name := "dictionary",
    scalaVersion := scala2Version,
    //crossScalaVersions := Seq(scala3Version, scala2Version),
    /*unmanagedSourceDirectories in Compile ++= {
      val sourceDir = (sourceDirectory in Compile).value
      CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((3, _))  => Seq(sourceDir / "scala-3")
        case Some((2,13))  => Seq(sourceDir / "scala-3")
        case Some((2,_))  => Seq(sourceDir / "scala-2.13", sourceDir / "scala-2.13+")
        case _             => Seq()
      }
    },*/
    scalacOptions ++= {
      if (isDotty.value)
        Seq(
          "-Ytasty-reader",
          "-encoding",
          "UTF-8",
          "-feature",
          "-unchecked",
          "-language:implicitConversions"
          // "-Xfatal-warnings" will be added after the migration
        )
      else
        Seq(
          "-encoding",
          "UTF-8",
          "-feature",
          "-deprecation",
          "-language:implicitConversions",
          "-Xfatal-warnings"
          //"-Wunused:imports,privates,locals",
          //"-Wvalue-discard"
        )
    },
    libraryDependencies ++= {
      CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((3, _)) =>
          Seq(("dev.zio" %% "zio" % zioVersion).withDottyCompat(scala2Version))
        case Some((2, 13)) =>
          Seq(
            "dev.zio" %% "zio" % zioVersion,
            "dev.zio" %% "zio-test" % zioVersion % Test,
            "dev.zio" %% "zio-test-sbt" % zioVersion % Test,
            "dev.zio" %% "zio-test-magnolia" % zioVersion % Test, // optional
            "dev.zio" %% "zio-test-junit" % zioVersion % Test,
            "org.scala-lang" % "scala-reflect" % scala2Version,
            "io.getquill" %% "quill-jdbc" % quillVersion,
            "org.postgresql" % "postgresql" % pgJdbcVersion,
            "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion,
            "io.grpc" % "grpc-netty" % grpcVersion
          )
        case _ =>
          Seq(
            "dev.zio" %% "zio" % zioVersion,
            "dev.zio" %% "zio-test" % zioVersion % Test,
            "dev.zio" %% "zio-test-sbt" % zioVersion % Test,
            "dev.zio" %% "zio-test-magnolia" % zioVersion % Test, // optional
            "dev.zio" %% "zio-test-junit" % zioVersion % Test,
            "org.scala-lang" % "scala-reflect" % scala2Version,
            "io.getquill" %% "quill-jdbc" % quillVersion,
            "org.postgresql" %% "postgresql" % pgJdbcVersion
          )
      }
    },
    unmanagedBase := baseDirectory.value.getParentFile / "lib",
    testFrameworks := Seq(new TestFramework("zio.test.sbt.ZTestFramework")),
    PB.targets in Compile := Seq(
      scalapb.gen(grpc = true) -> (sourceManaged in Compile).value,
      scalapb.zio_grpc.ZioCodeGenerator -> (sourceManaged in Compile).value
    )
  )
  .dependsOn(kernel)
