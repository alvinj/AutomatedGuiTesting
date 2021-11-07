val scala3Version = "3.0.1"

lazy val root = project
    .in(file("."))
    .settings(
        name := "AutomatedGuiScripting",
        version := "0.1",
        scalaVersion := scala3Version,
        libraryDependencies ++= Seq(
            // ScalaTest does not yet work with Scala 3.1
            "org.scalactic" %% "scalactic" % "3.2.10" % "test",
            "org.scalatest" %% "scalatest" % "3.2.10" % "test"
        )
    )

// libraryDependencies ++= Seq(
//     ("org.scalactic" %% "scalactic" % "3.2.10" % "test") cross CrossVersion.for3Use2_13,
//     ("org.scalatest" %% "scalatest" % "3.2.10" % "test") cross CrossVersion.for3Use2_13
// )

//fork := true
