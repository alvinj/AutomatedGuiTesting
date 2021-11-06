val scala3Version = "3.1.0"

lazy val root = project
    .in(file("."))
    .settings(
        name := "AutomatedGuiScripting",
        version := "0.1",
        scalaVersion := scala3Version
    )

//fork := true
