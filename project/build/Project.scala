

import sbt._

class LiftProject(info: ProjectInfo) extends DefaultWebProject(info) {
  val scalatoolsRelease = "Scala Tools Snapshot" at
  "http://scala-tools.org/repo-releases/"


  val liftVersion = "2.2-RC4"

  val lift_mongo_record = "net.liftweb" %% "lift-mongodb-record" % liftVersion % "compile->default" withSources
  val lift_webkit =  "net.liftweb" %% "lift-webkit" % liftVersion % "compile->default" withSources

  override def libraryDependencies = Set(
    "net.liftweb" %% "lift-testkit" % liftVersion % "compile->default",
    "org.mortbay.jetty" % "jetty" % "6.1.22" % "test->default",
    "ch.qos.logback" % "logback-classic" % "0.9.26",
    "junit" % "junit" % "4.5" % "test->default",
    "org.scala-tools.testing" %% "specs" % "1.6.6" % "test->default"
  ) ++ super.libraryDependencies
}
