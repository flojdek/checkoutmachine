name := "Checkout Machine"

version := "3.14"

scalaVersion := "2.11.8"

lazy val root = (project in file("."))
  .aggregate(checkout, coniface)

lazy val checkout = project in file("checkout")

lazy val coniface = (project in file("coniface")).dependsOn(checkout)
